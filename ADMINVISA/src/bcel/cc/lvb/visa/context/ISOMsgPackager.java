package bcel.cc.lvb.visa.context;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.packager.GenericPackager;

public class ISOMsgPackager {
	private static Logger logger = Logger.getLogger(ISOMsgPackager.class);
	private static ISOPackager packager;
	private static InputStream in;
	static{
		try {
			 in = ISOMsgPackager.class.getResourceAsStream("/iso93ascii.xml");
			 packager = new GenericPackager(in);
		} catch (ISOException e) {
			logger.debug("Exception occured why try to build generic packager",e);
		}
	}
	
	public static ISOPackager getPackager(){
		return packager;
	}
}
