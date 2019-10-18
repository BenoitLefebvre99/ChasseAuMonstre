package launcherText;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import noyau.Partie;
import noyauNetwork.GestionTourOnline;
import util.Clavier;
import utils.DirectionMonstre;
import utils.Position;

public class Online {
	static Scanner tmp;


	public static void main(String[] args) {
		int mp;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		System.out.println("Host ?");
		mp=Clavier.lireInt();						//Hote ou client
		boolean server=true;
		if(mp==0) server=false;
		String ip=null;
		if(!server) ip=Clavier.lireString();
		int port=Clavier.lireInt();
		Socket socket =createSocket(server,ip,port); //initialisation de la socket
		try {
			System.out.println("init stream");

			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		boolean hostIsHunter=true;
		if(server) {
			hostIsHunter=attribuerRoles();
			GestionTourOnline gestionnaire=new GestionTourOnline(hostIsHunter);
			try {
				out.flush();
				out.writeObject(new Boolean(hostIsHunter));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		GestionTourOnline gestionnaire=new GestionTourOnline(hostIsHunter);
		if(!server) {
			do {
				System.out.println("en Attente du role du serveur");
				try {
					hostIsHunter=(boolean) in.readObject();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			while(in==null);
		}
		
		if(server) {				
			Partie.initInstance(5, 5);
			Partie.getInstance().changeVisiteTime();
			while(Partie.getInstance().enCours()) {
				Partie.getInstance().newTour();
				gestionnaire.prochainTour(in,out);
			}
		}
		else {
			while(true) {
				do {
					System.out.println("en Attente de la partie du serveur");

					try {
						String plateau=(String) in.readObject();
						System.out.println(plateau);
						out.flush();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				while(in==null);

				if(hostIsHunter) {
					char m = choixDeplacement();
					try {
						if(m == 'z') out.writeObject(DirectionMonstre.UP);
						if(m == 's') out.writeObject(DirectionMonstre.DOWN);
						if(m == 'q') out.writeObject(DirectionMonstre.LEFT);
						if(m == 'd') out.writeObject(DirectionMonstre.RIGHT);
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				else {
					try {
						out.writeObject(attentionLeChasseurTire());
					}
					catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		//FIN D'EXECUTION
		try {
			socket.close();
			in.close();
			out.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	/**
	 * Méthode créant une socket entre 2 appareils pour une communication réseau
	 * @param ip adresse ip en décimale pointée
	 * @param port entier servant de numero de port
	 * @param server paramètre indiquant à la méthode si elle doit créer une socket ou s'associer à une socket existante
	 * @return une socket liant 2 appareils
	 */
	private static Socket createSocket(boolean server,String ip,int port) {

		if(server) {
			try {
				System.out.println("start serveur");
				ServerSocket serveur = new ServerSocket(port);

				Socket sock = serveur.accept();
				serveur.close();
				return sock;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				System.out.println("lookup");
				Socket socket = new Socket(ip, port);
				System.out.println(socket.isConnected());
				return socket;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Méthode attribuant coté serveur les roles des deux joueurs
	 * @return true si l'hote est chasseaur, faux sinon
	 */
	private static boolean attribuerRoles() {
		boolean hostIsHunter=false;// true
		hostIsHunter=true;				//attribution des rôles
		Random genAlea =new Random();
		if(genAlea.nextInt(2)==0) {
			hostIsHunter=false; // Test temporaire, a repasser a false
		}
		return hostIsHunter;
	}
	
	private static char choixDeplacement() {
		char res='a';
		tmp= new Scanner(System.in);
		do {
			System.out.println("Veuillez entrez un caractère : ");
			System.out.println("l : quitter");
			System.out.println("z : UP ");
			System.out.println("s : DOWN ");
			System.out.println("q : LEFT");
			System.out.println("d : RIGHT");
			System.out.print("Caractère choisi : ");
			res = tmp.next().charAt(0);
		}while(res!='l' && res!='z' && res!='s' && res!='q' && res!='d');
		return res;
	}
	
	private static Position attentionLeChasseurTire() {
		System.out.println("Saisissez la ligne à viser puis la colonne à viser");
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
			try {res=Integer.parseInt(tmp2);}
			catch(Exception e){correct=false;}
			if(res<0||res>max) {correct=false;}

		}
		return res;
	}

}
