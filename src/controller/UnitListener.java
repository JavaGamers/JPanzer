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
import model.Player;
import model.Unit‡;

import view.ErrorWindow;
import view.GameWin;
import view.MappaGrafica;
import view.UnitPanel;

public class UnitListener implements ActionListener {
	
	public final static String FANTLEGGOPT = "fanteriaLegg";
	public final static String FANTPESOPT = "fanteriaPes";
	public final static String PANZEROPT = "panzer";
	public final static String AEREOOPT = "aereo";
	public final static String ARTIGLIERIAOPT = "artiglieria";
	public final static String GIOCAOPT = "gioca";
	public static GameMode gameMode = GameMode.getGameMode();
	
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
		
	}

	
	private void artiglieriaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		Player player = gameMode.getPlayer(gameMode.getTurno());
		if(player.getSoldi()>=Artiglieria.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				if(e.getUnit()==null){
					e.setUnit(new Artiglieria(Unit‡.UNITACOMPRABILI,gameMode.getTurno()));
			
					eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
					mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
				
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Artiglieria.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
				
				}
				else if(e.getUnit() instanceof Artiglieria){
					int unit‡Prec = e.getUnit().getNumUnits();
					int unit‡Nuove =unit‡Prec+Unit‡.UNITACOMPRABILI;
					e.getUnit().setNumUnits(unit‡Nuove);
					
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Artiglieria.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
					
				}
				else{
					ErrorWindow ew = gameMode.getErrorWindow();
					ew.setErrorLabel("C'Ë gi‡ un'unit‡ di tipo diverso in questo territorio");
					ew.setVisible(true);
				}
			}
		}
		else{
			ErrorWindow ew = gameMode.getErrorWindow();
			ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unit‡");
			ew.setVisible(true);
		}
	}


	private void aereoOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		Player player = gameMode.getPlayer(gameMode.getTurno());
		if(player.getSoldi()>=Aereo.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				if(e.getUnit()==null){
					e.setUnit(new Aereo(Unit‡.UNITACOMPRABILI,gameMode.getTurno()));
			
					eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
					mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
				
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Aereo.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
				
				}
				else if(e.getUnit() instanceof Aereo){
					int unit‡Prec = e.getUnit().getNumUnits();
					int unit‡Nuove =unit‡Prec+Unit‡.UNITACOMPRABILI;
					e.getUnit().setNumUnits(unit‡Nuove);
					
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Aereo.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
					
				}
				else{
					ErrorWindow ew = gameMode.getErrorWindow();
					ew.setErrorLabel("C'Ë gi‡ un'unit‡ di tipo diverso in questo territorio");
					ew.setVisible(true);
				}
			}
		}
		else{
			ErrorWindow ew = gameMode.getErrorWindow();
			ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unit‡");
			ew.setVisible(true);
		}
		
	}


	private void panzerOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		Player player = gameMode.getPlayer(gameMode.getTurno());
		if(player.getSoldi()>=Panzer.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				if(e.getUnit()==null){
					e.setUnit(new Panzer(Unit‡.UNITACOMPRABILI,gameMode.getTurno()));
			
					eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
					mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
				
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Panzer.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
				
				}
				else if(e.getUnit() instanceof Panzer){
					int unit‡Prec = e.getUnit().getNumUnits();
					int unit‡Nuove =unit‡Prec+Unit‡.UNITACOMPRABILI;
					e.getUnit().setNumUnits(unit‡Nuove);
					
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - Panzer.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
					
				}
				else{
					ErrorWindow ew = gameMode.getErrorWindow();
					ew.setErrorLabel("C'Ë gi‡ un'unit‡ di tipo diverso in questo territorio");
					ew.setVisible(true);
				}
			}
		}
		else{
			ErrorWindow ew = gameMode.getErrorWindow();
			ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unit‡");
			ew.setVisible(true);
		}
		
	}


	private void fanteriaPesOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		Player player = gameMode.getPlayer(gameMode.getTurno());
		if(player.getSoldi()>=FanteriaPesante.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				if(e.getUnit()==null){
					e.setUnit(new FanteriaPesante(Unit‡.UNITACOMPRABILI,gameMode.getTurno()));
			
					eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
					mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
				
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - FanteriaPesante.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
				
				}
				else if(e.getUnit() instanceof FanteriaPesante){
					int unit‡Prec = e.getUnit().getNumUnits();
					int unit‡Nuove =unit‡Prec+Unit‡.UNITACOMPRABILI;
					e.getUnit().setNumUnits(unit‡Nuove);
					
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - FanteriaPesante.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
					
				}
				else{
					ErrorWindow ew = gameMode.getErrorWindow();
					ew.setErrorLabel("C'Ë gi‡ un'unit‡ di tipo diverso in questo territorio");
					ew.setVisible(true);
				}
			}
		}
		else{
			ErrorWindow ew = gameMode.getErrorWindow();
			ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unit‡");
			ew.setVisible(true);
		}
		
	}


	private void fanteriaLeggOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		Player player = gameMode.getPlayer(gameMode.getTurno());
		if(player.getSoldi()>=FanteriaLeggera.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				if(e.getUnit()==null){
					e.setUnit(new FanteriaLeggera(Unit‡.UNITACOMPRABILI,gameMode.getTurno()));
			
					eG = new EsagonoGrafico(mG.getSelezionato(),mG.getXCentro(),mG.getYCentro(),mG.getRaggio());
					mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());
				
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - FanteriaLeggera.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
				
				}
				else if(e.getUnit() instanceof FanteriaLeggera){
					int unit‡Prec = e.getUnit().getNumUnits();
					int unit‡Nuove =unit‡Prec+Unit‡.UNITACOMPRABILI;
					e.getUnit().setNumUnits(unit‡Nuove);
					
					//aggiorno i soldi del player
					player = gameMode.getPlayer(gameMode.getTurno());
					int soldiPrecedenti = player.getSoldi();
					int soldiNuovi = soldiPrecedenti - FanteriaLeggera.COSTO;
					player.setMoney(soldiNuovi);
					UnitPanel unitPanel = gameMode.getUnitPanel();
					unitPanel.setSoldiLabel(soldiNuovi);
					
				}
				else{
					ErrorWindow ew = gameMode.getErrorWindow();
					ew.setErrorLabel("C'Ë gi‡ un'unit‡ di tipo diverso in questo territorio");
					ew.setVisible(true);
				}
			}
		}
		else{
			ErrorWindow ew = gameMode.getErrorWindow();
			ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unit‡");
			ew.setVisible(true);
		}
	}
}
