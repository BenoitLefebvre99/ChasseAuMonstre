package noyauNetwork;

import util.Clavier;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Multijoueur en réseau ?");
		int mp=Clavier.lireInt();
		if(mp==0) launcherText.Offline.main(null); //mode hors ligne -> IA ou 2 joueurs locaux
		else launcherText.Online.main(null);	//mode en ligne 2 joueurs sur 2 pc distants
	}


}
