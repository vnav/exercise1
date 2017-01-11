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
import app.dao.FundReturnDao;
import app.model.FundReturn;

/**
 * The Class FundReturnDaoImpl.
 */
@Repository("fundReturnDao")
public class FundReturnDaoImpl extends AbstractCsvDao<FundReturn> implements FundReturnDao {
	
	@Value("${fundReturn.filename}")
	private String filePath;
		
	@Autowired
	private DozerBeanMapper beanMapper;
		
	/* (non-Javadoc)
	 * @see app.dao.FundReturnDao#getRecords()
	 */
	@Cacheable(value="fundReturnCache", key="#root.methodName")
	public List<FundReturn> getRecords() {
		List<Map<String, String>> list = readFromFile(filePath);
		return mapToModel(list);
	}
		
	/* (non-Javadoc)
	 * @see app.dao.CsvDao#mapToModel(java.util.List)
	 */
	@Override
	public List<FundReturn> mapToModel(List<Map<String, String>> list) {
		List<FundReturn> fundReturnList = new ArrayList<>(list.size());		
		list.forEach(e -> {			
				FundReturn fundReturn = new FundReturn();			
				beanMapper.map(e, fundReturn, "fundReturn");				
				fundReturnList.add(fundReturn);
			}
		);
		
		return fundReturnList;
	}
	
	/* (non-Javadoc)
	 * @see app.dao.FundReturnDao#findByCode(java.lang.String)
	 */
	@Override
	@Cacheable(value="fundReturnCache", key="#code")
	public FundReturn findByCode(String code) {
		List<FundReturn> list = getRecords();
		FundReturn fundReturn = null;
		
		for(FundReturn b : list) {
			if(code.equals(b.getFundCode())) {
				fundReturn = b;
			}
		}
		
		return fundReturn;
	}

	/* (non-Javadoc)
	 * @see app.dao.FundReturnDao#findByDate(java.util.Date)
	 */
	@Override
	@Cacheable(value="fundReturnCache", key="#date")
	public List<FundReturn> findByDate(Date date) {
		List<FundReturn> list = getRecords();
		List<FundReturn> fundReturnList = new ArrayList<>();
		
		for(FundReturn f : list) {
			if(date.equals(f.getDate())) {
				fundReturnList.add(f);
			}
		}
		
		return fundReturnList;
	}

}
