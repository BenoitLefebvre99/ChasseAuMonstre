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
	 * Méthode disant si le monstre a gagné ou non.
	 * @return true si gagné.
	 */
	public boolean aGagne() {
		return Partie.getInstance().toutExplore();
	}
	/**
	 * Méthode renvoyant la position en ordonnée.
	 * @return int
	 */
	public int getLigne() {
		return this.position.getLigne();
	}
	/**
	 * Méthode renvoyant la position en abscisse.
	 * @return int
	 */
	public int getColonne() {
		return this.position.getColonne();
	}
	/**
	 * Méthode permettant le déplacement du monstre.
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
	 * Méthode déplaçant le monstre vers le haut
	 */
	private void up() {
		position.decLigne();
	}
	/**
	 * Méthode déplaçant le monstre vers le bas
	 */
	private void down() {
		position.incrLigne();
	}
	/**
	 * Méthode déplaçant le monstre vers la gauche
	 */
	private void left() {
		position.decColonne();
	}
	/**
	 * Méthode déplaçant le monstre vers la droite
	 */
	private void right() {
		position.incrColonne();
	}

	public void deplacementPosition(Position position2) {
		this.position = position2;
	}
}
