package noyau;

import java.io.Serializable;
import java.util.Scanner;

import utils.DirectionMonstre;
import utils.Position;

public class Partie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594321567179916344L;

	private static Partie instance;
	public int nombreTours;
	public boolean enCours;
	public final Map plateau;
	public final Monstre monstre;
	public final Chasseur chasseur;
	


	/**
	 * Constructeur d'une Partie g�n�rant une Map de nbLigne lignes et de nbColonne colonnes.
	 * @param nbLigne Le nombre de lignes voulu
	 * @param nbColonne Le nombre de colonnes voulu
	 */
	private Partie(int nbLignes, int nbColonnes) {
		
		
		nombreTours = 0;
		enCours = true;
		plateau = new Map(nbLignes, nbColonnes);
		monstre = new Monstre(nbLignes,nbColonnes);
		chasseur = new Chasseur();
	}
	/**
	 * 
	 * @param nbLigne Le nombre de lignes voulu
	 * @param nbColonne Le nombre de colonnes voulu
	 */
	public static void initInstance(int nbLignes,int nbColonnes) {
		instance=new Partie(nbLignes, nbColonnes);
		}
	
	/**
	 * Retourne l'instance unique de la classe Partie
	 * @return l'instance de Partie
	 */
	public static Partie getInstance() {
		if(instance==null) {
			initInstance(2, 2);
		}
		return instance;
	}
	
	/**
	 * M�thode permettant de savoir s'il y a un vainqueur pour arr�ter la Partie.
	 * @param chasseur vrai si on v�rifie la victoire du chasseur, faux pour celle du monstre
	 * @return true s'il y a un vainqueur.
	 */
	public boolean checkWin(boolean chasseur) {
		if(chasseur) {
			if(this.chasseur.aGagne()) {
				this.enCours=false;
				return true;
			}
			else return false;
		}
		else {
			if(monstre.aGagne()) {
				enCours=false;
				return true;		

			}
			else return false;
		}
	}
	/**
	 * M�thode d�terminant si toutes les cases ont �t� explor�es par le monstre
	 * @return vrai si c'est le cas, faux sinon
	 */
	public  boolean toutExplore() {
		for(int ligne=0;ligne<this.plateau.getNB_LIGNES();ligne++) {
			for(int colonne=0;colonne<this.plateau.getNB_COLONNES();colonne++) {
				if(this.plateau.getCaseN(ligne, colonne).getVisiteTime()==-1) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * M�thode affichant le vainqueur si il y en a un et met fin au jeu
	 */
	
	public void finDePartie() {
		String res="";
		if(this.monstre.aGagne()) {
			res="Le Monstre a gagn�";
		}
		else if(this.chasseur.aGagne()) {
			res="Le chasseur a gagn�";
		}
		else {
			res="Partie annul�e";
		}
		for(int i=0;i<50;i++) {
			System.out.println("\n");
		}
		System.out.println(res);
		Scanner tmp = new Scanner(System.in);
		char tmp2;
		do {
			System.out.println("Fermeture du jeu, Tapez Y pour valider");
			tmp2=tmp.next().charAt(0);
		}while(tmp2!='y' && tmp2 !='Y');
		tmp.close();
		System.exit(0);
	}
	
	/**
	 * M�thode renvoyant la carte sous forme de texte (format chasseur).
	 * @return String la carte
	 */
	public String toStringChasseur() {return this.plateau.toString();}
	/**
	 * M�thode renvoyant la carte sous forme de texte (format monstre).
	 * @return String la carte
	 */
	public String toStringMonstre() {
		int coordL = this.monstre.getLigne();
		int coordC = this.monstre.getColonne();
		return super.toString() + " \n " + this.plateau.toString(coordL, coordC);
		}
	
	/**
	 * M�thode lan�ant un nouveau tour et incr�mentant le nombre de tours.
	 */
	public void newTour() { this.nombreTours++;}
	
	/**
	 * M�thode renvoyant le num�ro de tour actuel.
	 * @return int nombre de tour actuel
	 */
	public int getNombreTours() {
		return this.nombreTours;
	}
	/**
	 * M�thode d�couvrant la Case aux indices en param�tre.
	 * @param ligne la ligne souhait�e
	 * @param colonne la colonne souhait�e
	 */
	public void setShowed(int ligne, int colonne){
		this.plateau.changeShowed(ligne, colonne);
	}
	/**
	 * M�thode retournant le nombre de lignes de la map.
	 * @return int le nombre de ligne
	 */
	public int getNombreLignes() {
		return this.plateau.getNB_LIGNES();
	}
	/**
	 * M�thode retournant le nombre de colonnes de la map.
	 * @return int le nombre de colonne
	 */
	public int getNombreColonnes() {
		return this.plateau.getNB_COLONNES();
	}
	/**
	 * M�thode renvoyant un bool�en donnant l'�tat de la Partie.
	 * @return true si partie lanc�e.
	 */
	public boolean enCours() {
		return this.enCours;
	}
	/**
	 * M�thode �x�cutant un tir du chasseur.
	 * @param p la position cibl�e
	 */
	public void tir(Position p) {
		this.chasseur.tir(p);
	}
	/**
	 * M�thode �x�cutant le d�placement du monstre.
	 * @param d le d�placement choisi
	 */
	public void deplacement(DirectionMonstre d) {this.monstre.deplacement(d);}
	
	/**
	 * M�thode actualisant le tour auquelle la case p � �t� visit�e
	 * @param p La position de la case souhait�e
	 */
	public void changeVisiteTime(Position p) {
		this.plateau.changeVisiteTime(p);
	}
	/**
	 * Méthode actualisant le tour de visite de la case sur laquelle est le monstre
	 */
	public void changeVisiteTime() {
		this.plateau.changeVisiteTime(new Position(this.monstre.getLigne(), this.monstre.getColonne()));
	}
	public void deplacementPosition(Position position) {
		this.monstre.deplacementPosition(position);
		
	}
	
}
