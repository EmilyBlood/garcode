package definiowanieZadan.databaseProvider.dao.impl;

import definiowanieZadan.databaseProvider.dao.ITestCaseDAO;
import definiowanieZadan.databaseProvider.entity.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;

public class TestCaseHibernateDAO implements ITestCaseDAO{

    Session session;

    public TestCaseHibernateDAO(Session session){
        this.session = session;
    }

    @Override
    public TestCase findById(Integer testCaseId) {
        TypedQuery<TestCase> query = session.createQuery("from TestCase where id=:testCaseId", TestCase.class);
        query.setParameter("testCaseId", testCaseId);
        return query.getSingleResult();
    }

    @Override
    public void addTestCase(TestCase testCase) {
        Transaction tx = session.beginTransaction();
        session.save(testCase);
        tx.commit();
    }

    @Override
    public void deleteTestCase(TestCase testCase) {
        Transaction tx = session.beginTransaction();
        session.delete(testCase);
        tx.commit();
    }
}
