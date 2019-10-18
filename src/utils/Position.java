package utils;

import java.io.Serializable;

import noyau.Partie;

public class Position implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2736919616870593983L;
	private int ligne;
	private int colonne;
	
	/**
	 * Constructeur cr�ant une position.
	 * @param ligne la ligne souhait�e
	 * @param colonne la colonne souhait�e
	 */
	public Position(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	/**
	 * M�thode renvoyant l'abscisse.
	 * @return int
	 */
	public int getLigne() {
		return this.ligne;
	}
	/**
	 * M�thode renvoyant l'ordonn�e.
	 * @return int
	 */
	public int getColonne() {
		return this.colonne;
	}
	
	/**
	 * M�thode diminuant la ligne de 1
	 */
	public void decLigne() {
		this.ligne--;
		if(this.ligne < 0) this.ligne = Partie.getInstance().getNombreLignes() -1;
	}
	/**
	 * M�thode diminuant la colonne de 1
	 */
	public void decColonne() {
		this.colonne--;
		if(this.colonne < 0) this.colonne = Partie.getInstance().getNombreColonnes() -1;
	}
	/**
	 * M�thode augmentant la ligne de 1
	 */
	public void incrLigne() {
		this.ligne++;
		if(this.ligne == Partie.getInstance().getNombreLignes()) this.ligne =0;
	}
	/**
	 * M�thode augmentant la colonne de 1
	 */
	public void incrColonne() {
		this.colonne++;
		if(this.colonne == Partie.getInstance().getNombreColonnes()) this.colonne =0;
	}
	
	/**
	 * M�thode de comparaison equals renvoyant un boolean
	*/
	public boolean equals(Position p) {
		if(this.ligne == p.getLigne() && this.colonne == p.getColonne()) return true;
		else return false;
	}
}
