package tryOutsGoHere;



public class dummy {
	
	public static void main(String []args){
		
		String data = "one,two,three; ";
		
		int row = data.split(";").length;
		int counter=0;
		for (int i=0;i<data.length();i++)
		{
			if (data.charAt(i)==';')
				counter++;
		}


		if (counter==row-1)
			System.out.println("everything is good");
		else
			System.out.println("check test data, there is an extra ; somewhere");
	}
}
