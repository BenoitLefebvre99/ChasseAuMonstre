package noyau;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import noyau.Partie;
import utils.Position;

public class ChasseurTest {

	@BeforeClass
	public static void avantTest() {
		System.out.println("D�but de la s�rie de test");
	}
	@Before
	public void avantUnTest() {
		Partie.initInstance(100, 100);
		System.out.print("D�but du test : ");
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
	public void testChasseur() {
		System.out.println("Test m�thode chasseur");
		assertFalse(Partie.getInstance().checkWin(true));
	}
	
	@Test
	public void testTir() {
		System.out.println("Test m�thode tir");
		int y = 5;
		int x = 5;
		Partie.getInstance().tir(new Position(x, y));
		if(Partie.getInstance().monstre.getColonne() == x && Partie.getInstance().monstre.getLigne() == y) {
			assertTrue(Partie.getInstance().checkWin(true));
		} else {
			assertFalse(Partie.getInstance().checkWin(true));
		}
	}

	@Test
	public void testAGagne() {
		System.out.println("Test m�thode aGagne");
		assertFalse(Partie.getInstance().checkWin(true));
	}


}
