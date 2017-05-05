package com.github.mauricioaniche.ck;

public class Tester {
	public static void main(String[] args) {
		
		String path = "fixtures/bico/";
		
		CKReport report = new CK().calculate(path);
		
		for(CKNumber result : report.all()) {
			if(result.isError()) continue;
			
			System.out.println("NOMWMOP: " + result.getNomwmop());
		}
	}
}
