package app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import app.dao.BenchmarkDao;
import app.dao.BenchmarkReturnDao;
import app.dao.FundDao;
import app.dao.FundReturnDao;
import app.model.Benchmark;
import app.model.BenchmarkReturn;
import app.model.Fund;
import app.model.FundReturn;
import app.model.MonthlyOutperformance;
import app.util.enums.PerformanceMessageEnum;
import app.util.i18n.ResourceBundleUtil;

/**
 * The Class FundPerformanceCalculator.
 */
@Service
public class FundPerformanceService {	
	@Value("${output.filename}")
	private String outputFilename;
	
	@Autowired
	private ResourceBundleUtil resourceBundleUtil;
		
	@Autowired
	private FundDao fundDao; 

	@Autowired
	private FundReturnDao fundReturnDao; 

	@Autowired
	private BenchmarkDao benchmarkDao; 
	
	@Autowired
	private BenchmarkReturnDao benchmarkReturnDao; 
	
	@Autowired
	private FileOutputService fileOutputService;
	
	/** The csv path. */
	@Value("${fundDate.format}")
	private String dateFormat;
	
	/** The SimpleDateFormat. */
	private DateFormat df;
	
	@PostConstruct
	public void init() {
		df = new SimpleDateFormat(dateFormat);
	}

	/**
	 * Creates the monthly performance.
	 */
	public void createMonthlyPerformanceFile() {
		Map<Date, Map<Double, List<MonthlyOutperformance>>> mainMap = generateMonthlyPerformance();
		fileOutputService.writeToFile(outputFilename, convertToList(mainMap));
	}
		
	/**
	 * Compute excess.
	 *
	 * @param fundReturn the fund return
	 * @param benchmarkReturn the benchmark return
	 * @return the big decimal
	 */
	BigDecimal computeExcess(double fundReturn, double benchmarkReturn) {;
		BigDecimal excess = new BigDecimal(fundReturn)
								.subtract(new BigDecimal(benchmarkReturn))
								.setScale(2, RoundingMode.HALF_DOWN);
		return excess;		
	}
		
	/**
	 * Gets the performance message.
	 *
	 * @param excess the excess
	 * @return the performance message
	 */
	String getPerformanceMessage(double value) {
		String message = "";
		if(value < -1) {
			message = PerformanceMessageEnum.UNDER_PERFORM.getMessage();
		} else if(value > 1) {
			message = PerformanceMessageEnum.OUT_PERFORM.getMessage();
		} else {
			message = PerformanceMessageEnum.EQUAL.getMessage();
		}
		return resourceBundleUtil.getMessage("monthly.performance." + message);
	}
	
	/**
	 * Generate monthly performance.
	 *
	 * @return the map
	 */
	private Map<Date, Map<Double, List<MonthlyOutperformance>>> generateMonthlyPerformance() {
		List<FundReturn> fundReturnList = fundReturnDao.getRecords();

		Map<Date, Map<Double, List<MonthlyOutperformance>>> mainMap = new TreeMap<>(new DescendingComparator<Date>());
		
		for(FundReturn fundReturn : fundReturnList) {	
			Fund fund = fundDao.findByCode(fundReturn.getFundCode());
			Benchmark benchmark = benchmarkDao.findByCode(fund.getBenchmarkCode());
			BenchmarkReturn benchmarkReturn = benchmarkReturnDao.findByCodeAndDate(benchmark.getBenchmarkCode(), fundReturn.getDate());
			
			MonthlyOutperformance monthlyOutperformance = new MonthlyOutperformance();
			monthlyOutperformance.setFundName(fund.getName());
			monthlyOutperformance.setDate(benchmarkReturn.getDate());
			BigDecimal excess = computeExcess(fundReturn.getReturnValue(), benchmarkReturn.getReturnValue());
			monthlyOutperformance.setExcess(excess.doubleValue());
			monthlyOutperformance.setOutperformance(getPerformanceMessage(excess.doubleValue()));
			monthlyOutperformance.setReturnValue(new BigDecimal(fundReturn.getReturnValue()).setScale(2, RoundingMode.HALF_DOWN).doubleValue());
			
			
			Map<Double, List<MonthlyOutperformance>> innerMap = mainMap.get(benchmarkReturn.getDate());
			if(innerMap == null) {
				innerMap = new TreeMap<>(new DescendingComparator<Double>());
				List<MonthlyOutperformance> list = new ArrayList<>();
				list.add(monthlyOutperformance);
				innerMap.put(monthlyOutperformance.getReturnValue(), list);
				mainMap.put(monthlyOutperformance.getDate(), innerMap);
			} else {
				List<MonthlyOutperformance> list = innerMap.get(monthlyOutperformance.getReturnValue());
				if(list == null) {
					list = new ArrayList<>();
					list.add(monthlyOutperformance);
					innerMap.put(monthlyOutperformance.getReturnValue(), list);
				} else if(!list.contains(monthlyOutperformance)) {
					list.add(monthlyOutperformance);
				}
			}
		};
		return mainMap;
	}
	
	/**
	 * Convert to list.
	 *
	 * @param map the map
	 * @return the list
	 */
	private List<List<Object>> convertToList(Map<Date, Map<Double, List<MonthlyOutperformance>>> map) {
		List<List<Object>> returnList = new ArrayList<>();
		
		for(Map<Double, List<MonthlyOutperformance>> innerMap : map.values()) {
			int index = 1;
			for(List<MonthlyOutperformance> list : innerMap.values()) {
				for(MonthlyOutperformance monthlyOutperformance : list) {
					List<Object> innerList = new ArrayList<>();			
					innerList.add(monthlyOutperformance.getFundName());
					innerList.add(df.format(monthlyOutperformance.getDate()));
					innerList.add(monthlyOutperformance.getExcess());
					innerList.add(monthlyOutperformance.getOutperformance());
					innerList.add(monthlyOutperformance.getReturnValue());
					innerList.add(index++);
					returnList.add(innerList);										
				}
			}
		}
		return returnList;
	}
	
	/**
	 * The Class DescendingComparator.
	 *
	 * @param <T> the generic type
	 */
	private class DescendingComparator<T extends Comparable<T>> implements Comparator<T> {
		@Override
		public int compare(T o1, T o2) {
			return o2.compareTo(o1);
		}
	}
}
