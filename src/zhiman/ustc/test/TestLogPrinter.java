package zhiman.ustc.test;

import zhiman.ustc.util.LogPrinter;

public class TestLogPrinter {

	public static void main(String[] args) {
		LogPrinter.printXmlLog("log.xml", "s-time","Test Print");
		LogPrinter.printXmlLog("log.xml","e-time", "hahahha");

	}

}
