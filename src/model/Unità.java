package model;

import java.awt.Image;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Observable;

import controller.Nodo;

public abstract class Unità extends Observable {
	protected int att;	// attacco
	protected int dif;	// difesa
	protected int esp;	// esperienza
	protected int passi;	// passi rimanenti
	protected double rig;	// rigenerazione
	protected double bonus;	// bonus territorio
	protected int numUnits;	// numero di unità
	protected Esagono pos;	//	posizione sulla mappa
	protected int player;	// 1=player 1 - 2= player 2
	protected BufferedImage bImg;
	protected List<Nodo> esagoniRaggiungibili;
	
	public static final int UNITACOMPRABILI = 10;
	
	public Unità(int n, int player){
		if(player<1 || player>2){
			throw new IllegalArgumentException("Si gioca in 2");
		}
		this.att=0;
		this.dif=0;
		this.rig=0;
		this.passi=0;
		this.esp=0;
		this.numUnits=n;
		this.pos=null;
		this.bonus=0;
		this.player=player;
		this.bImg=null;
		this.esagoniRaggiungibili=null;
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
	
	public abstract String getNome();
	
	public int getPlayer(){
		return this.player;
	}
	
	public Image getImage(){
		return this.bImg;
	}
	
	public List<Nodo> getEsagoniRaggiungibili(){
		return this.esagoniRaggiungibili;
	}
	
	public void setEsagoniRaggiungibili(List<Nodo> list){
		this.esagoniRaggiungibili= list;
	}
	
	public void setEsp(int e){
		if(e>10){
			throw new IllegalArgumentException("Valore massimo 10");
		}
		this.esp=e;
	}
	
	public void setNumUnits(int n){
		if(n<0){
			throw new IllegalArgumentException("non ha senso avere numero di unità negative");
		}
		this.numUnits = n;
	}
	
	public void updateEsp(){
		if(this.esp<10)
			this.esp++;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setPos(Esagono p){
		this.pos=p;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setAtt(){
		// da fare
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setDif(){
		// da fare
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setBonus(){
		// da fare
		this.setChanged();
		this.notifyObservers();
	}
	
	
	
	// ritorna true se il set è andato a buon fine 
	public boolean aggiornaPassi(int p){
			boolean ok = true;
			if(this.passi<p)
				ok= false;
			else
				this.passi=this.passi-p;
			
			this.setChanged();
			this.notifyObservers();
			return ok;
	}
	
	

}
