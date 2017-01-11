package app.service.impl;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.service.FileOutputService;
import app.util.csv.CsvFileUtil;
import app.util.i18n.ResourceBundleUtil;

@Service
public class CsvFileOutputServiceImpl implements FileOutputService {
	private static final Logger logger = LoggerFactory.getLogger(CsvFileOutputServiceImpl.class);
	
	@Value("${output.filename}")
	private String outputFilename;
	
	@Autowired
	private CsvFileUtil csvFileUtil;
	
	@Autowired
	private ResourceBundleUtil resourceBundleUtil;
	
	/**
	 * Gets the output csv headers.
	 *
	 * @return the output csv headers
	 */
	private String[] getOutputCsvHeaders() {
		String headerStr = resourceBundleUtil.getMessage("output.headers");
		return headerStr.split(",");		
	}
	
	@Override
	public void writeToFile(String filename, List<List<Object>> objectList) {
		try {
			//"FundName", "Date", "Excess", "Outperformance", "Return"
			csvFileUtil.writeCsvFile(objectList, filename, getOutputCsvHeaders());
		} catch (IOException e) {
			logger.error("Error in writting CSV file" , e);
		}	
	}
	
}
