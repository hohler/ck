package dit;

public class NONC {
	
	public void a() {
		Object a;
		int i;
		
		if(a == null) {}
		
		if(a != null) {}
		
		if(null == a && i == 0) {}
		
		if(null != a || i == 0) {}
		
		if(a == null && (i == 1 || i == 2)) {}
		
		if(true && (null == a)) {}
		
		if((null == a && null != a) || null == a) {}
		
		if((null == a && null != a) && null == a) {}
		
		if((null == a && null != a) || (null == a) && (a != null)) {}
		
		if((null == a && null != a) || (null == a) && a != null) {}
		
		if(i == 0) {
			Object b;
			if(b == null) {}
			
			if(b != null) {}
			
			if(null == b) {}
			
			if(null != b) {}
		}
	}
}