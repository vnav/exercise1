package app.util.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.input.BOMInputStream;
import org.springframework.stereotype.Component;

/**
 * The Class CsvFileParser.
 */
@Component
public class CsvFileUtil {
	public static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * Read csv file.
	 *
	 * @param path the path
	 * @return the list
	 */
	public List<CSVRecord> readCsvFile(String path){
		List<CSVRecord> list = Collections.<CSVRecord>emptyList();
		try {
			File csvFile = new File(path);
			final Reader reader = new InputStreamReader(new BOMInputStream(new FileInputStream(csvFile)));
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader());
			
			list = parser.getRecords();
			parser.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Write csv file.
	 *
	 * @param list the list
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void writeCsvFile(List<List<Object>> list, String filename, String... header) throws IOException {
		File outputFile = new File(filename);
		outputFile.createNewFile();
		PrintWriter printWriter = new PrintWriter(outputFile);		
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR).withHeader(header);
		CSVPrinter csvPrinter = new CSVPrinter(printWriter, csvFileFormat);
			
		for(List<Object> record : list) {
			csvPrinter.printRecord(record);		    			
		}
		csvPrinter.flush();		
		csvPrinter.close();
		printWriter.close();
	}
		
}
