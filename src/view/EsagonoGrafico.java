package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import model.Esagono;

public class EsagonoGrafico extends Polygon{
	private double xCentro, yCentro; 
	private double apotema, raggio; // raggio= raggio circonferenza circoscritta all'esagono
	private Esagono esagono;
	
	 /* le ascisse dei vertici dell'esagono sono in senso antiorario a partire da destra (asse x)
		le ordinate dei vertici dell'esagono sono in senso antiorario a partire da destra (asse x) 
	 	xC, yC coordinate dell'origine, ovvero del centro dell'esagono root (numero 0) */
	public EsagonoGrafico(Esagono e,double xC, double yC, double r){
		super();
		super.npoints=6;
		super.xpoints= new int[6];
		super.ypoints= new int[6];
		this.esagono=e;
		this.raggio=r;
		this.apotema=Math.sqrt(3)/2*r;
		
		int s = this.esagono.getSettore();
		int l = this.esagono.getLivello();
		int p = this.esagono.getPosizione();
		switch(s){
		case 0: this.xCentro=xC;
				this.yCentro=yC;
				break;
		
		case 1: this.xCentro = 3/2*raggio*p+raggio*p/2 +xC;
				this.yCentro = (2*l-p)*this.apotema+yC;
				break;
				
		case 2: this.xCentro = 3/2*raggio*l+l*raggio/2+xC;
				this.yCentro = (l-2*p)*this.apotema+yC;
				break;	
		
		case 3: this.xCentro = 3/2*raggio*(l-p)+(l-p)*raggio/2+xC;
				this.yCentro = -(l+p)*this.apotema+yC;
				break;	
				
		case 4: this.xCentro = -3/2*raggio*p-raggio*p/2+xC;
				this.yCentro = -(2*l-p)*this.apotema+yC;
				break;	
				
		case 5: this.xCentro = -3/2*raggio*l-l*raggio/2+xC;
				this.yCentro = -(l-2*p)*this.apotema+yC;
				break;
				
		case 6: this.xCentro = -3/2*raggio*(l-p)-(l-p)*raggio/2+xC;
				this.yCentro = (l+p)*this.apotema+yC;
				break;		
		}
		
		//riempio arrays coordinate
				super.xpoints[0]=(int)(this.xCentro+this.raggio);
				super.xpoints[1]=(int)(this.xCentro+this.raggio/2);
				super.xpoints[2]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[3]=(int)(this.xCentro-this.raggio);
				super.xpoints[4]=(int)(this.xCentro-this.raggio/2);
				super.xpoints[5]=(int)(this.xCentro+this.raggio/2);
				
				super.ypoints[0]=(int)this.yCentro;
				super.ypoints[1]=(int)(this.yCentro+this.apotema);
				super.ypoints[2]=(int)(this.yCentro+this.apotema);
				super.ypoints[3]=(int)this.yCentro;
				super.ypoints[4]=(int)(this.yCentro-this.apotema);
				super.ypoints[5]=(int)(this.yCentro-this.apotema);
	}
	
	public double getApotema(){
		return this.apotema;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2 = (Graphics2D)g;
		Image img = null;
		int height = 0, width=0;
		if(this.esagono.getTerritorio()!=null){
			img = esagono.getTerritorio().getImage();
			height=img.getHeight(null);
			width=img.getWidth(null);
		}
		g2.setClip(this);
		g2.clip(this);
		g2.drawImage(img,(int)(this.xCentro-width/2),(int)(this.yCentro-height/2), null);
		
		for(int i=0;i<5;i++){
			g.drawLine(this.xpoints[i], this.ypoints[i], this.xpoints[i+1], this.ypoints[i+1]);
		}
		g.drawLine(this.xpoints[0], this.ypoints[0], this.xpoints[5], this.ypoints[5]);
	}
	
	public Esagono getEsagono(){
		return this.esagono;
	}
	
}
