package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.beans.Sector;
import com.cliffhangout.beans.Way;
import com.cliffhangout.business.contract.manager.SectorManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class SectorManagerImpl extends AbstractManagerImpl implements SectorManager {

    @Override
    public void deleteSector(Sector sector) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if (sector.getWays() != null) {
                    for (Way way : sector.getWays()) {
                        if (way.getLengths() != null) {
                            for (Length length : way.getLengths()) {
                                if (length.getPoints() != null) {
                                    for (Point point : length.getPoints()) {
                                        getDaoFactory().getPointDao().delete(point);
                                    }
                                }
                                getDaoFactory().getLengthDao().delete(length);
                            }
                        }
                        getDaoFactory().getWayDao().delete(way);
                    }
                }
                getDaoFactory().getSectorDao().delete(sector);
            }
        });
    }

    @Override
    public void updateSector(Sector sector) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if (sector.getWays() != null) {
                    for (Way way : sector.getWays()) {
                        if (way.getLengths() != null) {
                            for (Length length : way.getLengths()) {
                                if (length.getPoints() != null) {
                                    for (Point point : length.getPoints()) {
                                        getDaoFactory().getPointDao().update(point);
                                    }
                                }
                                getDaoFactory().getLengthDao().update(length);
                            }
                        }
                        getDaoFactory().getWayDao().update(way);
                    }
                }
                getDaoFactory().getSectorDao().update(sector);
            }
        });
    }

    @Override
    public void addSector(Sector sector) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                getDaoFactory().getSectorDao().create(sector);
                if (sector.getWays() != null) {
                    for (Way way : sector.getWays()) {
                        getDaoFactory().getWayDao().create(way);
                        if (way.getLengths() != null) {
                            for (Length length : way.getLengths()) {
                                getDaoFactory().getLengthDao().create(length);
                                if (length.getPoints() != null) {
                                    for (Point point : length.getPoints()) {
                                        getDaoFactory().getPointDao().create(point);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }
}
