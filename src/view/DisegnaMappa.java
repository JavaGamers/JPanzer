package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.Mappa;

public class DisegnaMappa extends Drawing {
	private Mappa mappa;
	
	public DisegnaMappa(Mappa m){
		this.mappa=m;
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2d= (Graphics2D) g;
		for(int i=0;i<this.mappa.getComponent().length;i++){
			
		}

	}

}
