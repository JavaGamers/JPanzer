package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
		int option = JOptionPane.showConfirmDialog(gameMode.getGameWin(),
				"Stai Stai uscendo dall'editor: vuoi salvare la mappa?",
				"EXIT EDITOR", JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		switch (option) {
		case (JOptionPane.YES_OPTION):
			yesOpt();
			break;
		case (JOptionPane.NO_OPTION):
			noOpt();
			break;

		}
	}

	private void yesOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra
		// e
		// aggiungo quelli nuovi
		c.removeAll();
		gameMode.getLandPanel().enableAll();
		gameMode.resetAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();

	}

	private void noOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		gameMode.getLandPanel().enableAll();
		gameMode.resetAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
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
