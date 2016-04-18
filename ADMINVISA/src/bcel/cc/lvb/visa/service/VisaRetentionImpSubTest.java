package bcel.cc.lvb.visa.service;

import java.io.File;
import java.sql.Date;
import java.text.ParseException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bcel.cc.lvb.visa.util.UtilPackage;

public class VisaRetentionImpSubTest {
	private static ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	@Test
	public void testRetend() throws ParseException {
		VisaRetention bean = (VisaRetention) context.getBean("visaRetentionSub");
		File file = new File("C:\\Users\\phoud\\Documents\\TestData\\TESTFILE.txt");
		Date date = UtilPackage.str2Date("2016-03-21");
		bean.retend(date, file);
	}

}
