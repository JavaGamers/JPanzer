package model;

public class Panzer extends Unità {

	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

	public Panzer (int n){
		super(n);
		this.att=Panzer.BASEATT;
		this.dif=Panzer.BASEDIF;
		this.rig=Panzer.BASERIG;
		this.passi=Panzer.PTT;
		}

	public String getNome() {
		
		return "panzer";
	}
}
