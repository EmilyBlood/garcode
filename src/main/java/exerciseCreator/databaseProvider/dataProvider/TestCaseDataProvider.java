package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.ITestCaseDAO;
import exerciseCreator.databaseProvider.dao.impl.TestCaseHibernateDAO;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.session.HibernateSession;

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
