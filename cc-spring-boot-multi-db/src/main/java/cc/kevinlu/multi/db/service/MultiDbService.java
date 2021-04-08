package cc.kevinlu.multi.db.service;

public interface MultiDbService {

    void saveMultiNoTransaction();

    void saveMultiWithOneTransaction();

    void saveMultiWithTwoTransaction();

    void saveMultiWithMultiTransaction();

}
