package app.dao;

import java.util.Date;
import java.util.List;

import app.model.BenchmarkReturn;

/**
 * The Interface BenchmarkReturnDao.
 */
public interface BenchmarkReturnDao {
	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	List<BenchmarkReturn> getRecords();
	
	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the benchmark return
	 */
	BenchmarkReturn findByCode(String code);
	
	/**
	 * Find by date.
	 *
	 * @param date the date
	 * @return the benchmark return
	 */
	BenchmarkReturn findByCodeAndDate(String code, Date date);
}
