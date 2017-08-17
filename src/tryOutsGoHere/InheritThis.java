package tryOutsGoHere;

public class InheritThis {
	
	public static Object[][] args = null;
	
	public void setArgs(String args){
		String[] s1 = args.split(";");
		System.out.println(0+s1[0]);
		System.out.println(1+s1[1]);
		System.out.println(s1.length);
		
		for (int i=0;i<s1.length;i++)
		{
			
			System.out.println(i+s1[i]);
			InheritThis.args[0][i] = s1[i];
		}
		
		
	}

	public Object[] getArgs()
	{
		return args;
	}
	
}
