package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import controller.GameMode;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import model.Mappa;

public class GameWin extends JFrame{
	
	private CommandPanel commandPanel;
	private MappaGrafica mappaGrafica;
	private UnitPanel unitPanel;
	private LandPanel landPanel;
	
	
	public GameWin(String title, Mappa m){
		super(title);
		initComponents(m);
	}
	
	public CommandPanel getCommandPanel(){
		return this.commandPanel;
	}
	
	public MappaGrafica getMappaGrafica(){
		return this.mappaGrafica;
	}
		
	private void initComponents(Mappa m) {
		commandPanel = new CommandPanel();
		mappaGrafica = new MappaGrafica(m,700,500);
		unitPanel = new UnitPanel();
		landPanel = new LandPanel();
		mappaGrafica.setPreferredSize(new Dimension(800,800));		
		
		Container c = this.getContentPane();
	//	c.add(commandPanel,BorderLayout.EAST);
	//	c.add(unitPanel,BorderLayout.EAST);
		c.add(landPanel,BorderLayout.EAST);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(800,600));
		super.pack();
		mappaGrafica.newSet(mappaGrafica.getWidth()/2, mappaGrafica.getHeight()/2);
		
	}

	public static void main(String[] args) {
		Mappa m = new Mappa(4);
		GameWin gW=new GameWin("Java meglio",m);
		GameMode gM = GameMode.getGameMode();
		gM.setMappa(m);
		gM.setGameWin(gW);
		gW.setVisible(true);
	}
	
	public void paint(Graphics g){
		mappaGrafica.newSet(mappaGrafica.getWidth()/2, mappaGrafica.getHeight()/2);
		super.paint(g);
	}
}
