package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Aereo extends Unità {

	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

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

	
	public String getNome() {
		
		return "aereo";
	}
}
