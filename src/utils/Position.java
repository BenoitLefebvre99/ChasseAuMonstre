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
	 * Constructeur créant une position.
	 * @param ligne la ligne souhaitée
	 * @param colonne la colonne souhaitée
	 */
	public Position(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	/**
	 * Méthode renvoyant l'abscisse.
	 * @return int
	 */
	public int getLigne() {
		return this.ligne;
	}
	/**
	 * Méthode renvoyant l'ordonnée.
	 * @return int
	 */
	public int getColonne() {
		return this.colonne;
	}
	
	/**
	 * Méthode diminuant la ligne de 1
	 */
	public void decLigne() {
		this.ligne--;
		if(this.ligne < 0) this.ligne = Partie.getInstance().getNombreLignes() -1;
	}
	/**
	 * Méthode diminuant la colonne de 1
	 */
	public void decColonne() {
		this.colonne--;
		if(this.colonne < 0) this.colonne = Partie.getInstance().getNombreColonnes() -1;
	}
	/**
	 * Méthode augmentant la ligne de 1
	 */
	public void incrLigne() {
		this.ligne++;
		if(this.ligne == Partie.getInstance().getNombreLignes()) this.ligne =0;
	}
	/**
	 * Méthode augmentant la colonne de 1
	 */
	public void incrColonne() {
		this.colonne++;
		if(this.colonne == Partie.getInstance().getNombreColonnes()) this.colonne =0;
	}
	
	/**
	 * Méthode de comparaison equals renvoyant un boolean
	*/
	public boolean equals(Position p) {
		if(this.ligne == p.getLigne() && this.colonne == p.getColonne()) return true;
		else return false;
	}
}
