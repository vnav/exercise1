package app.dao;

import java.util.List;
import java.util.Map;

/**
 * The Interface CsvDao.
 *
 * @param <T> the generic type
 */
public interface FileSourceDao<T> {
	
	/**
	 * Read from file.
	 *
	 * @param path the path
	 * @return the list
	 */
	List<Map<String, String>> readFromFile(String path);
	
	/**
	 * Map to model.
	 *
	 * @param list the list
	 * @return the list
	 */
	List<T> mapToModel(List<Map<String, String>> list);
}
