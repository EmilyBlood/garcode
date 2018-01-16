package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IThresholdDAO;
import exerciseCreator.databaseProvider.entity.Threshold;
import exerciseCreator.databaseProvider.session.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;


public class ThresholdHibernateDAO implements IThresholdDAO {

    @Override
    public Threshold findById(Integer id) {
        try (Session session = HibernateSession.getSession()) {
            TypedQuery<Threshold> query = session.createQuery("from Threshold where id=:thresholdId", Threshold.class);
            query.setParameter("thresholdId", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addThreshold(Threshold threshold) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.save(threshold);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteThreshold(Threshold threshold) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.delete(threshold);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
