package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

import model.Esagono;

public class EsagonoGrafico extends Drawing implements Observer {
	private double xCentro, yCentro; 
	private double apotema, raggio; // raggio= raggio circonferenza circoscritta all'esagono
	private double[] xPoint; // ascisse dei vertici dell'esagono in senso antiorario a partire da destra (asse x)
	private double[] yPoint; // ordinate dei vertici dell'esagono in senso antiorario a partire da destra (asse x)
	private Esagono esagono;
	
	// xC, yC coordinate dell'origine, ovvero del centro dell'esagono root (numero 0)
	public EsagonoGrafico(Esagono e,double xC, double yC, double r){
		this.esagono=e;
		this.raggio=r;
		this.apotema=Math.sqrt(3)/2*r;
		this.xPoint= new double[6];
		this.yPoint= new double[6];
		
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
				this.xPoint[0]=this.xCentro+this.raggio;
				this.xPoint[1]=this.xCentro+this.raggio/2;
				this.xPoint[2]=this.xCentro-this.raggio/2;
				this.xPoint[3]=this.xCentro-this.raggio;
				this.xPoint[4]=this.xCentro-this.raggio/2;
				this.xPoint[5]=this.xCentro+this.raggio/2;
				
				this.yPoint[0]=this.yCentro;
				this.yPoint[1]=this.yCentro+this.apotema;
				this.yPoint[2]=this.yCentro+this.apotema;
				this.yPoint[3]=this.yCentro;
				this.yPoint[4]=this.yCentro-this.apotema;
				this.yPoint[5]=this.yCentro-this.apotema;
		
	}
	
	public double getApotema(){
		return this.apotema;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2d= (Graphics2D) g;
		Shape s;
		Image img = this.esagono.getTerritorio().getImage();
		g.drawImage(img,(int)(this.xCentro-this.raggio),(int)(this.yCentro-this.raggio), null);
		
		for(int i=0;i<5;i++){
			s = new Line2D.Double(this.xPoint[i], this.yPoint[i], this.xPoint[i+1], this.yPoint[i+1]);
			g2d.draw(s);
		}
		g2d.draw(new Line2D.Double(this.xPoint[0], this.yPoint[0], this.xPoint[5], this.yPoint[5]));
	}
	
	public Esagono getEsagono(){
		return this.esagono;
	}

	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	
}
