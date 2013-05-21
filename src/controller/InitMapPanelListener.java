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
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.GameWin;
import view.LandPanel;

import model.Collina;
import model.Esagono;
import model.Foresta;
import model.Lago;
import model.Mappa;
import model.Montagna;
import model.Pianura;

public class InitMapPanelListener implements ActionListener, ListSelectionListener {
	public static GameMode gameMode = GameMode.getGameMode();
	private static final String NEWOPT = "new";
	private static final String EDITOPT = "edit";
	private static final String BACKOPT = "back";
	private static final String FORWARDOPT = "forward";
	private static int choosedOpt = 1; // dimensione mappa di default: media
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(NEWOPT)){
			newOpt();
		}
		else if(com.equals(EDITOPT)){
			editOpt();
		}
		else if(com.equals(BACKOPT)){
			backOpt();
		}
		else if(com.equals(FORWARDOPT)){
			forwardOpt();
		}

	}


	private void forwardOpt() {
		if(gameMode.getInitMapPanel().getDimList().isEnabled()){
		
			switch(choosedOpt){
			case 0: gameMode.setMappa(Mappa.SMALL);
					break;
			case 1: gameMode.setMappa(Mappa.MEDIUM);
					break;
			case 2: gameMode.setMappa(Mappa.LARGE);
					break;
			case 3: gameMode.setMappa(Mappa.EPIC);
					break;
				
			}
			
			if(gameMode.getMappaGrafica()==null){
				gameMode.createAndSetMappaGrafica();
			}
					
			GameWin gameWin = gameMode.getGameWin();
			LandPanel landPanel = gameMode.getLandPanel();
			
			Container c = gameWin.getContentPane();
			
			// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
			c.removeAll();
			c.add(landPanel, BorderLayout.EAST);
			c.add(gameMode.getMappaGrafica(),BorderLayout.CENTER);
			
			//ridisegno della finestra
			gameWin.repaint();
			gameWin.validate();
			
			//disattivo la JList
			gameMode.getInitMapPanel().getDimList().setEnabled(false);
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
	}


	private void editOpt() {
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
    				
    				//setto la nuova mappa 
    				gameMode.setMappa(m);
    				if(gameMode.getMappaGrafica()==null){
    					gameMode.createAndSetMappaGrafica();
    				}
    				
    				GameWin gameWin = gameMode.getGameWin();
    				LandPanel landPanel = gameMode.getLandPanel();
    				
    				Container c = gameWin.getContentPane();
    				
    				// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
    				c.removeAll();
    				c.add(gameMode.getMappaGrafica(),BorderLayout.WEST);
    				c.add(landPanel, BorderLayout.EAST);
    				//ridisegno della finestra
    				gameWin.repaint();
    				gameWin.validate();
    				
    			} 
    			catch (IOException ioException) {
    			}
    		}
        }
	}


	private void newOpt() {
		// rendo sensibile la JList di scelta
		gameMode.getInitMapPanel().setDimListEnable();
		
	}


	public void valueChanged(ListSelectionEvent e) {
		JList list = null;
		if( e.getSource() instanceof JList){
			list = (JList) e.getSource();
		}
		
		if (e.getValueIsAdjusting() == false){
			if(list.getSelectedIndex()!=-1){
				switch(list.getSelectedIndex()){
				case 0: choosedOpt=0;
						break;
				case 1: choosedOpt=1;
						break;
				case 2: choosedOpt=2;
						break;
				case 3: choosedOpt=3;
						break;
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
