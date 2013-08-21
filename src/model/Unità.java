package model;

import java.awt.Image;
import java.util.List;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import controller.Dijkstra;
import controller.GameMode;

public abstract class Unità {
	protected int att;	// attacco
	protected int dif;	// difesa
	protected double esp;	// esperienza valori da 0 a 1 con passo 0.1
	protected int passi;	// passi rimanenti
	protected double rig;	// rigenerazione
	protected double bonus;	// bonus territorio
	protected int numUnits;	// numero di unità
	protected Esagono pos;	//	posizione sulla mappa
	protected int player;	// 1=player 1 / 2=player 2
	protected BufferedImage bImg;
	protected BufferedImage xImage;
	protected List<Esagono> esagoniRaggiungibili;
	protected boolean alreadyAttack;
	public static GameMode gameMode = GameMode.getGameMode();
	protected boolean isMoved;
	
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
		this.isMoved = false;
		this.alreadyAttack=false;
		
		if(this.player==1){
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Xrossa.png");
				xImage = ImageIO.read(imgUrl);
		       } catch (IOException e) {
		    	   // da scrivere
		       }
			
			gameMode.getPlayer(1).aggiungiUnità(this);
		}
		else{
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Xblu.png");
				xImage = ImageIO.read(imgUrl);
		       } catch (IOException e) {
		    	   // da scrivere
		       }
			gameMode.getPlayer(2).aggiungiUnità(this);
		}
	}
	
	public boolean hasAlreadyAttack(){
		return this.alreadyAttack;
	}
	
	public int getAtt(){
		return this.att;
	}
	
	public int getDef(){
		return this.dif;
	}
	
	public double getEsp(){
		return this.esp;
	}
	
	public int getPassi(){
		return this.passi;
	}
	
	public double getRig(){
		return this.rig;
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
	
	public Image getXImage(){
		return this.xImage;
	}
	
	public abstract String getNome();
	
	public int getPlayer(){
		return this.player;
	}
	
	public Image getImage(){
		return this.bImg;
	}
	
	public List<Esagono> getEsagoniRaggiungibili(){
		if(isMoved || this.esagoniRaggiungibili==null){
			this.calcolaEsagoniRaggiungibili();
		}
		return this.esagoniRaggiungibili;
	}
	
	private void calcolaEsagoniRaggiungibili(){
		Mappa m = gameMode.getMappa();
		m.resetDistances();
		this.esagoniRaggiungibili = Dijkstra.shortestPath(m, pos, passi);
		this.isMoved=false;
	}
	
	public void setEsp(double e){
		if(e>1){
			throw new IllegalArgumentException("Valore massimo 1");
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
		if(this.esp+0.1<=1)
			this.esp+=0.1;
		else
			this.esp=1;
	}
	
	public void setPos(Esagono p){
		this.pos=p;
		if(p.getTerritorio()!=null){
			this.bonus=p.getTerritorio().getBonus();
		}
		this.isMoved=true;
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
