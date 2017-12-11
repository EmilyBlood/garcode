package definiowanieZadan.databaseProvider.dataProvider;

import definiowanieZadan.databaseProvider.dao.ITestCaseDAO;
import definiowanieZadan.databaseProvider.dao.impl.TestCaseHibernateDAO;
import definiowanieZadan.databaseProvider.entity.TestCase;
import definiowanieZadan.databaseProvider.session.HibernateSession;

public class TestCaseDataProvider {

    ITestCaseDAO testCaseDAO = new TestCaseHibernateDAO(HibernateSession.getSession());

    public TestCase getTestCaseById(Integer testCaseId) {
        return testCaseDAO.findById(testCaseId);
    }

    public void createTestCase(TestCase testCase) {
        testCaseDAO.addTestCase(testCase);
    }

    public void removeTestCase(TestCase testCase) {
        testCaseDAO.deleteTestCase(testCase);
    }
}
