package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.ITestCaseDAO;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.session.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;

public class TestCaseHibernateDAO implements ITestCaseDAO{

    @Override
    public TestCase findById(Integer testCaseId) {
        try (Session session = HibernateSession.getSession()) {
            TypedQuery<TestCase> query = session.createQuery("from TestCase where id=:testCaseId", TestCase.class);
            query.setParameter("testCaseId", testCaseId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addTestCase(TestCase testCase) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.save(testCase);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTestCase(TestCase testCase) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.delete(testCase);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
