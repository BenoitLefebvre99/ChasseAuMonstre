package noyauNetwork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import noyau.Partie;
import utils.DirectionMonstre;
import utils.Position;

public class GestionTourOnline {
	private static Scanner tmp;
	private boolean hostIsHunter;
	private boolean tourMonstre=true;

	public GestionTourOnline(boolean hostIsHunter) {
		this.hostIsHunter=hostIsHunter;
	}

	public void prochainTour(ObjectInputStream in,ObjectOutputStream out) {
		try {
			if(tourMonstre) {
				if(hostIsHunter) clientM(in,out); 
				else serveurM(in,out);
			}
			else {
				if(hostIsHunter) serveurC(in,out);
				else clientC(in,out);
			}

			tourMonstre=!tourMonstre;
		}catch (IOException e) {
			e.printStackTrace();	
		}
		
		Partie.getInstance().checkWin(false);
		Partie.getInstance().checkWin(true);
		
	}

	private void serveurM(ObjectInputStream in,ObjectOutputStream out) throws IOException {
		System.out.println(Partie.getInstance().toStringMonstre());
		char m = choixDeplacement();
		if(m == 'z') Partie.getInstance().deplacement(DirectionMonstre.UP);
		if(m == 's') Partie.getInstance().deplacement(DirectionMonstre.DOWN);
		if(m == 'q') Partie.getInstance().deplacement(DirectionMonstre.LEFT);
		if(m == 'd') Partie.getInstance().deplacement(DirectionMonstre.RIGHT);
		if(m == 'l') Partie.getInstance().finDePartie();

	}

	private void serveurC(ObjectInputStream in,ObjectOutputStream out) throws IOException {
		System.out.println(Partie.getInstance().toStringChasseur());
		Partie.getInstance().tir(attentionLeChasseurTire());
	}

	private void clientM(ObjectInputStream in,ObjectOutputStream out) throws IOException {
		out.flush();
		out.writeObject(Partie.getInstance().toStringMonstre());
		DirectionMonstre choixClientM;
		do {
			System.out.println("en Attente de la saisie du client");
			try {
				choixClientM=(DirectionMonstre) in.readObject();
				Partie.getInstance().deplacement(choixClientM);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		while(in==null);
	}

	private void clientC(ObjectInputStream in,ObjectOutputStream out) throws IOException {
		out.flush();
		out.writeObject(Partie.getInstance().toStringChasseur());
		Position choixClientC;
		do {
			System.out.println("en Attente de la saisie du client");
			try {
				choixClientC=(Position) in.readObject();
				Partie.getInstance().tir(choixClientC);
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		while(in==null);

	}

	private static Position attentionLeChasseurTire() {
		System.out.println("Saisissez la ligne Ã  viser puis la colonne Ã  viser");
		return new Position(readInt(Partie.getInstance().getNombreLignes()-1),readInt(Partie.getInstance().getNombreColonnes()-1));
	}

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

	private static char choixDeplacement() {
		char res='a';
		tmp= new Scanner(System.in);
		do {
			System.out.println("Veuillez entrez un caractï¿½re : ");
			System.out.println("l : quitter");
			System.out.println("z : UP ");
			System.out.println("s : DOWN ");
			System.out.println("q : LEFT");
			System.out.println("d : RIGHT");
			System.out.print("Caractï¿½re choisi : ");
			res = tmp.next().charAt(0);
		}while(res!='l' && res!='z' && res!='s' && res!='q' && res!='d');
		return res;
	}
}
