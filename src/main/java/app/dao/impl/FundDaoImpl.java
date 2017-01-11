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
import app.dao.FundDao;
import app.model.Fund;

/**
 * The Class FundDaoImpl.
 */
@Repository("fundDao")
public class FundDaoImpl extends AbstractCsvDao<Fund>  implements FundDao {
	
	@Value("${fund.filename}")
	private String filePath;
	
	@Autowired
	private DozerBeanMapper beanMapper;
	
	/* (non-Javadoc)
	 * @see app.dao.FundDao#getRecords()
	 */
	@Cacheable(value="fundCache", key="#root.methodName")
	public List<Fund> getRecords() {
		List<Map<String, String>> list = readFromFile(filePath);
		return mapToModel(list);
	}

	/* (non-Javadoc)
	 * @see app.dao.CsvDao#mapToModel(java.util.List)
	 */
	@Override
	public List<Fund> mapToModel(List<Map<String, String>> list) {
		List<Fund> fundList = new ArrayList<>(list.size());
		
		list.forEach(e -> {
				Fund fund = new Fund();
				beanMapper.map(e, fund, "fund");
				fundList.add(fund);
			}
		);
		
		return fundList;
	}
	
	/* (non-Javadoc)
	 * @see app.dao.FundDao#findByCode(java.lang.String)
	 */
	@Override
	@Cacheable(value="fundCache", key="#code")
	public Fund findByCode(String code) {
		Fund fund = null;
		
		List<Fund> list = getRecords(); 
		
		for(Fund f : list) {
			if(code.equals(f.getFundCode())) {
				fund = f;
			}
		}
		return fund;
	}
}
