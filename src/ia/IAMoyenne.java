package ia;

import java.util.Random;

import noyau.*;

public class IAMoyenne implements iIA{

	public  int[] chasseur(Map map) {
		int rdm=new Random().nextInt(3);
		int x=0;int y=0;
		Case tmp=new Case();
		for (int i=0;i<Partie.getInstance().getNombreLignes();i++) {
			for (int j=0;j<Partie.getInstance().getNombreColonnes();j++) {
				if (Partie.getInstance().plateau.getCaseN(i,j).getVisiteTime()>tmp.getVisiteTime()) {
					x=i;y=j;
					tmp=Partie.getInstance().plateau.getCaseN(i,j);
				}
			}
		}
		int a=(x+1*rdm)%Partie.getInstance().getNombreColonnes();
		int b=(x-1*rdm)%Partie.getInstance().getNombreColonnes();
		int c=(y+1*rdm)%Partie.getInstance().getNombreLignes();
		int d=(y-1*rdm)%Partie.getInstance().getNombreLignes();
		
		if (!Partie.getInstance().plateau.getCaseN(a,y).getShowed()) return new int[] {a,y};
		if (!Partie.getInstance().plateau.getCaseN(b,y).getShowed()) return new int[] {b,y};
		if (!Partie.getInstance().plateau.getCaseN(x,c).getShowed()) return new int[] {x,c};
		if (!Partie.getInstance().plateau.getCaseN(x,d).getShowed()) return new int[] {x,d};
				else return new int[] {(new Random()).nextInt(map.getNB_COLONNES()),(new Random()).nextInt(map.getNB_LIGNES())};
		
	}
	
	public int[] monstre(Map map) {
		boolean up=false;
		boolean down=false;
		boolean right=false;
		boolean left=false;
		int monstreL=Partie.getInstance().monstre.getLigne();
		int monstreC=Partie.getInstance().monstre.getColonne();
		
		if (!Partie.getInstance().plateau.getCaseN((monstreC+1)%Partie.getInstance().getNombreColonnes(),
				monstreL).getShowed()) {
			right=true;
		}
		
		if (!Partie.getInstance().plateau.getCaseN((monstreC-1)%Partie.getInstance().getNombreColonnes(),
				monstreL).getShowed()) {
			left=true;
		}
		
		if (!Partie.getInstance().plateau.getCaseN(monstreC,(monstreL+1)%Partie.getInstance().getNombreLignes()).getShowed()) {
			down=true;
		}
		
		if (!Partie.getInstance().plateau.getCaseN(monstreC,(monstreL-1)%Partie.getInstance().getNombreLignes()).getShowed()) {
			up=true;
		}
		
		
		
		if (!up&&!down&&!right&&!left) {
			int tmp=(new Random()).nextInt(3);
			if (tmp==0) return new int[] {(Partie.getInstance().monstre.getColonne()+1)%Partie.getInstance().getNombreColonnes(),Partie.getInstance().monstre.getLigne()};
			if (tmp==1) return new int[] {(Partie.getInstance().monstre.getColonne()-1)%Partie.getInstance().getNombreColonnes(),Partie.getInstance().monstre.getLigne()};
			if (tmp==2) return new int[] {Partie.getInstance().monstre.getColonne(),(Partie.getInstance().monstre.getLigne()+1)%Partie.getInstance().getNombreLignes()};
			else return new int[] {Partie.getInstance().monstre.getColonne(),(Partie.getInstance().monstre.getLigne()-1)%Partie.getInstance().getNombreLignes()};
		}
		
		
		boolean[] tab=new boolean[4];
			tab[0]=right;		
			tab[1]=left;
			tab[2]=up;
			tab[3]=down;
		
		boolean tmp=false;
		int indice=-1;
		
		while(!tmp){
			int rdm=new Random().nextInt(3); 
			tmp=tab[rdm];
			indice=rdm;
		}
		
		if (indice==0) return new int[] {monstreL,(monstreC+1)%Partie.getInstance().getNombreLignes()};
		if (indice==1) return new int[] {monstreL,(monstreC-1)%Partie.getInstance().getNombreLignes()};
		if (indice==2) return new int[] {(monstreL-1)%Partie.getInstance().getNombreLignes(),monstreC};
		else return new int[] {(monstreL+1)%Partie.getInstance().getNombreLignes(),monstreC};
	}
}
