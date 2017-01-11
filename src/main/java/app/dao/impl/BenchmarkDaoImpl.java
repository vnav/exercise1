package app.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import app.dao.AbstractCsvDao;
import app.dao.BenchmarkDao;
import app.model.Benchmark;

/**
 * The Class BenchmarkDaoImpl.
 */
@Repository("benchmarkDao")
public class BenchmarkDaoImpl extends AbstractCsvDao<Benchmark> implements BenchmarkDao {
		
	@Value("${benchmark.filename}")
	private String filePath;

	@Autowired
	private DozerBeanMapper beanMapper;
	
	/* (non-Javadoc)
	 * @see app.dao.BenchmarkDao#getRecords()
	 */
	@Cacheable(value="benchmarkCache", key="#root.methodName")
	public List<Benchmark> getRecords() {
		List<Map<String, String>> list = readFromFile(filePath);
		return mapToModel(list);
	}
	
	/* (non-Javadoc)
	 * @see app.dao.CsvDao#mapToModel(java.util.List)
	 */
	@Override
	public List<Benchmark> mapToModel(List<Map<String, String>> list) {
		List<Benchmark> banchmarkList = new ArrayList<>(list.size());
		
		list.forEach(e -> {
				Benchmark benchmark = new Benchmark();
				beanMapper.map(e, benchmark, "benchmark");
				banchmarkList.add(benchmark);
			}
		);
		
		return banchmarkList;
	}
	
	/* (non-Javadoc)
	 * @see app.dao.BenchmarkDao#findByCode(java.lang.String)
	 */
	@Override
	@Cacheable(value="benchmarkCache", key="#code")
	public Benchmark findByCode(String code) {
		Benchmark benchmark = null;
		
		List<Benchmark> list = getRecords(); 
		
		for(Benchmark b : list) {
			if(code.equals(b.getBenchmarkCode())) {
				benchmark = b;
			}
		}
		return benchmark;
	}
	
}
