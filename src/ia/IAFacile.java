package ia;

import java.util.Random;

import noyau.*;

public class IAFacile implements iIA{
	public  int[] chasseur(Map map) {
		return new int[] {(new Random()).nextInt(map.getNB_COLONNES()),(new Random()).nextInt(map.getNB_LIGNES())};
	}
	
	public int[] monstre(Map map) {
		int tmp=(new Random()).nextInt(3);
		if (tmp==0) return new int[] {(Partie.getInstance().monstre.getColonne()+1)%Partie.getInstance().getNombreColonnes(),Partie.getInstance().monstre.getLigne()};
		if (tmp==1) return new int[] {(Partie.getInstance().monstre.getColonne()-1)%Partie.getInstance().getNombreColonnes(),Partie.getInstance().monstre.getLigne()};
		if (tmp==2) return new int[] {Partie.getInstance().monstre.getColonne(),(Partie.getInstance().monstre.getLigne()+1)%Partie.getInstance().getNombreLignes()};
		else return new int[] {Partie.getInstance().monstre.getColonne(),(Partie.getInstance().monstre.getLigne()-1)%Partie.getInstance().getNombreLignes()};
	}
}
