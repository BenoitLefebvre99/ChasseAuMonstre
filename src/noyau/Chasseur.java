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
	 * M�thode �x�cutant le tir du Chasseur � la Position fournie.
	 * @param position La postion cibl�e
	 */
	public void tir(Position position) {
		tir(position.getLigne(),position.getColonne());
	}
	/**
	 * M�thode �x�cutant le tir du Chasseur aux coordonn�es fournies.
	 * @param ligne La ligne cibl�e
	 * @param colonne La colonne cibl�e
	 */
	public void tir(int ligne, int colonne) {
		Partie.getInstance().setShowed(ligne,colonne);
		if(Partie.getInstance().monstre.getLigne() == ligne && Partie.getInstance().monstre.getColonne() == colonne) this.aGagne = true;
	}
	
	/**
	 * M�thode renvoyant si le chasseur a gagn� ou non.
	 * @return boolean
	 */
	public boolean aGagne() {
		return this.aGagne;
	}
}
