package noyau;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import noyau.Case;

public class CaseTest {
	
	Case ca;
	
	@BeforeClass
	public static void avantTest() {
	System.out.println("D�but de la s�rie de test");
	}
	@Before
	public void avantUnTest() {
		System.out.print("D�but du test : ");
		ca = new Case(); 
	}
	@After
	public void apresUnTest() {
		System.out.println("--- fin d'un test ----------------------");
	}
	@AfterClass
	public static void apresTest() {
		System.out.println("Fin de la s�rie de test");
	}

	@Test
	public void testCase() {
		System.out.println("Test m�thode Case");
		assertFalse(ca.getShowed());
	}

	@Test
	public void testGetShowed() {
		System.out.println("Test m�thode getShowed");
		assertFalse(ca.getShowed());
	}

	@Test
	public void testChangeShowed() {
		System.out.println("Test m�thode changeShowed");
		assertFalse(ca.getShowed());
		ca.changeShowed();
		assertTrue(ca.getShowed());
		ca.changeShowed();
		assertTrue(ca.getShowed());
	}

	@Test
	public void testChangeVisiteTime() {
		System.out.println("Test m�thode changeVisiteTime");
		int tmp =ca.getVisiteTime();
		ca.changeVisiteTime();
		assertEquals(tmp+1, ca.getVisiteTime());;

	}


}
