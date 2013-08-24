package view;

import javax.swing.JFrame;

import model.Sound;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import controller.GameMode;

public class GameWin extends JFrame{
	public static GameMode gameMode = GameMode.getGameMode();
	
	public GameWin(String title){
		super(title);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(1024,768));
		super.setLayout(new BorderLayout());
		super.pack();
		Sound sound = gameMode.getSound();
		sound.startThemeMusic();
	}

	public static void main(String[] args) {
		GameWin gameWin = new GameWin("JPanzer");
		gameMode.setGameWin(gameWin);
		Container c = gameWin.getContentPane();
		c.add(gameMode.getStartPanel());
		gameWin.setVisible(true);
		
	}
	
	public void paint(Graphics g){
		if(gameMode.getMappaGrafica()!=null){
			MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
			mappaGrafica.newSet(mappaGrafica.getWidth()/2, mappaGrafica.getHeight()/2);
		}
		super.paint(g);
	}
}
