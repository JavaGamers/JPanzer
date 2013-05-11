package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import model.Aereo;
import model.Artiglieria;
import model.Esagono;
import model.EsagonoGrafico;
import model.FanteriaLeggera;
import model.FanteriaPesante;
import model.Lago;
import model.Panzer;
import view.GameWin;
import view.MappaGrafica;

public class UnitListener implements ActionListener {
	
	public final static String FANTLEGGOPT = "fanteriaLegg";
	public final static String FANTPESOPT = "fanteriaPes";
	public final static String PANZEROPT = "panzer";
	public final static String AEREOOPT = "aereo";
	public final static String ARTIGLIERIAOPT = "artiglieria";
	public final static String GIOCAOPT = "gioca";
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		JButton source = (JButton) e.getSource();
		
		if(com.equals(FANTLEGGOPT)){
			fanteriaLeggOpt(source);
		}
		else if(com.equals(FANTPESOPT)){
			fanteriaPesOpt(source);
		}
		else if(com.equals(PANZEROPT)){
			panzerOpt(source);
		}
		else if(com.equals(AEREOOPT)){
			aereoOpt(source);
		}
		else if(com.equals(ARTIGLIERIAOPT)){
			artiglieriaOpt(source);
		}
		else if(com.equals(GIOCAOPT)){
			giocaOpt();
		}
	}


	private void giocaOpt() {
		// TODO Auto-generated method stub
		
	}

	
	private void artiglieriaOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Artiglieria(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
	}


	private void aereoOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Aereo(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void panzerOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Panzer(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void fanteriaPesOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new FanteriaPesante(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void fanteriaLeggOpt(JButton b) {
		GameWin dW = (GameWin) SwingUtilities.getRoot(b);
		MappaGrafica mG = dW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new FanteriaLeggera(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}

}
