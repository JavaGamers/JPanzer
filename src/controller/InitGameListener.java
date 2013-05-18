package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

import model.Collina;
import model.Esagono;
import model.Foresta;
import model.Lago;
import model.Mappa;
import model.Montagna;
import model.Pianura;
import model.Player;

import view.ErrorWindow;
import view.GameWin;
import view.InitGame;
import view.MappaGrafica;
import view.UnitPanel;

public class InitGameListener implements ActionListener {
	public static GameMode gameMode = GameMode.getGameMode();
	private static final String CHOOSEMAPOPT = "chooseMap";
	private static final String FORWARDOPT = "forward";
	private static final String BACKOPT = "back";
	private static boolean scelta = false;  //variabile usata per verificare se la mappa è stata scelta o no 
	private static boolean error = false; // variabile usata per verificare se ci sono errori
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(CHOOSEMAPOPT)){
			chooseMapOpt();
		}
		else if(com.equals(FORWARDOPT)){
			forwardOpt();
		}
		else if(com.equals(BACKOPT)){
			backOpt();
		}

	}

	private void backOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getStartPanel(),BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
		gameMode.getInitGame().getPreviewMap().setMappa(InitGame.DEFMAP);
		
	}

	private void forwardOpt() {
		error = false;
		if(!scelta){
			ErrorWindow errorWindow = gameMode.getErrorWindow();
			errorWindow.getErrorLabel().setText(ErrorWindow.DEFTEXT + "non hai scelto la mappa!");
			errorWindow.setVisible(true);
			error=true;
		}
		else{
			gameMode.setMappa(gameMode.getInitGame().getPreviewMap().getMappa());
			if(gameMode.getMappaGrafica()==null){
				gameMode.createAndSetMappaGrafica();
			}
		}
		
		//gestisco il player 1
		//gestisco il nome
		String txt1 = gameMode.getInitGame().getTextFieldNome(1).getText();
		Player p1=null;
			
		if(txt1.equals(null) ||txt1.equals("")){
			p1 = new Player("Player 1",1);
		}
		else{
			p1 = new Player(txt1,1);
		}
		gameMode.setPlayer(p1,1);
		
		//gestisco soldi
		int soldi1 = Integer.parseInt(gameMode.getInitGame().getTextFieldSoldi(1).getText());
		if(soldi1<Player.MINMONEY || soldi1>Player.MAXMONEY){
			ErrorWindow errorWindow = gameMode.getErrorWindow();
			errorWindow.getErrorLabel().setText(ErrorWindow.DEFTEXT + "Valore soldi player 1 errato!");
			errorWindow.setVisible(true);
			error= true;
				
		}
		else{
			gameMode.getPlayer(1).setMoney(soldi1);
		}
					
			
		//gestisco il plyer 2
		//gestisco il nome
		String txt2 = gameMode.getInitGame().getTextFieldNome(2).getText();
		Player p2=null;
			
		if(txt2.equals(null) ||txt2.equals("")){
			p2 = new Player("Player 2",2);
		}
		else{
			p2 = new Player(txt2,2);
		}
		gameMode.setPlayer(p2,2);
			
		//gestisco soldi
			
		int soldi2 = Integer.parseInt(gameMode.getInitGame().getTextFieldSoldi(2).getText());
			
		if(soldi2<Player.MINMONEY || soldi2>Player.MAXMONEY){
			ErrorWindow errorWindow = gameMode.getErrorWindow();
			errorWindow.getErrorLabel().setText(ErrorWindow.DEFTEXT + "Valore soldi player 2 errato!");
			errorWindow.setVisible(true);
			error= true;
			
		}
		else{
			gameMode.getPlayer(2).setMoney(soldi2);
		}
		
		if(!error){
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			if(gameMode.getUnitPanel()==null){
				gameMode.createAndSetUnitPanel();
			}
			UnitPanel unitPanel = gameMode.getUnitPanel();
			
			// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
			c.removeAll();
			c.add(unitPanel, BorderLayout.EAST);
			c.add(gameMode.getMappaGrafica(),BorderLayout.WEST);
			
			//ridisegno della finestra
			gameWin.repaint();
			gameWin.validate();
		}
		
	}

	private void chooseMapOpt() {
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(gameMode.getInitMapPanel());
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            BufferedReader br;
    		String[] elements;
    		if (file.isFile()){
    			try {
    				br = new BufferedReader(new FileReader(file));
    				// leggo la 1° riga del file
    				String text = br.readLine();
    				int dim = Integer.parseInt(text);
    				Mappa m = new Mappa(dim);
    				Esagono e;
    				//leggo le altre righe
    				while ((text = br.readLine()) != null){
    					elements=getElements(text);
    					e = m.getComponent()[Integer.parseInt(elements[0])];
    					// setto il territorio dell'esagono
    					if(elements[1].equals("Pianura")){
    						e.setTerritorio(new Pianura());
    					}
    					else if(elements[1].equals("Collina")){
    						e.setTerritorio(new Collina());
    					}
    					else if(elements[1].equals("Montagna")){
    						e.setTerritorio(new Montagna());
    					}
    					else if(elements[1].equals("Lago")){
    						e.setTerritorio(new Lago());
    					}
    					else if(elements[1].equals("Foresta")){
    						e.setTerritorio(new Foresta());
    					}
    					else{
    						e.setTerritorio(null);
    					}
    				}
    				br.close();
    				
    				// setto la nuova mappa nell'anteprima
    				MappaGrafica anteprima = gameMode.getInitGame().getPreviewMap();
    				GameWin gameWin = gameMode.getGameWin();
    				anteprima.setMappa(m);
    				scelta = true;
    				anteprima.paint(anteprima.getGraphics());
    				gameWin.repaint();
    				gameWin.validate();
    			}
    			catch (IOException ioException) {
    			}
    		}
        }
	}
	
	private static String[] getElements(String s){
		String[] elements= new String[6];
		StringTokenizer str = new StringTokenizer(s,"-");
		int i=0;
		while(str.hasMoreTokens()){
			elements[i]= str.nextToken();
			i++;
		}
		return elements;
	}
}
