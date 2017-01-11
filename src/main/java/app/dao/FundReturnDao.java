package app.dao;

import java.util.Date;
import java.util.List;

import app.model.FundReturn;

/**
 * The Interface FundReturnDao.
 */
public interface FundReturnDao {
	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	List<FundReturn> getRecords();
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the fund return
	 */
	FundReturn findByCode(String code);
	
	/**
	 * Find by date.
	 *
	 * @param date the date
	 * @return the fund return
	 */
	List<FundReturn> findByDate(Date date);
}
