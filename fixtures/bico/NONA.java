package bico;

public class NONA {
	
	private void a() {
		Object a;
		a = null;
		
		Object b = null;
		
		//b = (a == null ? null : a);
		
		a = b instanceof Object ? null : b;
		
		a = b;
	}
}