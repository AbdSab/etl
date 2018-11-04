package etl.elements;

public class MouseElement {
	public static int Element = 0;
	public static String name = "";
	public static int function = 0;
	public static Element src;
	
	public static void take(Element e) {
		if(e != null) {
			name = e.getName();
			function = 1;
			src = e;
		}else {
			name = "Nothing";
			function = 0;
			src = null;
		}
	}
	
	public static void newElmt(int element, String n) {
		name = n;
		Element = element;
	}
}
