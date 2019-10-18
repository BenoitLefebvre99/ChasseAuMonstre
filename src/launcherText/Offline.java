package launcherText;

import java.util.Scanner;

import noyau.Partie;
import utils.DirectionMonstre;
import utils.Position;

public class Offline {
	private static Scanner tmp;

	/**
	 * Méthode assurant la bonne rotation des tours entre les deux joueurs ainsi qu'une confirmation pour éviter la triche involontaire
	 * @param var la variable donnant qui joue
	 */
	private static void ecranTransition(char var) {
		for(int i=0;i<50;i++) {
			System.out.println("\n");
		}

		tmp = new Scanner(System.in);
		char tmp2;
		do {
			System.out.println("Changement de joueur, Tapez Y pour valider");
			tmp2=tmp.next().charAt(0);
		}while(tmp2!='y' && tmp2 !='Y');
		System.out.println("Tour né"+Partie.getInstance().getNombreTours());
		if(var == 'm') System.out.println("Tour du Monstre ..");
		if(var == 'c') System.out.println("Tour du Chasseur ..");
	}
	
	/**
	 * Méthode demandant/vérifiant le choix de direction(haut,bas,gauche,droite et quitter) de l'utilisateur ou l'arrêt de la partie
	 * @return option choisie
	 */
	private static char choixDeplacement() {
		char res='a';
		tmp= new Scanner(System.in);
		do {
			System.out.println("Veuillez entrez un caractére : ");
			System.out.println("l : quitter");
			System.out.println("z : UP ");
			System.out.println("s : DOWN ");
			System.out.println("q : LEFT");
			System.out.println("d : RIGHT");
			System.out.print("Caractére choisi : ");
			res = tmp.next().charAt(0);
		}while(res!='l' && res!='z' && res!='s' && res!='q' && res!='d');
		return res;
	}
	
	/**
	 * Méthode assurant la demande de tir auprés du chasseur
	 * @return la position de tir transmise
	 */
	private static Position attentionLeChasseurTire() {
		System.out.println("Saisissez la ligne à viser puis la colonne à viser");
		return new Position(readInt(Partie.getInstance().getNombreLignes()-1),readInt(Partie.getInstance().getNombreColonnes()-1));
	}
	
	/**
	 * Méthode demandant/vérifiant/retournant la saisie d'un int auprés de l'utilisateur
	 * @param max borne supérieure de la saisie
	 * @return retourne un int valide compris entre 0 et max saisie par l'utilisateur
	 */
	private static int readInt(int max) {
		boolean correct=false;
		int res=0;
		while(!correct) {
			correct=true;
			System.out.println("Saisissez un entier entre 0 et "+(max));
			tmp = new Scanner(System.in);
			String tmp2=tmp.nextLine();
			try {
				res=Integer.parseInt(tmp2);
			}
			catch(Exception e){
				correct=false;
			}
			if(res<0||res>max) {
				correct=false;
			}
			
		}
		return res;
	}
	



	public static void main(String[] args) {
		Partie.initInstance(5, 5);
		Partie.getInstance().changeVisiteTime();
		char m;

		while(Partie.getInstance().enCours()) {
			Partie.getInstance().newTour();

			ecranTransition('m');
			System.out.println(Partie.getInstance().toStringMonstre());
			m = choixDeplacement();
			if(m == 'z') Partie.getInstance().deplacement(DirectionMonstre.UP);
			if(m == 's') Partie.getInstance().deplacement(DirectionMonstre.DOWN);
			if(m == 'q') Partie.getInstance().deplacement(DirectionMonstre.LEFT);
			if(m == 'd') Partie.getInstance().deplacement(DirectionMonstre.RIGHT);
			if(m =='l') Partie.getInstance().finDePartie();
			else {
				if(!Partie.getInstance().checkWin(false)) {
					ecranTransition('c');
					System.out.println(Partie.getInstance().toStringChasseur());
					Partie.getInstance().tir(attentionLeChasseurTire());
					Partie.getInstance().checkWin(true);
				}
			}

		}
		for(int i=0;i<50;i++) {
			System.out.println("\n");
		}
		Partie.getInstance().finDePartie();

	}

}
