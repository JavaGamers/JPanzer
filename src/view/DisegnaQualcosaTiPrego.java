package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import model.Mappa;

public class DisegnaQualcosaTiPrego extends JFrame {
	private Drawing draw;
	
	public DisegnaQualcosaTiPrego(String title, Drawing d){
		super(title);
		this.draw = d;
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(800,600));
		super.pack();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		draw.paint(g);
	}
	
	
	public static void main(String[] args) {
		Mappa m = new Mappa(3);
		Drawing dM = new DisegnaMappa(m,700,400);
		new DisegnaQualcosaTiPrego("Java meglio", dM).setVisible(true);
	}

}
