package app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;

import app.util.csv.CsvFileUtil;

/**
 * The Class AbstractCsvDao.
 *
 * @param <T> the generic type
 */
public abstract class AbstractCsvDao<T> implements FileSourceDao<T> {

	@Autowired
	private CsvFileUtil csvFileUtil;
		
	/* (non-Javadoc)
	 * @see app.dao.FileSourceDao#readFromFile(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> readFromFile(String path) {
		List<Map<String, String>> list = new ArrayList<>();
		List<CSVRecord> csvList = csvFileUtil.readCsvFile(path);
		
		csvList.forEach(csvRecord -> list.add(csvRecord.toMap()));
		
		return list;
	}	
}
