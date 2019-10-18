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
	System.out.println("Début de la série de test");
	}
	@Before
	public void avantUnTest() {
		System.out.print("Début du test : ");
		ca = new Case(); 
	}
	@After
	public void apresUnTest() {
		System.out.println("--- fin d'un test ----------------------");
	}
	@AfterClass
	public static void apresTest() {
		System.out.println("Fin de la série de test");
	}

	@Test
	public void testCase() {
		System.out.println("Test méthode Case");
		assertFalse(ca.getShowed());
	}

	@Test
	public void testGetShowed() {
		System.out.println("Test méthode getShowed");
		assertFalse(ca.getShowed());
	}

	@Test
	public void testChangeShowed() {
		System.out.println("Test méthode changeShowed");
		assertFalse(ca.getShowed());
		ca.changeShowed();
		assertTrue(ca.getShowed());
		ca.changeShowed();
		assertTrue(ca.getShowed());
	}

	@Test
	public void testChangeVisiteTime() {
		System.out.println("Test méthode changeVisiteTime");
		int tmp =ca.getVisiteTime();
		ca.changeVisiteTime();
		assertEquals(tmp+1, ca.getVisiteTime());;

	}


}
