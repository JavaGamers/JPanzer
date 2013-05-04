package model;

public class Aereo extends Unità {

	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

	public Aereo (int n){
		super(n);
		this.att=Aereo.BASEATT;
		this.dif=Aereo.BASEDIF;
		this.rig=Aereo.BASERIG;
		this.passi=Aereo.PTT;
		}

	
	public String getNome() {
		
		return "aereo";
	}
}
