package controller;

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
	private GameMode gM = GameMode.getGameMode();

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

	}

	private void pianuraOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if (mG.getSelezionato() != -1) {
			e = mG.getMappa().getComponent()[mG.getSelezionato()];
			e.setTerritorio(new Pianura());

			eG = new EsagonoGrafico(mG.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void lagoOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Lago());

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void montagnaOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Montagna());

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void collinaOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Collina());

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void forestaOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Foresta());

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
		
	}
	
	private void salvaOpt(){
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		JFileChooser jfc = new JFileChooser();		
		int returnVal = jfc.showSaveDialog(mG);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            FileWriter fw;
    		BufferedWriter bw;
    		
    		try{
    			
    		fw = new FileWriter(file);
    		bw = new BufferedWriter(fw);
    		
    		bw.write(""+mG.getMappa().getDim());
    		bw.write("\n");
    		for(int i=0;i<mG.getMappa().getComponent().length;i++){
    			bw.write(mG.getMappa().getComponent()[i].saveToString());
    			bw.write("\n");
    		}
    		bw.close();
    		fw.close();
    		
    		} catch(IOException io){
    			System.out.println(io.toString());
    		}
        }
	}

	private void caricaOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
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
