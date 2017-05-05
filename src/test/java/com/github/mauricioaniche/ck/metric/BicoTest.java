package com.github.mauricioaniche.ck.metric;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.mauricioaniche.ck.CK;
import com.github.mauricioaniche.ck.CKNumber;
import com.github.mauricioaniche.ck.CKReport;

public class BicoTest extends BaseTest {

	private static CKReport report;
	
	@BeforeClass
	public static void setUp() {
		report = new CK().calculate(fixturesDir() + "/bico");
	}
	
	@Test
	public void countNumberOfCatchBlocks() {
		CKNumber a = report.getByClassName("bico.NOCB");
		Assert.assertEquals(4, a.getNocb());
	}
	
	@Test
	public void countNumberOfNullChecks() {
		CKNumber a = report.getByClassName("bico.NONC");
		Assert.assertEquals(24, a.getNonc());
	}
	
	@Test
	public void countNumberOfNullAssignments() {
		CKNumber a = report.getByClassName("bico.NONA");
		Assert.assertEquals(3,  a.getNona());
	}
	
	@Test
	public void countNumberOfMethodsWithMoreThanOneParameter() {
		CKNumber a = report.getByClassName("bico.NOMWMOP");
		Assert.assertEquals(5, a.getNomwmop());
	}
}
