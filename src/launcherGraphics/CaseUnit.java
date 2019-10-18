package launcherGraphics;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import noyau.Partie;
import utils.DirectionMonstre;
import utils.Position;
import utils.TypeCaseUnit;

public class CaseUnit extends Application{
	private final boolean CHASSEUR;
	private TypeCaseUnit type;
	private Canvas c;
	private final Position POSITION;
	private final double TAILLE;
	
	public CaseUnit(TypeCaseUnit t, double taille, boolean chasseur, int ligne, int colonne) {
		this.type = t;
		this.TAILLE = taille;
		this.CHASSEUR = chasseur;
		this.POSITION = new Position(ligne,colonne);
		
		this.c = new Canvas(this.TAILLE,this.TAILLE);
		this.c.setOnMouseClicked(new Hover());
		if(this.CHASSEUR) {
			this.c.setOnMouseEntered(new Hover());
			this.c.setOnMouseExited(new Hover());
		}
		else{ 
			initialisationMonstre();
		}
	}
	
	private void initialisationMonstre() {
		if(type!=TypeCaseUnit.WALKED && type!=TypeCaseUnit.BUISSON) {
			Position tmp = new Position(Partie.getInstance().monstre.getLigne(), Partie.getInstance().monstre.getColonne());
			if(POSITION.equals(new Position(tmp.getLigne(),tmp.getColonne()-1))) setType(TypeCaseUnit.LEFT); 
			else if(POSITION.equals(new Position(tmp.getLigne(),tmp.getColonne()+1))) setType(TypeCaseUnit.RIGHT); 
			else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()))) setType(TypeCaseUnit.UP); 
			else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()))) setType(TypeCaseUnit.DOWN);
			else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()-1))) setType(TypeCaseUnit.UP_LEFT);
			else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()-1))) setType(TypeCaseUnit.DOWN_LEFT);
			else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()+1))) setType(TypeCaseUnit.UP_RIGHT);
			else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()+1))) setType(TypeCaseUnit.DOWN_RIGHT); 
		}
		majCanvas();
	}
	public class Hover implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent evt) {
			if(evt.getEventType() == MouseEvent.MOUSE_ENTERED && type != TypeCaseUnit.SHOT && type!=TypeCaseUnit.BUISSON) {
				setType(TypeCaseUnit.TARGET);
				majCanvas();
			}
			else if(evt.getEventType() == MouseEvent.MOUSE_EXITED && type != TypeCaseUnit.SHOT && type!=TypeCaseUnit.BUISSON) {
				setType(TypeCaseUnit.NORMAL);
				majCanvas();
			}
			else if(evt.getEventType() == MouseEvent.MOUSE_CLICKED) {
				if(!CHASSEUR) {
					Position tmp = new Position(Partie.getInstance().monstre.getLigne(), Partie.getInstance().monstre.getColonne());
					if(POSITION.equals(new Position(tmp.getLigne(),tmp.getColonne()-1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.LEFT); 
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne(),tmp.getColonne()+1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.RIGHT); 
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.UP); 
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.DOWN);
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()-1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.UP_LEFT);
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()-1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.DOWN_LEFT);
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()-1,tmp.getColonne()+1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.UP_RIGHT);
						Link.affichageTransition(CHASSEUR);
					}
					else if(POSITION.equals(new Position(tmp.getLigne()+1,tmp.getColonne()+1))) {
						Partie.getInstance().monstre.deplacement(DirectionMonstre.DOWN_RIGHT); 
						Link.affichageTransition(CHASSEUR);
					}
					
					majCanvas();
				}
				else {
					Partie.getInstance().tir(POSITION);
					if(Partie.getInstance().chasseur.aGagne()) {
						setType(TypeCaseUnit.MONSTRE);
						Link.affichageVainqueur(CHASSEUR);
					}
					else {
						setType(TypeCaseUnit.SHOT);
						Link.affichageTransition(CHASSEUR);
					}
					majCanvas();	
				}
			}
		}
	}
	
	public TypeCaseUnit getType() {return this.type;}
	
	public Canvas getCanvas() {
		majCanvas();
		return this.c;
	}
	  
	public void setType(TypeCaseUnit t) {this.type = t;}
	
	private void majCanvas() {
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img = new Image(Index.path+this.type.GetFILENAME(),this.TAILLE,this.TAILLE, false, false);
		gc.drawImage(img, 0, 0);
	}
	
	@Override
	public void start(Stage arg0) throws Exception {}
}
