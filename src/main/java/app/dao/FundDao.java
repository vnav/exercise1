package app.dao;

import java.util.List;

import app.model.Fund;

/**
 * The Interface FundDao.
 */
public interface FundDao {
	
	/**
	 * Gets the by code.
	 *
	 * @param code the code
	 * @return the by code
	 */
	Fund findByCode(String code);
	
	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	List<Fund> getRecords();

}
