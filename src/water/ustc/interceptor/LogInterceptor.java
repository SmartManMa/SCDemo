package water.ustc.interceptor;

import java.text.DateFormat;
import java.util.Date;

import zhiman.ustc.util.LogPrinter;

/** 
  * @description LogInterceptor.java
  * @author Administrator
  * @date 2017/12/11
  * @version
  */
public class LogInterceptor {
	DateFormat dateFormat = DateFormat.getDateTimeInstance();
	 
	public void preAction() {
		String startTime = dateFormat.format(new Date());
		LogPrinter.printXmlLog("log.xml", "s-time",startTime);
		System.out.println("Action֮ǰִ��LogInterceptor preAction:"+startTime);
	}
	public void afterAction() {
		String endTime = dateFormat.format(new Date());
		LogPrinter.printXmlLog("log.xml", "e-time",endTime);
		System.out.println("Action֮��ִ��LogInterceptor afterAction:"+endTime);
	}
	
}
