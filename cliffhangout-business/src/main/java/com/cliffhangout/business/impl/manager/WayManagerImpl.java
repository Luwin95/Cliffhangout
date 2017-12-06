package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import com.cliffhangout.business.contract.manager.WayManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

public class WayManagerImpl extends AbstractManagerImpl implements WayManager {
    @Override
    public void configWay(Way way, double heightWay, List<String> pointsNb, Map<String, Object> session, String idSector) {
        String quotationName = way.getQuotation().getName();
        way.setQuotation(getDaoFactory().getQuotationDao().findByName(quotationName));
        way.setHeight(heightWay);
        int wayPointNb=0;
        configWayLength(way, wayPointNb, pointsNb);
        way.setPointsNb(wayPointNb);
        ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).addWay(way);
    }

    @Override
    public void configEditWay(Way wayBean, double heightWay, List<String> pointsNb, Map<String, Object> session, String idSector, String idWay) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                Way wayToEdit = ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().get(Integer.parseInt(idWay));
                wayToEdit.setName(wayBean.getName());
                wayToEdit.setQuotation(getDaoFactory().getQuotationDao().findByName(wayBean.getQuotation().getName()));
                wayToEdit.setHeight(heightWay);
                int wayPointNb=0;
                configWayLength(wayBean, wayPointNb, pointsNb);
                for(int i=0; i<wayToEdit.getLengths().size(); i++)
                {
                    wayToEdit.getLengths().get(i).setName(wayBean.getLengths().get(i).getName());
                    wayToEdit.getLengths().get(i).setDescription(wayBean.getLengths().get(i).getDescription());
                    if(wayToEdit.getLengths().get(i).getPoints().size() != Integer.parseInt(pointsNb.get(i)))
                    {
                        for(Point point : wayToEdit.getLengths().get(i).getPoints())
                        {
                            getDaoFactory().getPointDao().delete(point);
                        }
                        wayToEdit.getLengths().get(i).setPoints(wayBean.getLengths().get(i).getPoints());
                        for(Point point : wayToEdit.getLengths().get(i).getPoints())
                        {
                            point.setLengthId(wayToEdit.getLengths().get(i).getId());
                            getDaoFactory().getPointDao().create(point);

                        }
                    }

                }
                for(Length length : wayToEdit.getLengths())
                {
                    for (Point point : length.getPoints())
                    {
                        wayPointNb++;
                    }
                }
                wayToEdit.setPointsNb(wayPointNb);
                updateWay(wayToEdit);
                ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().remove(Integer.parseInt(idWay));
                ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).getWays().add(wayToEdit);
            }
        });
    }

    @Override
    public void configWayLength(Way way, int wayPointNb, List<String> pointsNb) {
        for(int i=0; i<way.getLengths().size(); i++ )
        {
            for(int cpt=0; cpt<Integer.parseInt(pointsNb.get(i)); cpt++)
            {
                Point point = new Point();
                point.setName("Point n°"+(cpt+1));
                point.setDescription("Point n°"+(cpt+1)+" de la longueur n°"+(i+1));
                way.getLengths().get(i).addPoint(point);
                wayPointNb++;
            }
        }
    }

    @Override
    public void deleteWay(Way way) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(way.getLengths() !=null)
                {
                    for(Length length : way.getLengths())
                    {
                        if(length.getPoints() !=null)
                        {
                            for (Point point : length.getPoints())
                            {
                                getDaoFactory().getPointDao().delete(point);
                            }
                        }
                        getDaoFactory().getLengthDao().delete(length);
                    }
                }
                getDaoFactory().getWayDao().delete(way);
            }
        });

    }

    @Override
    public void updateWay(Way way) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(way.getLengths() !=null)
                {
                    for(Length length : way.getLengths())
                    {
                        if(length.getPoints() !=null)
                        {
                            for (Point point : length.getPoints())
                            {
                                getDaoFactory().getPointDao().update(point);
                            }
                        }
                        getDaoFactory().getLengthDao().update(length);
                    }
                }
                getDaoFactory().getWayDao().update(way);
            }
        });
    }

    @Override
    public void addWay(Way way) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                getDaoFactory().getWayDao().create(way);
                if(way.getLengths() !=null)
                {
                    for(Length length : way.getLengths())
                    {
                        length.setWayId(way.getId());
                        getDaoFactory().getLengthDao().create(length);
                        if(length.getPoints() !=null)
                        {
                            for (Point point : length.getPoints())
                            {
                                point.setLengthId(length.getId());
                                getDaoFactory().getPointDao().create(point);
                            }
                        }
                    }
                }
            }
        });
    }
}
