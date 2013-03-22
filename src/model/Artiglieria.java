package model;

public class Artiglieria extends Unità {

	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

	public Artiglieria (int n){
		super(n);
		this.att=Artiglieria.BASEATT;
		this.dif=Artiglieria.BASEDIF;
		this.rig=Artiglieria.BASERIG;
		this.passi=Artiglieria.PTT;
		}
}
