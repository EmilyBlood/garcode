package exerciseCreator.databaseProvider.dataProvider;

import exerciseCreator.databaseProvider.dao.ITestCaseDAO;
import exerciseCreator.databaseProvider.dao.impl.TestCaseHibernateDAO;
import exerciseCreator.databaseProvider.entity.TestCase;

public class TestCaseDataProvider {

    ITestCaseDAO testCaseDAO = new TestCaseHibernateDAO();

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
