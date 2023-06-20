package com.blubank.doctorappointment.repository.custom;

import org.jinq.jpa.JPAJinqStream;
import org.jinq.jpa.JPAQueryLogger;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseJinqRepositoryImpl<T> {
    Logger LOG = LoggerFactory.getLogger(BaseJinqRepositoryImpl.class);

    @Autowired
    private JinqJPAStreamProvider jinqDataProvider;

    @PersistenceContext
    private EntityManager entityManager;

    static boolean isSetHint = false;

    protected abstract Class<T> entityType();

    public JPAJinqStream<T> stream() throws Exception {
        return streamOf(entityType());
    }
    private <U> JPAJinqStream<U> streamOf(Class<U> clazz) throws Exception {
        if (!isSetHint) {
            jinqDataProvider.setHint("exceptionOnTranslationFail", true);
            jinqDataProvider.setHint("isObjectEqualsSafe", true);
            jinqDataProvider.setHint("isAllEqualsSafe", true);
            jinqDataProvider.setHint("isCollectionContainsSafe", true);
            jinqDataProvider.setHint("queryLogger", (JPAQueryLogger) (query, positionParameters, namedParameters) -> {
                LOG.info("JINQ_Query: {}", query);
            });
            isSetHint = true;
        }
        return jinqDataProvider.streamAll(entityManager, clazz);
    }
}
