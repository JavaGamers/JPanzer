package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import controller.Dijkstra;

public class Aereo extends Unità {

	private final static int BASEATT=0;	// attacco di base
	private final static int BASEDIF=0;	// difesa di base
	private final static double BASERIG=0;	// rigenerazione di base	
	private final static int PTT=2;	// passi per turno
	public final static int COSTO = 100;

	public Aereo (int n, int player){
		super(n,player);
		this.att=Aereo.BASEATT;
		this.dif=Aereo.BASEDIF;
		this.rig=Aereo.BASERIG;
		this.passi=Aereo.PTT;
		if(this.player==1){
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Aereo1_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
		else{
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Aereo2_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
	}
	
	public void resetPassi(){
		this.passi=Aereo.PTT;
	}
	
	public List<Esagono> getEsagoniRaggiungibili(){
		if(counter>0 || this.esagoniRaggiungibili==null){
			Mappa m = gameMode.getMappa();
			m.resetDistances();
			List<Esagono> l = new LinkedList<Esagono>();
			calcolaEsagoniRaggiungibili(this.passi,this.pos,l,0);
			this.esagoniRaggiungibili = l;
			this.counter=0;
		}
		return this.esagoniRaggiungibili;
	}
	
	private static List<Esagono> calcolaEsagoniRaggiungibili(int passi, Esagono e, List<Esagono> l,int distance){
		if(passi==0){
			if(!l.contains(e)){
				e.setDistance(distance);
				l.add(e);
			}
		}
		else{
			for(int i = 0; i<6; i++){
				if(e.getAdiacenze()[i]!=null){
					calcolaEsagoniRaggiungibili(passi-1,e.getAdiacenze()[i], l, distance++);
				}
			}
		}
		return l;
	}

	
	public String getNome() {
		return "aereo";
	}
}
