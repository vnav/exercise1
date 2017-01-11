package app.service;

import java.util.List;

/**
 * The Interface FileOutputService.
 */
public interface FileOutputService {
	
	/**
	 * Write to file.
	 *
	 * @param filename the filename
	 * @param objectList the object list
	 */
	void writeToFile(String filename, List<List<Object>> objectList);
}
