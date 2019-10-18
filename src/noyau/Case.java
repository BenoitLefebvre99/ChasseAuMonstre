package noyau;

import java.io.Serializable;

public class Case implements Serializable{

	private static final long serialVersionUID = 4186621749258968978L;
	private int visiteTime;
	private boolean showed;
	/**
	 * Constructeur de Case
	 */
	public Case() {
		this.visiteTime = -1;
		this.showed = false;
	}
	
	/**
	 * Méthode renvoyant le tour du dernier passage;
	 */
	public String toString() {
		updateVisiteTime();
		return "" + this.visiteTime;
	}
	/**
	 * Méthode dévoilant si la Case a été tirée par le Chasseur.
	 * @return true si la Case a été tirée.
	 */
	public boolean getShowed() {
		return this.showed;
	}
	/**
	 * Méthode activant l'affichage du nombre de tours.
	 */
	public void changeShowed() {
		this.showed =true;
	}
	/**
	 * Méthode changeant la date de visite.
	 */
	public void changeVisiteTime() {
		try {
		this.visiteTime = Partie.getInstance().getNombreTours();
		}
		catch(NullPointerException e) { // cas particulier survenant lors de l'initialisation d'une partie
			this.visiteTime=0;
		}
	}
	/**
	 * Méthode privée de mise à jour du nombre de tours.
	 */
	private void updateVisiteTime() {
		this.visiteTime = this.visiteTime++;
	}

	public int getVisiteTime() {
		return visiteTime;
	}
	
	
}
