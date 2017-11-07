package utils;

import java.lang.reflect.Method;

public class DataProviderHelper extends BaseClass{
	
	/*Checks whether the Object Array passed by dataprovider and no of parameter needed by @test method are equal*/
	static boolean checkDataProviderSanity(Object[][] obj, Method method){
		
		boolean flag = true;
		
		for (int i=0;i<obj.length;i++){
			if (obj[i].length!=method.getParameterCount()){
				flag=false;
				test = extent.createTest(method.getName()+": "+getCurrentIterationTestData(method));
				ReportLogger.skip("skipped this Test");
				ReportLogger.skip("No of arguments taken by test case ["+method.getName()+"] are not matching test method parameters");
				break;		
			}
		}	
		return flag;
	}
	/*Fetches the data set used for each iteration*/
	static String getCurrentIterationTestData(Method method){
		testIterationNumber++;
		Object[] argumentObjectArray = TestCaseSelector.getYesTestDetails(method.getName())[testIterationNumber-1];
		String testArguments = "";
		for (int objectIndex=0;objectIndex<argumentObjectArray.length;objectIndex++){
			testArguments=testArguments+argumentObjectArray[objectIndex]+",";
		}
		return testArguments.substring(0, testArguments.length()-1);
	}

}
