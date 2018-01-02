package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IThresholdDAO;
import exerciseCreator.databaseProvider.entity.Threshold;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;


public class ThresholdHibernateDAO implements IThresholdDAO {

    Session session;

    public ThresholdHibernateDAO(Session session){
        this.session = session;
    }

    @Override
    public Threshold findById(Integer id) {
        TypedQuery<Threshold> query = session.createQuery("from Threshold where id=:thresholdId", Threshold.class);
        query.setParameter("thresholdId", id);
        return query.getSingleResult();
    }

    @Override
    public void addThreshold(Threshold threshold) {
        Transaction tx = session.beginTransaction();
        session.save(threshold);
        tx.commit();
    }

    @Override
    public void deleteThreshold(Threshold threshold) {
        Transaction tx = session.beginTransaction();
        session.delete(threshold);
        tx.commit();
    }
}
