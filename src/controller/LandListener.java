package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Collina;
import model.Esagono;
import model.Foresta;
import model.Lago;
import model.Montagna;
import model.Pianura;

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

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		JButton source = (JButton) e.getSource();
		
		if(com.equals(PIANURAOPT)){
			pianuraOpt(source);
		}
		else if(com.equals(COLLINAOPT)){
			collinaOpt(source);
		}
		else if(com.equals(FORESTAOPT)){
			forestaOpt(source);
		}
		else if(com.equals(MONTAGNAOPT)){
			montagnaOpt(source);
		}
		else if(com.equals(LAGOOPT)){
			lagoOpt(source);
		}
		else if(com.equals(CARICAOPT)){
			caricaOpt();
		}
		else if(com.equals(SALVAOPT)){
			salvaOpt(source);
		}

	}

	private void pianuraOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Pianura());
		mG.paint(mG.getGraphics());
		}
	}

	private void lagoOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Lago());
		mG.paint(mG.getGraphics());
		}
	}

	private void montagnaOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Montagna());
		mG.paint(mG.getGraphics());
		}
	}

	private void collinaOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Collina());
		mG.paint(mG.getGraphics());
		}
	}

	private void forestaOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setTerritorio(new Foresta());
		mG.paint(mG.getGraphics());
		}
		
	}
	
	private void salvaOpt(JButton b){
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		
		File f = new File("C:/Users/Federico/Documents/GitHub/JPanzer/src/Mappe salvate/mappa.txt");
		FileWriter fw;
		BufferedWriter bw;
		
		try{
			
		fw = new FileWriter(f);
		bw = new BufferedWriter(fw);
		
		for(int i=0;i<mG.getMappa().getComponent().length;i++){
			bw.write(mG.getMappa().getComponent()[i].toString());
			bw.write("\n");
		}
		bw.close();
		fw.close();
		
		} catch(IOException io){
			System.out.println(io.toString());
		}
	}

	private void caricaOpt() {
		// TODO Auto-generated method stub
		
	}

}
