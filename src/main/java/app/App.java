package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.service.FundPerformanceService;


/**
 * The Class App.
 */
public class App {
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/app-context.xml");
		
		FundPerformanceService fundPerformanceService = (FundPerformanceService) context.getBean("fundPerformanceService");
		
		fundPerformanceService.createMonthlyPerformanceFile();
		
		((ConfigurableApplicationContext)context).close();
	}

}
