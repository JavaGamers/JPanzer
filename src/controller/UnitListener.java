package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Aereo;
import model.Artiglieria;
import model.Esagono;
import model.EsagonoGrafico;
import model.FanteriaLeggera;
import model.FanteriaPesante;
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
	private GameMode gM = GameMode.getGameMode();
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(FANTLEGGOPT)){
			fanteriaLeggOpt();
		}
		else if(com.equals(FANTPESOPT)){
			fanteriaPesOpt();
		}
		else if(com.equals(PANZEROPT)){
			panzerOpt();
		}
		else if(com.equals(AEREOOPT)){
			aereoOpt();
		}
		else if(com.equals(ARTIGLIERIAOPT)){
			artiglieriaOpt();
		}
		else if(com.equals(GIOCAOPT)){
			giocaOpt();
		}
	}


	private void giocaOpt() {
		// TODO Auto-generated method stub
		
	}

	
	private void artiglieriaOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Artiglieria(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
	}


	private void aereoOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Aereo(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void panzerOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new Panzer(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void fanteriaPesOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if(mG.getSelezionato()!=-1){
		e = mG.getMappa().getComponent()[mG.getSelezionato()];
		e.setUnit(new FanteriaPesante(10,1));

		eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
		mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
		}
		
	}


	private void fanteriaLeggOpt() {
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG = gW.getMappaGrafica();
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
