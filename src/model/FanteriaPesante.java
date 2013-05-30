package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FanteriaPesante extends Unità {

	private final static int BASEATT=0;	// attacco di base
	private final static int BASEDIF=0;	// difesa di base
	private final static double BASERIG=0;	// rigenerazione di base	
	private final static int PTT=0;	// passi per turno
	public final static int COSTO = 100;

	public FanteriaPesante (int n, int player){
		super(n,player);
		this.att=FanteriaPesante.BASEATT;
		this.dif=FanteriaPesante.BASEDIF;
		this.rig=FanteriaPesante.BASERIG;
		this.passi=FanteriaPesante.PTT;
		if(this.player==1){
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Pesante1_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
		else{
			try {
		           bImg = ImageIO.read(new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Pesante2_Icon.gif"));
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
	}

	
	public String getNome() {
		
		return "fanteriapesante";
	}

	public void resetPassi() {
		this.passi=FanteriaPesante.PTT;
	}
}
