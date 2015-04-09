package local.service;

import java.util.Date;

public class Util {

	public static void log(String msg)
	{
		System.err.println("================================================================================");
		System.err.println(new Date());
		System.err.println(msg);
		System.err.println("================================================================================");
	}
}
