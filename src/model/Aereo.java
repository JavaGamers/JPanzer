package model;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

public class Aereo extends Unità {

	private final static int BASEATT=80;	// attacco di base
	private final static int BASEDIF=40;	// difesa di base
	private final static int PPT=5;	// passi per turno
	public final static int COSTO = 75;

	public Aereo (int n, int player){
		super(n,player);
		this.att=Aereo.BASEATT;
		this.dif=Aereo.BASEDIF;
		this.passi=Aereo.PPT;
		if(this.player==1){
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Aereo1_Icon.png");
				bImg = ImageIO.read(imgUrl);
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
		else{
			try {
				URL imgUrl=getClass().getResource("/view/Icon pack/Unit Pack/Aereo2_Icon.png");
				bImg = ImageIO.read(imgUrl);
		       } catch (IOException e) {
		    	   // da scrivere
		       }
		}
	}
	
	public void resetPassi(){
		this.passi=Aereo.PPT;
	}
	
	public List<Esagono> getEsagoniRaggiungibili(){
		if(this.isMoved || this.esagoniRaggiungibili==null){
			Mappa m = gameMode.getMappa();
			m.resetDistances();
			List<Esagono> l = new LinkedList<Esagono>();
			calcolaEsagoniRaggiungibili(this.passi,this.pos,l,0);
			this.esagoniRaggiungibili = l;
			this.isMoved=false;
		}
		return this.esagoniRaggiungibili;
	}
	
	private static List<Esagono> calcolaEsagoniRaggiungibili(int passi, Esagono e, List<Esagono> l,int distance){
		if(passi==0){
			if(!l.contains(e)){
				e.setDistance(distance);
				l.add(e);
			}
		}
		else{
			for(int i = 0; i<6; i++){
				if(e.getAdiacenze()[i]!=null){
					calcolaEsagoniRaggiungibili(passi-1,e.getAdiacenze()[i], l, distance++);
				}
			}
		}
		return l;
	}
	
	public String getNome() {
		return "aereo";
	}
	
	//attacco e difesa sono relativi ad una singola unità (numUnits = 1)
	public int getAtt(){
		return (int)(BASEATT*(1+this.esp));
	}
	
	public int getDef(){
		return (int)(BASEDIF*(1+this.esp)*this.bonus);
	}
}