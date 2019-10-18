package noyau;

import java.io.Serializable;

import utils.Position;

public class Chasseur implements Serializable {

	private static final long serialVersionUID = 1603085354536114617L;
	private boolean aGagne;
	
	/**
	 * Constructeur d'un chasseur.
	 */
	public Chasseur() {
		this.aGagne=false;
	}
	
	/**
	 * Méthode éxécutant le tir du Chasseur à la Position fournie.
	 * @param position La postion ciblée
	 */
	public void tir(Position position) {
		tir(position.getLigne(),position.getColonne());
	}
	/**
	 * Méthode éxécutant le tir du Chasseur aux coordonnées fournies.
	 * @param ligne La ligne ciblée
	 * @param colonne La colonne ciblée
	 */
	public void tir(int ligne, int colonne) {
		Partie.getInstance().setShowed(ligne,colonne);
		if(Partie.getInstance().monstre.getLigne() == ligne && Partie.getInstance().monstre.getColonne() == colonne) this.aGagne = true;
	}
	
	/**
	 * Méthode renvoyant si le chasseur a gagné ou non.
	 * @return boolean
	 */
	public boolean aGagne() {
		return this.aGagne;
	}
}
