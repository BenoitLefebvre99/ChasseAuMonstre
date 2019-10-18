package ia;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import noyau.Partie;

class IAMoyenneTest {

	@BeforeClass
	public static void avantTest() {
		System.out.println("Debut de la serie de test");
	}
	@Before
	public void avantUnTest() {
		Partie.initInstance(2, 1);
		System.out.print("Debut du test : ");
	}
	@After
	public void apresUnTest() {
		System.out.println("--- fin d'un test ----------------------");
	}
	@AfterClass
	public static void apresTest() {
		System.out.println("Fin de la serie de test");
	}
	@Test
	public static void testChasseur() {
		System.out.println("Test IA chasseur moyen");
		IAMoyenne ia =new IAMoyenne();
		int [] tab=ia.chasseur(Partie.getInstance().plateau);
		boolean test=false;
		if (tab[0]<=Partie.getInstance().getNombreColonnes() && tab[1]<=Partie.getInstance().getNombreLignes()) test=true;
		assertTrue(test);
	}
	@Test
	public static void testMonstre() {
		System.out.println("Test IA monstre moyen");
		IAMoyenne ia =new IAMoyenne();
		int [] tab=ia.monstre(Partie.getInstance().plateau);
		boolean test1=false;
		boolean test2=false;
		if ((tab[0]==Partie.getInstance().monstre.getColonne()+1 || tab[0]==Partie.getInstance().monstre.getColonne()-1 )&& tab[1]==Partie.getInstance().monstre.getLigne()) test1=true;
		if (tab[0]==Partie.getInstance().monstre.getColonne() &&( tab[1]==Partie.getInstance().monstre.getLigne()+1 || tab[1]==Partie.getInstance().monstre.getLigne()-1)) test2=true;
		assertTrue(test1 || test2);
	}

}
