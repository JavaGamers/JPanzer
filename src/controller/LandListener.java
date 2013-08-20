package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
import model.EsagonoGrafico;

import view.GameWin;
import view.MappaGrafica;

public class LandListener implements ActionListener {
	
	public final static String PIANURAOPT = "pianura";
	public final static String COLLINAOPT = "collina";
	public final static String FORESTAOPT = "foresta";
	public final static String MONTAGNAOPT = "montagna";
	public final static String LAGOOPT = "lago";
	public final static String SALVAOPT = "salva";
	public final static String CARICAOPT = "carica";
	public final static String MAINMENUOPT = "main";
	public static GameMode gameMode = GameMode.getGameMode();

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(PIANURAOPT)){
			pianuraOpt();
		}
		else if(com.equals(COLLINAOPT)){
			collinaOpt();
		}
		else if(com.equals(FORESTAOPT)){
			forestaOpt();
		}
		else if(com.equals(MONTAGNAOPT)){
			montagnaOpt();
		}
		else if(com.equals(LAGOOPT)){
			lagoOpt();
		}
		else if(com.equals(CARICAOPT)){
			caricaOpt();
		}
		else if(com.equals(SALVAOPT)){
			salvaOpt();
		}
		else if(com.equals(MAINMENUOPT)){
			mainMenuOpt();
		}

	}

	private void mainMenuOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getStartPanel(),BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
		
	}

	private void pianuraOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if (m.getSelezionato() != -1) {
			e = m.getComponent()[m.getSelezionato()];
			e.setTerritorio(new Pianura());

			eG = new EsagonoGrafico(m.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void lagoOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(m.getSelezionato()!=-1){
		e = m.getComponent()[m.getSelezionato()];
		e.setTerritorio(new Lago());

		eG = new EsagonoGrafico(m.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void montagnaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(m.getSelezionato()!=-1){
		e = m.getComponent()[m.getSelezionato()];
		e.setTerritorio(new Montagna());

		eG = new EsagonoGrafico(m.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void collinaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(m.getSelezionato()!=-1){
		e = m.getComponent()[m.getSelezionato()];
		e.setTerritorio(new Collina());

		eG = new EsagonoGrafico(m.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void forestaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(m.getSelezionato()!=-1){
		e = m.getComponent()[m.getSelezionato()];
		e.setTerritorio(new Foresta());

		eG = new EsagonoGrafico(m.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
		
	}
	
	private void salvaOpt(){
		MappaGrafica mG = gameMode.getMappaGrafica();
		JFileChooser jfc = new JFileChooser();		
		int returnVal = jfc.showSaveDialog(mG);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            FileWriter fw;
    		BufferedWriter bw;
    		
    		try{
    			
    		fw = new FileWriter(file);
    		bw = new BufferedWriter(fw);
    		
    		bw.write(gameMode.getMappa().toString());
    		
    		bw.close();
    		fw.close();
    		
    		} catch(IOException io){
    			System.out.println(io.toString());
    		}
        }
	}

	private void caricaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(mG);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            BufferedReader br;
    		String[] elements;
    		if (file.isFile()) {
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
    				
    				//setto la nuova mappa nel pannello
    				mG.setMappa(m);
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
