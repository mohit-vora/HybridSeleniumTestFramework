package tryOutsGoHere;

class TataGreyCar implements Vehicle{
	String company,color;
	
	public TataGreyCar(){
		company = "Tata";
		color = "Grey";
	}
	public void whichColor() {
		System.out.println(color);
	}
	public void whichCompany() {
		System.out.println(company);
	}
}

interface Vehicle{
	public void whichColor();
	public void whichCompany();
}

public class interfaceReference{
	public static void main(String[] args){
		Vehicle car = new TataGreyCar();
		car.whichColor();
		car.whichCompany();
	}
}