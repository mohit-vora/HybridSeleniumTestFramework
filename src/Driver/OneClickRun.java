package Driver;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class OneClickRun {

	public static void main(String[] args) {

		runTestNG();
	}

	public static void runTestNG() {
		TestNG runner = new TestNG();
		List<String> suitefiles = new ArrayList<String>();
		suitefiles.add(System.getProperty("user.dir") + "\\testng.xml");
		runner.setTestSuites(suitefiles);
		runner.run();

	}
}
