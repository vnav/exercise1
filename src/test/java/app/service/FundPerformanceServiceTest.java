package app.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-context.xml"})
public class FundPerformanceServiceTest {

	@Autowired
	private FundPerformanceService fundPerformanceService;
	
	@Test
	public void testComputeExcess() {
        BigDecimal result = fundPerformanceService.computeExcess(10.5, 2.2);
        assertEquals(8.3, result.doubleValue(), 0);
	}

	@Test
	public void testGetPerformanceMessage() {
        String result = fundPerformanceService.getPerformanceMessage(2);
        assertEquals("out performed", result);
        
        result = fundPerformanceService.getPerformanceMessage(-2);
        assertEquals("under performed", result);
	}
}
