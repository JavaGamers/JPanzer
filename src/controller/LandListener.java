package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Collina;
import model.Esagono;
import model.Foresta;
import model.Lago;
import model.Mappa;
import model.Montagna;
import model.Pianura;
import model.EsagonoGrafico;
import view.CommandPanel;
import view.GameWin;
import view.LeavingWin;
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
	public final static String ZOOMOPT = "zoom";
	public static GameMode gameMode = GameMode.getGameMode();

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(PIANURAOPT)) {
			pianuraOpt();
		} else if (com.equals(COLLINAOPT)) {
			collinaOpt();
		} else if (com.equals(FORESTAOPT)) {
			forestaOpt();
		} else if (com.equals(MONTAGNAOPT)) {
			montagnaOpt();
		} else if (com.equals(LAGOOPT)) {
			lagoOpt();
		} else if (com.equals(ZOOMOPT)) {
			zoomOpt();
		} else if (com.equals(SALVAOPT)) {
			salvaOpt();
		} else if (com.equals(MAINMENUOPT)) {
			mainMenuOpt();
		}

	}

	private void zoomOpt() {
		GameWin gameWin = gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		if (mG.getRaggio() == MappaGrafica.STDRAGGIO) {
			gameMode.setZoomOutMode(true);
			mG.setRaggio(MappaGrafica.ZOOMRAGGIO);
		} else {
			gameMode.setZoomOutMode(false);
			mG.setRaggio(MappaGrafica.STDRAGGIO);
		}
		gameWin.repaint();
		mG.validate();
	}

	private void mainMenuOpt() {
		gameMode.getLandPanel().silenceAll();
		LeavingWin leavingWin = gameMode.getLeavingWin();
		leavingWin.setTextLabel(LeavingWin.EXITMAPMSG);
		leavingWin.setVisible(true);

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
		if (m.getSelezionato() != -1) {
			e = m.getComponent()[m.getSelezionato()];
			e.setTerritorio(new Lago());

			eG = new EsagonoGrafico(m.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void montagnaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if (m.getSelezionato() != -1) {
			e = m.getComponent()[m.getSelezionato()];
			e.setTerritorio(new Montagna());

			eG = new EsagonoGrafico(m.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void collinaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if (m.getSelezionato() != -1) {
			e = m.getComponent()[m.getSelezionato()];
			e.setTerritorio(new Collina());

			eG = new EsagonoGrafico(m.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void forestaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		if (m.getSelezionato() != -1) {
			e = m.getComponent()[m.getSelezionato()];
			e.setTerritorio(new Foresta());

			eG = new EsagonoGrafico(m.getSelezionato(), mG.getXCentro(),
					mG.getYCentro(), mG.getRaggio());
			mG.paintImage(mG.getGraphics(), eG, e.getTerritorio().getImage());
		}
	}

	private void salvaOpt() {
		gameMode.salvaMappa();
	}
}
