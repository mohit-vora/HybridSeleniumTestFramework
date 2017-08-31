package tryOutsGoHere;

import java.util.ArrayList;
import java.util.HashMap;

import java.text.DecimalFormat;

public class DecimalPoint {

public static void main(String[] args) {

double a=45235.678458435;

double b=6765375.6535436345;

double c=a+b;



System.out.println(c);

DecimalFormat dff=new DecimalFormat(".####");

System.out.println(a+"  "+dff.format(a));
System.out.println(b+"  "+dff.format(b));
System.out.println(c+"  "+dff.format(c));

}

}
