package app.dao;

import java.util.List;

import app.model.Benchmark;

/**
 * The Interface BenchmarkDao.
 */
public interface BenchmarkDao {
	/**
	 * Gets the records.
	 *
	 * @return the records
	 */
	List<Benchmark> getRecords();

	/**
	 * Find by code.
	 *
	 * @param code the code
	 * @return the benchmark
	 */
	Benchmark findByCode(String code);
}
