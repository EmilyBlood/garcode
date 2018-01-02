package exerciseCreator.databaseProvider.dao;

import exerciseCreator.databaseProvider.entity.Threshold;

public interface IThresholdDAO {

    Threshold findById(Integer id);

    void addThreshold(Threshold threshold);

    void deleteThreshold(Threshold threshold);
}
