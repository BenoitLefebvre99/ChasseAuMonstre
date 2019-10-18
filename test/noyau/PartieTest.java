package noyau;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import noyau.Partie;
import utils.DirectionMonstre;
import utils.Position;

public class PartieTest {

	@BeforeClass
	public static void avantTest() {
		System.out.println("D�but de la s�rie de test");

	}
	@Before
	public void avantUnTest() {
		System.out.print("D�but du test : ");
		Partie.initInstance(2, 2);

	}
	@After
	public void apresUnTest() {
		System.out.println("--- fin d'un test ----------------------");
	}
	@AfterClass
	public static void apresTest() {
		System.out.println("Fin de la s�rie de test");
	}
	
	// test �limination du monstre
	
	@Test
	public void testCheckWinEtEnCours1() {
		assertFalse(Partie.getInstance().checkWin(true));
		assertTrue(Partie.getInstance().enCours());
		
		Partie.getInstance().tir(new Position(0, 0)); // tir sur chaque case du plateau (2x2)
		Partie.getInstance().tir(new Position(1, 1)); // car la case d'apparition du monstre est al�atoire
		Partie.getInstance().tir(new Position(1, 0)); // il faut donc essayer toutes les cases
		Partie.getInstance().tir(new Position(0, 1));
		
		assertTrue(Partie.getInstance().checkWin(true));
		assertFalse(Partie.getInstance().enCours());

	}
	
		//test exploration termin�e par le monstre
	
	@Test
	public void testCheckWinEtEnCours2() {
		assertFalse(Partie.getInstance().checkWin(false));
		assertTrue(Partie.getInstance().enCours());
		
		Partie.getInstance().deplacement(DirectionMonstre.DOWN); // exploration de toutes les cases
		Partie.getInstance().deplacement(DirectionMonstre.LEFT); // car la case d'apparition du monstre est al�atoire
		Partie.getInstance().deplacement(DirectionMonstre.UP); // il faut donc essayer toutes les cases
		
		assertTrue(Partie.getInstance().checkWin(false));
		assertFalse(Partie.getInstance().enCours());
	}	

	@Test
	public void testToutExplore() {
		assertFalse(Partie.getInstance().toutExplore());
		
		Partie.getInstance().deplacement(DirectionMonstre.DOWN); // exploration de toutes les cases
		Partie.getInstance().deplacement(DirectionMonstre.LEFT); // car la case d'apparition du monstre est al�atoire
		Partie.getInstance().deplacement(DirectionMonstre.UP); // il faut donc essayer toutes les cases
		
		assertTrue(Partie.getInstance().toutExplore());
	}

	@Test
	public void testNewTour() {
		int tmp =Partie.getInstance().getNombreTours();
		Partie.getInstance().newTour();
		assertEquals(tmp+1, Partie.getInstance().getNombreTours());
	}

}
