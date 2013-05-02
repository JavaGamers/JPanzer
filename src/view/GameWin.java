package view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import model.Mappa;
import model.Montagna;
import model.Territorio;

public class GameWin extends JFrame{
	
	private InfoPanel infoPanel;
	private CommandPanel commandPanel;
	private MappaGrafica mappaGrafica;
	private UnitPanel unitPanel;
	
	
	public GameWin(String title, Mappa m){
		super(title);
		initComponents(m);
	}
	
	public InfoPanel getInfoPanel(){
		return this.infoPanel;
	}
	
	public CommandPanel getCommandPanel(){
		return this.commandPanel;
	}
	
	public MappaGrafica getMappaGrafica(){
		return this.mappaGrafica;
	}
		
	private void initComponents(Mappa m) {
		infoPanel = new InfoPanel();
		commandPanel = new CommandPanel();
		mappaGrafica = new MappaGrafica(m,700,500);
		unitPanel = new UnitPanel();
		
		Container c = this.getContentPane();
	//	c.add(commandPanel,BorderLayout.EAST);
		c.add(unitPanel,BorderLayout.EAST);
		c.add(infoPanel,BorderLayout.NORTH);
		c.add(mappaGrafica,BorderLayout.CENTER);
		
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setPreferredSize(new Dimension(800,600));
		super.pack();
		mappaGrafica.newSet(mappaGrafica.getWidth()/2, mappaGrafica.getHeight()/2);
		
	}

	public static void main(String[] args) {
		Mappa m = new Mappa(4);
		Territorio t = new Montagna();
		for(int i=0;i<m.getComponent().length;i++){
			m.getComponent()[i].setTerritorio(t);
		}

		GameWin gW=new GameWin("Java meglio",m);
		gW.setVisible(true);
	}
	
	public void paint(Graphics g){
		mappaGrafica.newSet(mappaGrafica.getWidth()/2, mappaGrafica.getHeight()/2);
		super.paint(g);
	}
}