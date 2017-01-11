package app.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import app.dao.AbstractCsvDao;
import app.dao.BenchmarkReturnDao;
import app.model.BenchmarkReturn;

/**
 * The Class BenhmarkReturnDaoImpl.
 */
@Repository("benchmarkReturnDao")
public class BenchmarkReturnDaoImpl extends AbstractCsvDao<BenchmarkReturn> implements BenchmarkReturnDao {
	
	@Value("${benchmarkReturn.filename}")
	private String filePath;
	
	@Autowired
	private DozerBeanMapper beanMapper;
	
	/* (non-Javadoc)
	 * @see app.dao.BenchmarkReturnDao#getRecords()
	 */
	@Cacheable(value="benchmarkReturnCache", key="#root.methodName")
	public List<BenchmarkReturn> getRecords() {
		List<Map<String, String>> list = readFromFile(filePath);
		return mapToModel(list);
	}
	

	/* (non-Javadoc)
	 * @see app.dao.CsvDao#mapToModel(java.util.List)
	 */
	@Override
	public List<BenchmarkReturn> mapToModel(List<Map<String, String>> list) {
		List<BenchmarkReturn> benchmarkReturnList = new ArrayList<>(list.size());
		
		list.forEach(e -> {
				BenchmarkReturn benchmarkReturn = new BenchmarkReturn();				
				beanMapper.map(e, benchmarkReturn, "benchmarkReturn");									
				benchmarkReturnList.add(benchmarkReturn);
			}
		);
		
		return benchmarkReturnList;
	}

	/* (non-Javadoc)
	 * @see app.dao.BenchmarkReturnDao#findByCode(java.lang.String)
	 */
	@Override
	@Cacheable(value="benchmarkReturnCache", key="#code")
	public BenchmarkReturn findByCode(String code) {
		List<BenchmarkReturn> list = getRecords();
		BenchmarkReturn benchmarkReturn = null;
		
		for(BenchmarkReturn b : list) {
			if(code.equals(b.getBenchmarkCode())) {
				benchmarkReturn = b;
			}
		}
		
		return benchmarkReturn;
	}
	
	/* (non-Javadoc)
	 * @see app.dao.BenchmarkReturnDao#findByDate(java.util.Date)
	 */
	@Override
	@Cacheable(value="benchmarkReturnCache", key="{#code, #date}")
	public BenchmarkReturn findByCodeAndDate(String code, Date date) {
		List<BenchmarkReturn> list = getRecords();
		BenchmarkReturn benchmarkReturn = null;
		
		for(BenchmarkReturn b : list) {
			if(code.equals(b.getBenchmarkCode()) && date.equals(b.getDate())) {
				benchmarkReturn = b;
			}
		}
		
		return benchmarkReturn;
	}
}
