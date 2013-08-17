package model;

import java.awt.Image;
import java.util.List;
import java.awt.image.BufferedImage;

import controller.Dijkstra;
import controller.GameMode;

public abstract class Unità {
	protected int att;	// attacco
	protected int dif;	// difesa
	protected int esp;	// esperienza valori da 1 a 10
	protected int passi;	// passi rimanenti
	protected double rig;	// rigenerazione
	protected double bonus;	// bonus territorio
	protected int numUnits;	// numero di unità
	protected Esagono pos;	//	posizione sulla mappa
	protected int player;	// 1=player 1 / 2=player 2
	protected BufferedImage bImg;
	protected List<Esagono> esagoniRaggiungibili;
	protected boolean alreadyAttack;
	public static GameMode gameMode = GameMode.getGameMode();
	
	/*
	 counter è una variabile che indica la correttezza di esagoniRaggiungibili:
	 counter = 0 esagoniRaggiungibili è coerente con la posizione dell'esagono
	 counter > 0 l'unità è stata spostata di counter volte senza aver aggiornato esagoniRaggiungibili
	 */
	protected int counter;
	
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
		this.counter = 0;
		this.alreadyAttack=false;
	}
	
	public boolean hasAlreadyAttack(){
		return this.alreadyAttack;
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
	
	public List<Esagono> getEsagoniRaggiungibili(){
		if(counter>0 || this.esagoniRaggiungibili==null){
			this.calcolaEsagoniRaggiungibili();
		}
		return this.esagoniRaggiungibili;
	}
	
	private void calcolaEsagoniRaggiungibili(){
		Mappa m = gameMode.getMappa();
		m.resetDistances();
		this.esagoniRaggiungibili = Dijkstra.shortestPath(m, pos, passi);
		this.counter=0;
	}
	
	public void setEsp(int e){
		if(e>10){
			throw new IllegalArgumentException("Valore massimo 10");
		}
		this.esp=e;
	}
	
	public void setAlreadyAttack(boolean value){
		this.alreadyAttack = value;
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
	}
	
	public void setPos(Esagono p){
		this.pos=p;
		if(p.getTerritorio()!=null){
			this.bonus=p.getTerritorio().getBonus();
		}
		this.counter++;
	}
	
	public abstract void resetPassi();
	
	public void setPassi(int passi){
		this.passi = passi;
	}
	
	// ritorna true se il set è andato a buon fine 
	public boolean aggiornaPassi(int p){
			boolean ok = true;
			if(this.passi<p){
				ok= false;
			}
			else{
				this.passi=this.passi-p;
			}
			
			return ok;
	}
	
	public boolean isSameUnitOf(Unità other){
		return this.getNome().equals(other.getNome());
	}
	
	public boolean equals(Unità other){
		boolean ok = false;
		if(this.pos.equals(other.pos))
			ok = true;
		
		return ok;
	}
}
