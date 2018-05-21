package utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderHelper extends BaseClass{
	
	/*
	 * this method annotates testNG data provider which returns two dimensional object array
	 * It returns the test data object array */
    @DataProvider(name="dp")
    public static Object[][] dptryout(Method method){  	
		testIterationNumber=0;
    	
    	Object[][] returnObject = new Object[][]{};
    	
    	try
    	{
    		if(preExecutionCheck){
    			Object[][] obj = TestCaseSelector.getYesTestDetails(method.getName());
    			if (DataProviderHelper.checkDataProviderSanity(obj, method)){
    				returnObject = obj;
    			}
        	}
    	}
    	catch(Exception e){
    		ReportLogger.preExecutionFail(e);   		
    	}
    		
    	return returnObject; 	
    }  
	
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
