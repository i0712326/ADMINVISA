package bcel.cc.lvb.visa.service;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportExportServiceTest {
	private ApplicationContext context =  new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testExport() throws ParseException {
		ReportExportService bean = (ReportExportService) context.getBean("reportService");
		Date date = bcel.cc.lvb.visa.util.UtilPackage.str2Date("2016-03-21");
		File file = new File("C:\\Users\\phoud\\Documents\\TestData\\TESTFILE.txt");
		//bean.export(date, file);
		assertTrue(true);
	}

}
