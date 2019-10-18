package utils;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import noyau.Partie;
import utils.Position;

public class PositionTest {

	Position pos;

	@BeforeClass
	public static void avantTest() {
		System.out.println("D�but de la s�rie de test");
	}
	@Before
	public void avantUnTest() {
		System.out.print("D�but du test : ");
		Partie.initInstance(100, 100);
		pos = new Position(2,3);
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
	public void testPosition() {
		System.out.println("Test m�thode Position");
		assertEquals(pos.getLigne(), 2);
		assertEquals(pos.getColonne(), 3);
	}

	@Test
	public void testGetLigne() {
		System.out.println("Test m�thode getLigne");
		assertEquals(pos.getLigne(), 2);
	}
	
	@Test
	public void testGetColonne() {
		System.out.println("Test m�thode getColonne");
		assertEquals(pos.getColonne(), 3);
	}
	
	@Test
	public void testDecLigne() {
		System.out.println("Test m�thode desLigne");
		pos.decLigne();
		assertEquals(pos.getLigne(), 1);
	}
	
	@Test
	public void testDecColonne() {
		System.out.println("Test m�thode decColonne");
		pos.decColonne();
		assertEquals(pos.getColonne(), 2);
	}
	
	@Test
	public void testIncrLigne() {
		System.out.println("Test m�thode incrLigne");
		pos.incrLigne();
		assertEquals(pos.getLigne(), 3);
	}
	
	@Test
	public void testIncrColonne() {
		System.out.println("Test m�thode IncrColonne");
		pos.incrColonne();
		assertEquals(pos.getColonne(), 4);
	}
	
}
