package launcherGraphics;

import ia.IAGraphics;
import network.NetworkGraphics;
import tourATour.TourATourGraphics;
import utils.TypeJeu;

public class Link {
	public static TypeJeu type;
	
	public Link() {type=null;}
	
	public static void affichageTransition(boolean chasseur) {
		switch(type) {
			case TourATour:
				TourATourGraphics.affichageTransition(chasseur);
				break;
			case IA:
				IAGraphics.affichageTransition(chasseur);
				break;
			case Network:
				//NetworkGraphics.affichageTransition(chasseur);
				break;
			default:
				break;
		}
	}
	
	public static void affichageVainqueur(boolean chasseur) {
		switch(type) {
		case TourATour:
			TourATourGraphics.affichageVainqueur(chasseur);
			break;
		case IA:
			IAGraphics.affichageVainqueur(chasseur);
			break;
		case Network:
			//NetworkGraphics.affichageVainqueur(chasseur);
			break;
		default:
			break;
	}
	}
	
	public static void setType(TypeJeu t) {type=t;}
	
}
