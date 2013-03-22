package model;

public class FanteriaPesante extends Unità {

	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

	public FanteriaPesante (int n){
		super(n);
		this.att=FanteriaPesante.BASEATT;
		this.dif=FanteriaPesante.BASEDIF;
		this.rig=FanteriaPesante.BASERIG;
		this.passi=FanteriaPesante.PTT;
		}
}
