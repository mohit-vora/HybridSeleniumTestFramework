package tryOutsGoHere;

public class InheritThis {

	public static Object[][] args = null;
	
	public void setArgs(String args){
		
		int row=0;
		int col=0;
		
		row=args.split(";").length;
		col=args.split(";")[0].split(",").length;
		
		String[][] s1 = new String [row][col];
		
		for (int i=0;i<row;i++)
		{			
			for (int j=0;j<col;j++)
			{
				s1[i][j] = args.split(";")[i].split(",")[j];
			}
		}
		
		InheritThis.args=s1;
		
	}
	
	
    public Object[][] getArgs() {
        return args;
    }

}