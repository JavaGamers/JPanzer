package model;

public class FanteriaLeggera extends Unità {
	
	final static int BASEATT=0;	// attacco di base
	final static int BASEDIF=0;	// difesa di base
	final static double BASERIG=0;	// rigenerazione di base	
	final static int PTT=0;	// passi per turno

	public FanteriaLeggera (int n){
		super(n);
		this.att=FanteriaLeggera.BASEATT;
		this.dif=FanteriaLeggera.BASEDIF;
		this.rig=FanteriaLeggera.BASERIG;
		this.passi=FanteriaLeggera.PTT;
		}

	
	public String getNome() {
	
		return "fanterialeggera";
	}

}
