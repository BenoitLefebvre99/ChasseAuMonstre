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
	 * M�thode renvoyant le tour du dernier passage;
	 */
	public String toString() {
		updateVisiteTime();
		return "" + this.visiteTime;
	}
	/**
	 * M�thode d�voilant si la Case a �t� tir�e par le Chasseur.
	 * @return true si la Case a �t� tir�e.
	 */
	public boolean getShowed() {
		return this.showed;
	}
	/**
	 * M�thode activant l'affichage du nombre de tours.
	 */
	public void changeShowed() {
		this.showed =true;
	}
	/**
	 * M�thode changeant la date de visite.
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
	 * M�thode priv�e de mise � jour du nombre de tours.
	 */
	private void updateVisiteTime() {
		this.visiteTime = this.visiteTime++;
	}

	public int getVisiteTime() {
		return visiteTime;
	}
	
	
}
