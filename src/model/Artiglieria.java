package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Artiglieria extends Unità {

	private final static int BASEATT=0;	// attacco di base
	private final static int BASEDIF=0;	// difesa di base
	private final static double BASERIG=0;	// rigenerazione di base	
	private final static int PTT=0;	// passi per turno
	public final static int COSTO = 100;

	public Artiglieria (int n, int player){
		super(n,player);
		this.att=Artiglieria.BASEATT;
		this.dif=Artiglieria.BASEDIF;
		this.rig=Artiglieria.BASERIG;
		this.passi=Artiglieria.PTT;
		if(this.player==1){
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Artiglieria1_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
		else{
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Artiglieria2_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
		}

	
	public String getNome() {
		
		return "artiglieria";
	}
}
