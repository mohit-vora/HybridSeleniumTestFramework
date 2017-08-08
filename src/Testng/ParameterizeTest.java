package Testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizeTest {
  
  @Parameters ({"Suite level param"})
  @Test  
  public void firstTest(String Param) {
	  System.out.println(Param);
  }
  
  @Parameters ({"Test level param"})
  @Test
  public void SecondTest(String Param) {
	  System.out.println(Param);
  }
  
  
  @Parameters ({"Suite level param","Test level param"})
  @Test
  public void ThirdTest(String ParamSuite,String Testparam) {
	  System.out.println(ParamSuite + "------" + Testparam);
  }
}

