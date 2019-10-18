package noyau;

import java.io.Serializable;
import java.util.Random;

import utils.DirectionMonstre;
import utils.Position;

public class Monstre implements Serializable{

	private static final long serialVersionUID = -3684104027552408961L;
	private Position position;
	private Random alea = new Random();
	
	/**
	 * Constructeur d'un monstre.
	 */
	public Monstre(int nbLignes, int nbColonnes) {
		position = new Position(this.alea.nextInt(nbLignes), this.alea.nextInt(nbColonnes));
	}
	
	/**
	 * M�thode disant si le monstre a gagn� ou non.
	 * @return true si gagn�.
	 */
	public boolean aGagne() {
		return Partie.getInstance().toutExplore();
	}
	/**
	 * M�thode renvoyant la position en ordonn�e.
	 * @return int
	 */
	public int getLigne() {
		return this.position.getLigne();
	}
	/**
	 * M�thode renvoyant la position en abscisse.
	 * @return int
	 */
	public int getColonne() {
		return this.position.getColonne();
	}
	/**
	 * M�thode permettant le d�placement du monstre.
	 * @param d directionMonstre
	 */
	public void deplacement(DirectionMonstre d) {
		if(d == DirectionMonstre.UP ) up();
		if(d == DirectionMonstre.DOWN) down();
		if(d == DirectionMonstre.LEFT) left();
		if(d == DirectionMonstre.RIGHT) right();
		if(d == DirectionMonstre.DOWN_LEFT) {
			down();
			left();
		}
		if(d == DirectionMonstre.DOWN_RIGHT) {
			down();
			right();
		}
		if(d == DirectionMonstre.UP_RIGHT) {
			up();
			right();
		}
		if(d == DirectionMonstre.UP_LEFT) {
			up();
			left();
		}
		Partie.getInstance().changeVisiteTime(position);
	}
	/**
	 * M�thode d�pla�ant le monstre vers le haut
	 */
	private void up() {
		position.decLigne();
	}
	/**
	 * M�thode d�pla�ant le monstre vers le bas
	 */
	private void down() {
		position.incrLigne();
	}
	/**
	 * M�thode d�pla�ant le monstre vers la gauche
	 */
	private void left() {
		position.decColonne();
	}
	/**
	 * M�thode d�pla�ant le monstre vers la droite
	 */
	private void right() {
		position.incrColonne();
	}

	public void deplacementPosition(Position position2) {
		this.position = position2;
	}
}
