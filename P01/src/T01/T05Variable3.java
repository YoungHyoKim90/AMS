package T01;

public class T05Variable3 {

public static void main(String[] args) {
	int x = 3;
	int y = 14;
	
	System.out.print(x);
	System.out.print(".");
	System.out.print(y);
	System.out.println();
	
	String result = x + "." +y;
	System.out.println(result);
	
	double result2 = Double.parseDouble(result);
	
	System.out.println(result2);
}
}
