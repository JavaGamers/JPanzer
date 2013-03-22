package model;

public class Unità {
	protected int att;	// attacco
	protected int dif;	// difesa
	protected int esp;	// esperienza
	protected int passi;	// passi rimanenti
	protected double rig;	// rigenerazione
	protected double bonus;	// bonus territorio
	protected int numUnits;	// numero di unità
	protected Esagono pos;	//	posizione sulla mappa
	
	public Unità(int n){
		this.att=0;
		this.dif=0;
		this.rig=0;
		this.passi=0;
		this.esp=0;
		this.numUnits=n;
		this.pos=null;
		this.bonus=0;
	}
	
	public int getAtt(){
		return this.att;
	}
	
	public int getDif(){
		return this.dif;
	}
	
	public int getEsp(){
		return this.esp;
	}
	
	public int getPassi(){
		return this.passi;
	}
	
	public double getRig(){
		return this.att;
	}
	
	public double getBonus(){
		return this.bonus;
	}
	
	public int getNumUnits(){
		return this.numUnits;
	}
	
	public Esagono getPos(){
		return this.pos;
	}
	
	public void setEsp(){
		if(this.esp<10)
			this.esp++;
	}
	
	public void setPos(Esagono p){
		this.pos=p;
	}
	
	public void setAtt(){
		// da fare
	}
	
	public void setDif(){
		// da fare
	}
	
	public void setBonus(){
		// da fare
	}
	
	// ritorna true se il set è andato a buon fine 
	public boolean aggiornaPassi(int p){
			boolean ok = true;
			if(this.passi<p)
				ok= false;
			else
				this.passi=this.passi-p;
			return ok;
	}

}
