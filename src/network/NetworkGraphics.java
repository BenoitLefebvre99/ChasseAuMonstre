package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
import noyauNetwork.GestionTourOnline;
import tourATour.TourATourGraphics.ClickHandler;
import utils.Position;
import utils.TypeCaseUnit;
import utils.TypeJeu;

public class NetworkGraphics {
	public static boolean iAmHost, iAmHunter;
	private static final int PORT =2683;
	
	//ELEMENTS DE LA PARTIE RESEAU 
	private static Socket socket;
	private static ObjectInputStream in = null;
	private static  ObjectOutputStream out = null;
	
	//GENERAL
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
	private static Button quitter;
	
	
	/**
	 * Méthode créant la Map du Chasseur en initialisant le plateau CaseUnit[][].
	 */
	public static void creationMapChasseur() {
		for(int ligne=0; ligne<plateauChasseur.length; ligne++) {
			for(int colonne=0; colonne<plateauChasseur[0].length; colonne++) {
				plateauChasseur[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
			}
		}
	}
	/**
	 * Méthode créant la Map du monstre en initialisant le plateau CaseUnit[][].
	 */
	public static void creationMapMonstre() {
		if(anciennePositionMonstre!=null) plateauMonstre[anciennePositionMonstre.getLigne()][anciennePositionMonstre.getColonne()].setType(TypeCaseUnit.WALKED);
		for(int ligne=0; ligne<plateauMonstre.length; ligne++) {
			for(int colonne=0; colonne<plateauMonstre[0].length; colonne++) {
				plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
				if(Partie.getInstance().monstre.getColonne()==colonne 
						&& Partie.getInstance().monstre.getLigne() ==ligne) {
					plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.MONSTRE, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
					anciennePositionMonstre = new Position(ligne,colonne);
				}
			}
		}
	}
	/**
	 * Méthode générant l'écran du vainqueur et l'attachant à la partie centrale du BorderPane.
	 */
	public static void affichageVainqueur() {
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img;
		if(iAmHunter) {
			img = new Image(Index.path+"vainqueurChasseur.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
			gc.drawImage(img, 0, 0);
			main.setCenter(c);
		}
		else {
			img = new Image(Index.path+"vainqueurMonstre.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
			gc.drawImage(img, 0, 0);
			main.setCenter(c);
		}
	}
	
	/**
	 * Méthode attanchant le GridPane de la map du Chasseur sur la partie centrale du BorderPane.
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
	 * Méthode privée qui mets à jour le plateau du monstre.
	 */
	private static void majMonstre() {
		plateauMonstre[anciennePositionMonstre.getLigne()][anciennePositionMonstre.getColonne()].setType(TypeCaseUnit.WALKED);
		for(int ligne=0; ligne<plateauMonstre.length; ligne++) {
			for(int colonne=0; colonne<plateauMonstre[0].length; colonne++) {
				if(Partie.getInstance().monstre.getColonne()==colonne 
						&& Partie.getInstance().monstre.getLigne() ==ligne) {
					plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.MONSTRE, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
					anciennePositionMonstre = new Position(ligne,colonne);
				}
				else if (plateauMonstre[ligne][colonne].getType()==TypeCaseUnit.WALKED) plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.WALKED, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
				else plateauMonstre[ligne][colonne] = new CaseUnit(TypeCaseUnit.NORMAL, WIDTH_GRILLE/xTaille, iAmHunter, ligne, colonne);
			}
		}
	}
	
	/**
	 * Méthode attachant le GridPane de la map du monstre sur la partie centrale du BorderPane.
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
	 * Méthode générant l'écran d'attente et l'attachant à la partie centrale du BorderPane.
	 */
	public static void affichageAttente() {
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img = new Image(Index.path+"attente.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
		gc.drawImage(img, 0, 0);
		main.setCenter(c);
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
	
	/**
	 * Méthode privée créant la socket nécessaire au jeu.
	 * @param server
	 * @param ip
	 * @param port
	 * @return
	 */
	private static Socket createSocket(boolean server,String ip,int port) {
		if(server) {
			try {
				ServerSocket serveur = new ServerSocket(port);
				Socket sock = serveur.accept();
				serveur.close();
				return sock;
			} catch (IOException e) { }
		}
		else {
			try {
				Socket socket = new Socket(ip, port);
				return socket;

			} catch (IOException e) {  }
		}
		return null;
	}
	
	/**
	 * Méthode privée mettant à jour le rôle de chacun des joueurs.
	 * @param hostIsHunter
	 */
	private static void updateRole(boolean hostIsHunter) {
		if(iAmHost) {
				try {
					iAmHost = hostIsHunter;
					out.flush();
					out.writeObject(new Boolean(hostIsHunter));
				} catch (IOException e) {}	
		}else {
			do {
				try {
					hostIsHunter = (boolean) in.readObject();
					iAmHunter = !hostIsHunter;
				} catch (ClassNotFoundException e) {
				} catch (IOException e) {}
				
			}while(in == null);
		}
	}
	
	/**
	 * Méthode de démarrage de la version IHM du jeu en réseau.
	 * @param iAmHost
	 * @param ip
	 * @param hostIsHunter
	 */
	public static void lancer(boolean iAmHost, String ip, boolean hostIsHunter) {
		Link.setType(TypeJeu.Network);
	
		//CREATION DE LA SOCKET
		//socket = createSocket(iAmHost,ip,PORT);
		
		//ATTRIBUTION DES ROLES
		//updateRole(hostIsHunter);
		
		//INITIALISATION PAR L'HOTE
		/*if(iAmHost) {
			initialisation();
			Partie.initInstance(xTaille, yTaille);
		}*/
		
		
		main = new BorderPane();
		primaryStage = new Stage();
		//Right
			menu = new VBox(5);
			menu.setAlignment(Pos.TOP_CENTER);
			tour = new Label("Tour n° "+Partie.getInstance().getNombreTours());
			aQuiLeTour = new Label("Monstre");
			aQuiLeTour.setId("TourMenu");
			tour.setId("TourMenu");
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
		/*if(iAmHunter) {
			creationMapChasseur();
			affichageAttente();
		}
		else {
			creationMapMonstre();

		}*/
		
		Canvas c = new Canvas(WIDTH_GRILLE,HEIGHT_GRILLE);
		GraphicsContext gc = c.getGraphicsContext2D();
		Image img = new Image(Index.path+"pbNetwork.png",WIDTH_GRILLE,HEIGHT_GRILLE, false, false);
		gc.drawImage(img, 0, 0);

		
		main.setCenter(c);
		
		primaryScene = new Scene(main);
		primaryScene.getStylesheets().add("stylesheets/menuTourTour.css");
		
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Chasse au monstre | Jeu En Réseau");
		primaryStage.show();
	}
}
