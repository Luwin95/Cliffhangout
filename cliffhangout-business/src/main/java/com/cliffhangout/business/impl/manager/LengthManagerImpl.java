package com.cliffhangout.business.impl.manager;

import com.cliffhangout.beans.Length;
import com.cliffhangout.beans.Point;
import com.cliffhangout.business.contract.manager.LengthManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class LengthManagerImpl extends AbstractManagerImpl implements LengthManager{
    @Override
    public void deleteLength(Length length) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                if(length.getPoints() !=null)
                {
                    for (Point point : length.getPoints())
                    {
                        getDaoFactory().getPointDao().delete(point);
                    }
                }
                getDaoFactory().getLengthDao().delete(length);
            }
        });
    }

    @Override
    public void updateLength(Length length) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                getDaoFactory().getLengthDao().update(length);
                if(length.getPoints() !=null)
                {
                    for (Point point : length.getPoints())
                    {
                        getDaoFactory().getPointDao().update(point);
                    }
                }
            }
        });

    }

    @Override
    public void createLength(Length length) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());
        vTransactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus
                                                                pTransactionStatus) {
                getDaoFactory().getLengthDao().create(length);
                if(length.getPoints() !=null)
                {
                    for (Point point : length.getPoints())
                    {
                        getDaoFactory().getPointDao().create(point);
                    }
                }
            }
        });

    }
}
