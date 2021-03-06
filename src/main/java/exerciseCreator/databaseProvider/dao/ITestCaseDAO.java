package exerciseCreator.databaseProvider.dao;

import exerciseCreator.databaseProvider.entity.TestCase;

public interface ITestCaseDAO {

    TestCase findById(Integer testCaseId);

    void addTestCase(TestCase testCase);

    void deleteTestCase(TestCase testCase);
}
