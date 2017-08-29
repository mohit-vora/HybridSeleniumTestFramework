package tryOutsGoHere;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class dummy {

	@Factory(dataProvider="dp")
	public Object[] createInstances(String data) {
		return new Object[] {new AllTestCasesGoHere(data)};
	}
	
	@DataProvider(name="dp")
	public Object[][] getmeData()
	{
		Object[][] dataArray = {
				{"TEST1"},
				{"TEST2"},
				{"TEST3"}
		};
		return dataArray;
		
	}
}
