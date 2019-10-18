package tourATour;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import launcherGraphics.CaseUnit;
import launcherGraphics.Index;
import launcherGraphics.Link;
import noyau.Partie;
import utils.Position;
import utils.TypeCaseUnit;
import utils.TypeJeu;

public class TourATourGraphics {
	
	public static Partie jeu;
	private static Stage primaryStage;
	private static Scene primaryScene;
	private static BorderPane main;
	private static GridPane center;
	private static int xTaille,yTaille;
	private static final int WIDTH_GRILLE=900,HEIGHT_GRILLE=900;
	private static CaseUnit[][] plateauMonstre,plateauChasseur;
	private static Position anciennePositionMonstre;
	
	
	//LEFT
	private static VBox menu;
	private static Label tour;
	private static Label aQuiLeTour;
	private static Button quitter,goMonstre,goChasseur;

	/**
	 * Méthode créant la map du chasseur en initialisant le tableau de CaseUnit[][];
	 */
	public static void creationMapChasseur() {
		boolean chasseur=true;
		for(int ligne=0; ligne<plateauChasseur.length; ligne++) {
			for(int colonne=0; colonne<plateauChasseur[0].length; colonne++) {
				plateauChasseur[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
			}
		}
	}
	
	/**
	 * Méthode créant la map du monstre en initialisant le tableau de CaseUnit[][];
	 */
	public static void creationMapMonstre() {
		boolean chasseur=false;
		if(anciennePositionMonstre!=null) plateauMonstre[anciennePositionMonstre.getLigne()][anciennePositionMonstre.getColonne()].setType(TypeCaseUnit.WALKED);
		for(int ligne=0; ligne<plateauMonstre.length; ligne++) {
			for(int colonne=0; colonne<plateauMonstre[0].length; colonne++) {
				plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
				if(Partie.getInstance().monstre.getColonne()==colonne 
						&& Partie.getInstance().monstre.getLigne() ==ligne) {
					plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.MONSTRE, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
					anciennePositionMonstre = new Position(ligne,colonne);
				}
			}
		}
	}
	/**
	 * Méthode affichant l'écran du vainqueur en l'attachant à la partie centrale du BorderPane.
	 */
	public static void affichageVainqueur(boolean chasseur) {
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img;
		if(chasseur) {
			if(menu.getChildren().contains(goChasseur)) menu.getChildren().remove(goChasseur);
			img = new Image(Index.path+"vainqueurChasseur.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
			gc.drawImage(img, 0, 0);
			main.setCenter(c);
		}
		else {
			if(menu.getChildren().contains(goMonstre)) menu.getChildren().remove(goMonstre);
			img = new Image(Index.path+"vainqueurMonstre.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
			gc.drawImage(img, 0, 0);
			main.setCenter(c);
		}
	}
	
	/**
	 * Méthode attachant la map du chasseur sous forme de GridPane à la partie centrale du BorderPane.
	 */
	public static void affichageMapChasseur() {
		center.getChildren().clear();
		for(int y=0; y<plateauChasseur.length; y++) {
			for(int x=0; x<plateauChasseur[0].length;x++) {
				center.add(plateauChasseur[y][x].getCanvas(), x , y);
				if(plateauChasseur[y][x].getType() == TypeCaseUnit.SHOT) {
					Label ShotTour = new Label(Partie.getInstance().plateau.getVisitTime(y, x));
					ShotTour.setId("LabelTourShot");
					if(ShotTour.getText().equalsIgnoreCase("-1")) ShotTour.setText("?");
					center.add(ShotTour, x, y);	
				}
			}
		}
		main.setCenter(center);
	}
	/**
	 * Méthode privée mettant à jour le tableau du monstre.
	 */
	private static void majMonstre() {
		boolean chasseur = false;
		plateauMonstre[anciennePositionMonstre.getLigne()][anciennePositionMonstre.getColonne()].setType(TypeCaseUnit.WALKED);
		for(int ligne=0; ligne<plateauMonstre.length; ligne++) {
			for(int colonne=0; colonne<plateauMonstre[0].length; colonne++) {
				if(Partie.getInstance().monstre.getColonne()==colonne 
						&& Partie.getInstance().monstre.getLigne() ==ligne) {
					plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.MONSTRE, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
					anciennePositionMonstre = new Position(ligne,colonne);
				}
				else if (plateauMonstre[ligne][colonne].getType()==TypeCaseUnit.WALKED) plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.WALKED, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
				else plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, chasseur, ligne, colonne);
			}
		}
	}
	/**
	 * Méthode attachant la map du monstre sous forme de GridPane à la partie centrale du BorderPane.
	 */
	public static void affichageMapMonstre() {
		center.getChildren().clear();
		majMonstre();
		for(int y=0; y<plateauMonstre.length; y++) {
			for(int x=0; x<plateauMonstre[0].length;x++) {
				center.add(plateauMonstre[y][x].getCanvas(), x,y);
			}
		}
		main.setCenter(center);
	}
	
	/**
	 * Affichage de l'écran de transition annonçant qui doit jouer.
	 * @param chasseur
	 */
	public static void affichageTransition(boolean chasseur) {
		center.getChildren().clear();
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img;
		if(chasseur) {
			if(menu.getChildren().contains(goChasseur)) menu.getChildren().remove(goChasseur);
			menu.getChildren().add(goMonstre);
			img = new Image(Index.path+"tourMonstre.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
			gc.drawImage(img, 0, 0);
			main.setCenter(c);
		}
		else {
			if(monstreAGagne()) affichageVainqueur(chasseur);
			else {
				addTour();
				if(menu.getChildren().contains(goMonstre)) menu.getChildren().remove(goMonstre);
				menu.getChildren().add(goChasseur);
				img = new Image(Index.path+"tourChasseur.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
				gc.drawImage(img, 0, 0);
				main.setCenter(c);
			}
		}
	}
	/**
	 * Méthode vérifiant si le monstre a gagné la partie.
	 * @return
	 */
	private static boolean monstreAGagne() {
		boolean monstre = false;
		for(int ligne=0; ligne<plateauMonstre.length; ligne++) {
			for(int colonne=0; colonne<plateauMonstre[0].length; colonne++) {
				if(plateauMonstre[ligne][colonne].getType()==TypeCaseUnit.MONSTRE && !monstre) monstre=!monstre;
				else if(plateauMonstre[ligne][colonne].getType()!=TypeCaseUnit.WALKED) return false;
			}
		}
		return true;
	}
	private static void addTour() {
		Partie.getInstance().changeVisiteTime();
		Partie.getInstance().newTour();
		tour.setText("Tour n° "+Partie.getInstance().getNombreTours());
	}
	/**
	 * Méthode privée cherchant le nombre de colonnes et de ligne de la partie.
	 */
	private static void initialisation() {
		Random select = new Random();
		xTaille = select.nextInt(6)*2;
		yTaille= select.nextInt(6)*2;
		if(xTaille<5)xTaille=5;
		else if(xTaille>10)xTaille=10;
		yTaille = xTaille;
	}
	public static class ClickHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if(event.getTarget() == goMonstre) affichageMapMonstre();
			else if(event.getTarget() == goChasseur) affichageMapChasseur();
		}
	}
	/**
	 * Méthode de lancement de la partie en Tour à Tour.
	 */
	public static void lancer() {
		Link.setType(TypeJeu.TourATour);
		main = new BorderPane();
		primaryStage = new Stage();
		//Right
			menu = new VBox(5);
			menu.setAlignment(Pos.TOP_CENTER);
			tour = new Label("Tour n° "+Partie.getInstance().getNombreTours());
			aQuiLeTour = new Label("Monstre");
			aQuiLeTour.setId("TourMenu");
			tour.setId("TourMenu");
			goChasseur = new Button("C'est parti !");
			goMonstre = new Button("C'est parti !");
			goMonstre.setId("quitter");
			goChasseur.setId("quitter");
			
			goMonstre.setOnAction(new ClickHandler());
			goChasseur.setOnAction(new ClickHandler());
			quitter = new Button("Revenir au menu");
			quitter.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent evt) {
					primaryStage.close();
				}
				
			});
			quitter.setId("quitter");
			menu.getChildren().addAll(tour,aQuiLeTour,quitter);
			main.setRight(menu);
		
		initialisation();
		Partie.initInstance(xTaille,yTaille);
		
		plateauChasseur = new CaseUnit[xTaille][yTaille];
		plateauMonstre = new CaseUnit[xTaille][yTaille];
		
		main.setId("main");
		center = new GridPane();
		center.setAlignment(Pos.TOP_LEFT);
		center.setId("map");
		center.setMinWidth(WIDTH_GRILLE);
		center.setMaxWidth(WIDTH_GRILLE);
		center.setMinHeight(HEIGHT_GRILLE);
		center.setMaxHeight(HEIGHT_GRILLE);
		
		//JEU
		creationMapMonstre();
		creationMapChasseur();
		affichageTransition(true);
		
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img = new Image(Index.path+"go.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
		gc.drawImage(img, 0, 0);
		c.setOnMouseClicked(new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent arg0) {
			affichageMapMonstre();
			
		}});
		
		main.setCenter(c);
		
		primaryScene = new Scene(main);
		primaryScene.getStylesheets().add("stylesheets/menuTourTour.css");
		
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Chasse au monstre | Tour à Tour");
		/*primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(1200);*/
		primaryStage.show();
	}
}
