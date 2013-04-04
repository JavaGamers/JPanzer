package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class DisegnaEsagono extends Drawing {
	private double xCentro, yCentro; 
	private double apotema, raggio; // raggio= raggio circonferenza circoscritta all'esagono
	private double[] xPoint; // ascisse dei vertici dell'esagono in senso antiorario a partire da destra (asse x)
	private double[] yPoint; // ordinate dei vertici dell'esagono in senso antiorario a partire da destra (asse x)
	
	public DisegnaEsagono(double x, double y, double r){
		this.xCentro=x;
		this.yCentro=y;
		this.raggio=r;
		this.apotema=Math.sqrt(3)/2*r;
		this.xPoint= new double[6];
		this.yPoint= new double[6];
		
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
		for(int i=0;i<5;i++){
			s = new Line2D.Double(this.xPoint[i], this.yPoint[i], this.xPoint[i+1], this.yPoint[i+1]);
			g2d.draw(s);
		}
		g2d.draw(new Line2D.Double(this.xPoint[0], this.yPoint[0], this.xPoint[5], this.yPoint[5]));
	}

	
}
