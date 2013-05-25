package controller;

import java.awt.BorderLayout;
import java.awt.Container;
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
import model.Unità;

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
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		if(!gameMode.isPlayingMode()){
			int turno = gameMode.getTurno();
			if(turno==1){
				gameMode.switchTurno();
				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();
				c.removeAll();
				gameMode.getUnitPanel().updateLabel();
				c.add(gameMode.getUnitPanel(), BorderLayout.EAST);
				c.add(mappaGrafica, BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();
			}
			else{
				gameMode.switchTurno();
				gameMode.setSelectionUnitMode(false);
				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();
				c.removeAll();
				// ora inizia il gioco
				gameMode.setPlayingMode(true);
				c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
				c.add(mappaGrafica, BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();
			}
		}
		else{
			gameMode.setSelectionUnitMode(false);
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.removeAll();
			// ricomincia il gioco
			gameMode.setPlayingMode(true);
			c.add(mappaGrafica, BorderLayout.CENTER);
			c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
			gameWin.repaint();
			gameWin.validate();
		}
	}

	
	private void artiglieriaOpt() {
		
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno =gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		
		if(player.getSoldi()>=Artiglieria.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					if(e.getUnit()==null){
						e.setUnit(new Artiglieria(Unità.UNITACOMPRABILI,gameMode.getTurno()));
				
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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove =unitàPrec+Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);
						
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
						ew.setErrorLabel("C'è già un'unità di tipo diverso in questo territorio");
						ew.setVisible(true);
					}
				}
			}
			else{
				ErrorWindow ew = gameMode.getErrorWindow();
				ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unità");
				ew.setVisible(true);
			}
		}
	}


	private void aereoOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno =gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		
		if(player.getSoldi()>=Aereo.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					if(e.getUnit()==null){
						e.setUnit(new Aereo(Unità.UNITACOMPRABILI,gameMode.getTurno()));
				
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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove =unitàPrec+Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);
						
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
						ew.setErrorLabel("C'è già un'unità di tipo diverso in questo territorio");
						ew.setVisible(true);
					}
				}
			}
			else{
				ErrorWindow ew = gameMode.getErrorWindow();
				ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unità");
				ew.setVisible(true);
			}
		}
	}


	private void panzerOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno =gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		
		if(player.getSoldi()>=Panzer.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					if(e.getUnit()==null){
						e.setUnit(new Panzer(Unità.UNITACOMPRABILI,gameMode.getTurno()));
				
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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove =unitàPrec+Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);
						
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
						ew.setErrorLabel("C'è già un'unità di tipo diverso in questo territorio");
						ew.setVisible(true);
					}
				}
			}
			else{
				ErrorWindow ew = gameMode.getErrorWindow();
				ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unità");
				ew.setVisible(true);
			}
		}
	}


	private void fanteriaPesOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno =gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		if(player.getSoldi()>=FanteriaPesante.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					if(e.getUnit()==null){
						e.setUnit(new FanteriaPesante(Unità.UNITACOMPRABILI,gameMode.getTurno()));
				
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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove =unitàPrec+Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);
						
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
						ew.setErrorLabel("C'è già un'unità di tipo diverso in questo territorio");
						ew.setVisible(true);
					}
				}
			}
			else{
				ErrorWindow ew = gameMode.getErrorWindow();
				ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unità");
				ew.setVisible(true);
			}
		}	
	}


	private void fanteriaLeggOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno =gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		if(player.getSoldi()>=FanteriaLeggera.COSTO){
			if(mG.getSelezionato()!=-1){
				e = mG.getMappa().getComponent()[mG.getSelezionato()];
				
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					if(e.getUnit()==null){
						e.setUnit(new FanteriaLeggera(Unità.UNITACOMPRABILI,gameMode.getTurno()));
				
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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove =unitàPrec+Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);
						
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
						ew.setErrorLabel("C'è già un'unità di tipo diverso in questo territorio");
						ew.setVisible(true);
					}
				}
			}
			else{
				ErrorWindow ew = gameMode.getErrorWindow();
				ew.setErrorLabel("Non hai abbastanza soldi per comprare quest'unità");
				ew.setVisible(true);
			}
		}
	}
}
