package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Site;
import com.cliffhangout.beans.Way;
import com.cliffhangout.business.contract.manager.WayManager;

import java.util.List;
import java.util.Map;

public class WayManagerImpl extends AbstractManagerImpl implements WayManager {
    @Override
    public void configWay(Way way, double heightWay, List<String> pointsNb, Map<String, Object> session, String idSector) {
        String quotationName = way.getQuotation().getName();
        way.setQuotation(getDaoFactory().getQuotationDao().findByName(quotationName));
        way.setHeight(heightWay);
        int wayPointNb=0;
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
        way.setPointsNb(wayPointNb);
        ((Site) session.get("site")).getSectors().get(Integer.parseInt(idSector)).addWay(way);
    }
}
