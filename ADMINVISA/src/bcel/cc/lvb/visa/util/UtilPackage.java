package bcel.cc.lvb.visa.util;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilPackage {
	public static Date str2date(String str, String format) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		java.util.Date date = dateFormat.parse(str);
		return new Date(date.getTime());
	}
	
	public static String date2Str(Date date){
		SimpleDateFormat format = new SimpleDateFormat("MMdd");
		String strDate = format.format(date);
		return strDate;
	}
	
	public static Date str2Date(String str) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = format.parse(str);
		return new Date(date.getTime());
	}

	public static String date2Str(Date date, String form) {
		SimpleDateFormat format = new SimpleDateFormat(form);
		return format.format(date);
	}

	public static Object intFormat(int number) {
		DecimalFormat format = new DecimalFormat("##0.00");
		return format.format(number);
	}

	public static String numFormat(double amount) {
		DecimalFormat format = new DecimalFormat("#,##0.00");
		return format.format(amount);
	}
}
