package bcel.cc.lvb.visa.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplicationContext {
	private static ApplicationContext context;
	static{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public static Object getBean(String bean){
		return context.getBean(bean);
	}
}
