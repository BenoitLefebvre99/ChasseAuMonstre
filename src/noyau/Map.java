package noyau;

import java.io.Serializable;

import utils.Position;

public class Map implements Serializable {
	private static final long serialVersionUID = -1341946484617748301L;
	private final int NB_LIGNES;
	private final int NB_COLONNES;
	private  Case[][] plateau;
	
	/**
	 * Constructeur cr�ant la Map du nombre de lignes et de colonnes fournis en param�tres.
	 * @param nbLignes
	 * @param nbColonnes
	 */
	public Map(int nbLignes, int nbColonnes) {
		this.NB_LIGNES = nbLignes;
		this.NB_COLONNES = nbColonnes;
		this.plateau = new Case[NB_LIGNES][NB_COLONNES];
		initialisation();
	}
	/**
	 * M�thode utilis�e par le constructeur pour rendre le plateau utilisable
	 */
	private void initialisation() {
		for(int ligne=0; ligne<this.NB_LIGNES; ligne++) {
			for(int colonne=0; colonne<this.NB_COLONNES; colonne++) {
				this.plateau[ligne][colonne]= new Case();
			}
		}
	}
	
	/**
	 * M�thode renvoyant la N�me case.
	 * @param ligne La ligne souhait�e
	 * @param colonne La colonne souhait�e
	 * @return la case souhait�e
	 */
	public Case getCaseN(int ligne, int colonne) { return this.plateau[ligne][colonne];}
	
	/**
	 * M�thode renvoyant la Map compos�e de Cases sous forme de textes (Format Chasseur).
	 * @return String
	 */
	public String toString() {
		String res = "\t\t";
		for(int abscisse=0;abscisse<this.NB_COLONNES;abscisse++) {
			res=res+abscisse+"\t";
		}
		res=res+"\n\n";
		for(int ligne = 0; ligne<this.NB_LIGNES; ligne++) {
			res=res+ligne+"\t\t";
			for(int colonne = 0; colonne<this.NB_COLONNES; colonne++) {
				if(!this.plateau[ligne][colonne].getShowed()) res += "cach�"+"\t";
				else res += this.plateau[ligne][colonne].getVisiteTime() +"\t";
			}
			res+="\n";
		}
		return res;
	}
	
	/**
	 * M�thode renvoyant la Map compos�e de cases sous forme de textes (Format Monstre).
	 * @param a abscisse
	 * @param b ordonn�e
	 * @return String
	 */
	public String toString(int a, int b ) {
		String res = "\t\t";
		for(int abscisse=0;abscisse<this.NB_COLONNES;abscisse++) {
			res=res+abscisse+"\t";
		}
		res=res+"\n\n";
		for(int ligne = 0; ligne<this.NB_LIGNES; ligne++) {
			res=res+ligne+"\t\t";
			for(int colonne = 0; colonne<this.NB_COLONNES; colonne++) {
				if(ligne == a && colonne == b) res += "M\t";
				else if(this.plateau[ligne][colonne].getVisiteTime()==-1) res += "."+"\t";
				else res +="X\t";
			}
			res+="\n";
		}
		return res;
	}
	/**
	 * M�thode activant la vue de la case aux param�tres donn�es.
	 * @param ligne La ligne de la case souhait�e
	 * @param colonne La colonne de la case souhait�e
	 */
	public void changeShowed(int ligne, int colonne) {
		this.plateau[ligne][colonne].changeShowed();
	}
	/**
	 * M�thode renvoyant le nombre de lignes de la Map
	 * @return int 
	 */
	public int getNB_LIGNES() {
		return NB_LIGNES;
	}
	/**
	 * M�thode renvoyant le nombre de colonnes de la Map
	 * @return int
	 */
	public int getNB_COLONNES() {
		return NB_COLONNES;
	}
	/**
	 * M�thode actualisant le tour auquelle la case p � �t� visit�e
	 * @param p La position de la case souhait�e
	 */
	public void changeVisiteTime(Position p) {
		this.plateau[p.getLigne()][p.getColonne()].changeVisiteTime();
	}

	public String getVisitTime(int x, int y) {return ""+plateau[y][x].getVisiteTime();}
}
