package launcherGraphics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import ia.IAGraphics;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import network.NetworkGraphics;
import tourATour.TourATourGraphics;

public class Index extends Application{

	//Menu
	private Stage primaryStage = new Stage();
	private Scene mainMenu;
	private VBox choixMenu;
	private Button jeuSolo, jeuContreIA, jeuReseau, aideMenu, submit;
	public static String path = "file:../../ressources/";
	
	//MENU PRINCIPAL NETWORK
		private static Scene selection;
		private static VBox menuSelection;
		private static Button hote, invite, back;
		private static boolean iAmHost;
		
	//ENTRE DE L'IP
		private static Scene addressEnter;
		private static VBox menu;
		private static TextField ip;
		private static Label intitule;
		private static HBox boutons;
		private static Button valider,backMenuP,chasseur,monstre;
		
	//MENU PRINCIPAL CHOIX IA
		private static Scene choixIA;
		private static VBox menuIA;
		private static Button chasser,etreChasse,easy,medium;
		private static Label intituleIA;
		private static HBox integBoutons;
		
	/**
	 * Méthode privée qui charge le fichier d'aide et qui l'affiche.	
	 */
	private void affichageAide() {
		VBox contentAide = new VBox(5);
		contentAide.setAlignment(Pos.CENTER);
		mainMenu = new Scene(contentAide);
		mainMenu.getStylesheets().add("stylesheets/menu.css");
		contentAide.setId("choixMenu");
		Text aide = new Text();
		
		String texte="";
		submit = new Button("OK j'ai compris !");
		submit.setOnAction(new ClickHandler());
		submit.setId("aideButton");
		
		try{
			   InputStream in=new FileInputStream(path+"help_menu"); 
			   InputStreamReader lect=new InputStreamReader(in);
			   BufferedReader br=new BufferedReader(lect);
			   String ligne;
			   while ((ligne=br.readLine())!=null){
			    System.out.println(ligne);
			    texte+=ligne+"\n";
			   }
			   br.close(); 
		  }  
		  catch (Exception e){
		   System.out.println(e.toString());
		  }
		aide.setText(texte);
		aide.setId("aide");
		contentAide.getChildren().addAll(aide,submit);
		
		primaryStage.setScene(mainMenu);
		
	}
	
	private class ClickHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent bouton) {
			if(bouton.getTarget() == jeuSolo) {
				TourATourGraphics.lancer();
				primaryStage.close();
			}
			else if(bouton.getTarget() == jeuReseau) {
				menuSelection = new VBox(5);
				hote = new Button("Hôte");
				hote.setId("MainButton");
				hote.setOnAction(new ClickHandler());
				invite = new Button("Invité");
				invite.setId("MainButton");
				invite.setOnAction(new ClickHandler());
				back = new Button("Retour");
				back.setId("MainButton");
				back.setOnAction(new ClickHandler());
				
				menuSelection.getChildren().addAll(hote,invite,back);
				menuSelection.setId("choixMenu");
				
				menuSelection.setAlignment(Pos.CENTER);
				menuSelection.getStylesheets().add("stylesheets/menu.css");
				
				primaryStage = new Stage();
				selection = new Scene(menuSelection);
				primaryStage.setScene(selection);
				primaryStage.show();
			}
			else if(bouton.getTarget() == jeuContreIA) {
				menuIA = new VBox(5);
				integBoutons = new HBox(5);
				chasser = new Button("Chasseur");
				etreChasse = new Button("Monstre");
				
				intituleIA= new Label("Qui es-tu ?");
				intituleIA.setId("IntituleNetwork");
				
				chasser.setId("MainButton");
				chasser.setOnAction(new ClickHandler());
				etreChasse.setId("MainButton");
				etreChasse.setOnAction(new ClickHandler());
				
				
				integBoutons.getChildren().addAll(chasser,etreChasse);
				
				menuIA.getChildren().addAll(intituleIA, integBoutons);
				menuIA.setId("choixMenu");
				
				menuIA.setAlignment(Pos.CENTER);
				menuIA.getStylesheets().add("stylesheets/menu.css");
				
				choixIA = new Scene(menuIA);
				primaryStage.setScene(choixIA);
				primaryStage.show();
			}
			else if(bouton.getTarget() == chasser) {
				menuIA = new VBox(5);
				integBoutons = new HBox(5);
				easy = new Button("FACILE");
				medium = new Button("MOYEN");
				
				intituleIA= new Label("Choisis ta difficulté");
				intituleIA.setId("IntituleNetwork");
				
				easy.setId("MainButton");
				easy.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						IAGraphics.lancer(true, true);
						primaryStage.close();
						
					}
					});
				medium.setId("MainButton");
				medium.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						IAGraphics.lancer(true,false);
						primaryStage.close();
					}
					});
				
				
				integBoutons.getChildren().addAll(easy,medium);
				
				menuIA.getChildren().addAll(intituleIA, integBoutons);
				menuIA.setId("choixMenu");
				
				menuIA.setAlignment(Pos.CENTER);
				menuIA.getStylesheets().add("stylesheets/menu.css");
				
				choixIA = new Scene(menuIA);
				primaryStage.setScene(choixIA);
				primaryStage.show();
			}
			else if(bouton.getTarget() == etreChasse) {
				menuIA = new VBox(5);
				integBoutons = new HBox(5);
				easy = new Button("FACILE");
				medium = new Button("MOYEN");
				
				intituleIA= new Label("Choisis ta difficulté");
				intituleIA.setId("IntituleNetwork");
				
				easy.setId("MainButton");
				easy.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						IAGraphics.lancer(false, true);
						primaryStage.close();
						
					}
					});
				medium.setId("MainButton");
				medium.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						IAGraphics.lancer(false,false);
						primaryStage.close();
					}
					});
				
				
				integBoutons.getChildren().addAll(easy,medium);
				
				menuIA.getChildren().addAll(intituleIA, integBoutons);
				menuIA.setId("choixMenu");
				
				menuIA.setAlignment(Pos.CENTER);
				menuIA.getStylesheets().add("stylesheets/menu.css");
				
				choixIA = new Scene(menuIA);
				primaryStage.setScene(choixIA);
				primaryStage.show();
			}
			else if(bouton.getTarget() == aideMenu) affichageAide();
			else if(bouton.getTarget() == submit) {
				try {
					start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			else if(bouton.getTarget() == hote) {
				iAmHost = true;
				boutons= new HBox(5);
				boutons.setAlignment(Pos.CENTER);
				
				menu = new VBox(5);
				menu.setAlignment(Pos.CENTER);
				
				intitule = new Label("Choisis un rôle :");
				intitule.setId("IntituleNetwork");
				
				chasseur = new Button("CHASSEUR");
				chasseur.setId("MainButton");
				chasseur.setOnAction(new ClickHandler());
				
				monstre = new Button("MONSTRE");
				monstre.setId("MainButton");
				monstre.setOnAction(new ClickHandler());
				
				boutons.getChildren().addAll(chasseur,monstre);
				
				menu.getChildren().addAll(intitule,boutons);
				menu.setId("choixMenu");
				menu.getStylesheets().add("stylesheets/menu.css");
				addressEnter = new Scene(menu);
				primaryStage.setScene(addressEnter);
				primaryStage.show();
			}
			else if(bouton.getTarget() == chasseur) {
				NetworkGraphics.lancer(iAmHost, null, true);
				primaryStage.close();
			}
			else if(bouton.getTarget() == monstre) {
				NetworkGraphics.lancer(iAmHost, null, false);
				primaryStage.close();
			}
			else if(bouton.getTarget() == invite) {
				iAmHost = false;
				menu = new VBox(5);
				menu.setAlignment(Pos.CENTER);
				intitule = new Label("Adresse IP de l'hôte :");
				intitule.setId("IntituleNetwork");
				ip = new TextField();
				ip.setPromptText("Adresse IP");
				ip.setAlignment(Pos.CENTER);
				valider = new Button("Valider");
				valider.setId("aideButton");
				valider.setOnAction(new ClickHandler());
				backMenuP = new Button("Retour");
				backMenuP.setId("aideButton");
				boutons= new HBox(5);
				boutons.setAlignment(Pos.CENTER);
				boutons.getChildren().addAll(valider,backMenuP);
				menu.getChildren().addAll(intitule, ip,boutons);
				menu.setId("choixMenu");
				menu.getStylesheets().add("stylesheets/menu.css");
				addressEnter = new Scene(menu);
				primaryStage.setScene(addressEnter);
				primaryStage.show();
			}
			else if(bouton.getTarget() == back) {
				//Index.launch("");
			}
			else if(bouton.getTarget() == valider) {
				NetworkGraphics.lancer(iAmHost, ip.getText(),false);
				primaryStage.close();
			}
		}
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		choixMenu = new VBox(5);
		mainMenu = new Scene(choixMenu);
		mainMenu.getStylesheets().add("stylesheets/menu.css");
		choixMenu.setId("choixMenu");
		choixMenu.setAlignment(Pos.CENTER);
		jeuSolo = new Button("Jouer Tour à Tour");
		jeuContreIA = new Button("Jouer Seul (BOT)");
		jeuReseau = new Button("Jouer en Réseau");
		aideMenu = new Button("Aide");
		
		jeuSolo.setId("MainButton");
		jeuReseau.setId("MainButton");
		jeuContreIA.setId("MainButton");
		aideMenu.setId("MainButton");
		
		jeuSolo.setOnAction(new ClickHandler());
		jeuReseau.setOnAction(new ClickHandler());
		jeuContreIA.setOnAction(new ClickHandler());
		aideMenu.setOnAction(new ClickHandler());
		
		choixMenu.getChildren().addAll(jeuSolo, jeuContreIA, jeuReseau, aideMenu);
		
		new Link();
		this.primaryStage.setScene(mainMenu);
		this.primaryStage.setResizable(false);
		this.primaryStage.setTitle("Chasse Au Monste | Menu");
		this.primaryStage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(args);

	}

}
