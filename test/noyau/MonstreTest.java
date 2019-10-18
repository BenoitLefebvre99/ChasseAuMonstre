package noyau;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import noyau.Monstre;
import noyau.Partie;
import utils.DirectionMonstre;
import utils.Position;

public class MonstreTest {
	Monstre m1;
	Position pos; 
	
	@BeforeClass
	public static void avantTest() {
		System.out.println("D�but de la s�rie de test");
	}
	@Before
	public void avantUnTest() {
		System.out.print("D�but du test : ");
		Partie.initInstance(100, 100);
		pos = new Position(5,5);
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
	public void testMonstre() {
		System.out.println("Test constructeur Monstre");
		assertFalse(Partie.getInstance().checkWin(false));
	}
	
	@Test
	public void testAGagne() {
		System.out.println("Test m�thode aGagne");
		assertFalse(Partie.getInstance().checkWin(true));
	}
	
	@Test
	public void testDeplacement() {
		System.out.println("Test m�thode deplacement");
		int save = Partie.getInstance().monstre.getLigne();
		DirectionMonstre direction = DirectionMonstre.UP;
		Partie.getInstance().deplacement(direction);
		assertEquals(Partie.getInstance().monstre.getLigne(), save-1);
		
		save = Partie.getInstance().monstre.getLigne();
		direction = DirectionMonstre.DOWN;
		Partie.getInstance().deplacement(direction);
		assertEquals(Partie.getInstance().monstre.getLigne(), save+1);
		
		save = Partie.getInstance().monstre.getColonne();
		direction = DirectionMonstre.LEFT;
		Partie.getInstance().deplacement(direction);
		assertEquals(Partie.getInstance().monstre.getColonne(), save-1);
		
		save = Partie.getInstance().monstre.getColonne();
		direction = DirectionMonstre.RIGHT;
		Partie.getInstance().deplacement(direction);
		assertEquals(Partie.getInstance().monstre.getColonne(), save+1);
	}
	
	@Test
	public void testUp() {
		System.out.println("Test m�thode up");
		Position pos = new Position(5, 5);
		int save = pos.getLigne();
		pos.incrLigne();
		assertEquals(pos.getLigne(), save+1);
	}
	
	@Test
	public void testDown() {
		System.out.println("Test m�thode down");
		Position pos = new Position(5, 5);
		int save = pos.getLigne();
		pos.decLigne();
		assertEquals(pos.getLigne(), save-1);
	}
	
	@Test
	public void testLeft() {
		System.out.println("Test m�thode left");
		Position pos = new Position(5, 5);
		int save = pos.getColonne();
		pos.decColonne();
		assertEquals(pos.getColonne(), save-1);
	}
	
	@Test
	public void testRight() {
		System.out.println("Test m�thode right");
		Position pos = new Position(5, 5);
		int save = pos.getColonne();
		pos.incrColonne();
		assertEquals(pos.getColonne(), save+1);
	}

}
