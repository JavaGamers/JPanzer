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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.GameWin;

import model.Collina;
import model.Esagono;
import model.Foresta;
import model.Lago;
import model.Mappa;
import model.Montagna;
import model.Pianura;

public class InitMapPanelListener implements ActionListener, ListSelectionListener {
	private GameMode gameMode = GameMode.getGameMode();
	private static final String NEWOPT = "nuova";
	private static final String EDITOPT = "edit";
	private static final String BACKOPT = "back";
	private static final String FORWARDOPT = "forward";
	
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
	
		
	}


	private void backOpt() {
		// TODO Auto-generated method stub
		
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
    				gameMode.createAndSetMappaGrafica();
    				GameWin gameWin = gameMode.getGameWin();
    				Container c = gameWin.getContentPane();
    				c.add(gameMode.getMappaGrafica(),BorderLayout.WEST);
    				
    			} 
    			catch (IOException ioException) {
    			}
    		}
        }
	}


	private void newOpt() {
		gameMode.getInitMapPanel().setDimListEnable();
		
	}


	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		if (!lsm.isSelectionEmpty()){
            	if (lsm.isSelectedIndex(0)){
            		gameMode.createAndSetMappa(Mappa.SMALL);
            	}
            	else if (lsm.isSelectedIndex(1)){
            		gameMode.createAndSetMappa(Mappa.MEDIUM);
            	}
            	else if (lsm.isSelectedIndex(2)){
            		gameMode.createAndSetMappa(Mappa.LARGE);
            	}
            	else if (lsm.isSelectedIndex(3)){
            		gameMode.createAndSetMappa(Mappa.EPIC);
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
