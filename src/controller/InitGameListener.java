package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JViewport;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Player;
import view.GameWin;
import view.InitGame;
import view.MappaGrafica;
import view.UnitPanel;

public class InitGameListener implements ActionListener, ChangeListener {
	public static GameMode gameMode = GameMode.getGameMode();
	private static final String CHOOSEMAPOPT = "chooseMap";
	private static final String FORWARDOPT = "forward";
	private static final String BACKOPT = "back";
	private static boolean scelta = false; // variabile usata per verificare se
											// la mappa è stata scelta o no
	private static boolean error = false; // variabile usata per verificare se
											// ci sono errori

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(CHOOSEMAPOPT)) {
			chooseMapOpt();
		} else if (com.equals(FORWARDOPT)) {
			forwardOpt();
		} else if (com.equals(BACKOPT)) {
			backOpt();
		}

	}

	private void backOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
		gameMode.getInitGame().getPreviewMap().setMappa(InitGame.DEFMAP);

	}

	private void forwardOpt() {
		error = false;
		if (!scelta) {
			JOptionPane.showMessageDialog(gameMode.getGameWin(), "Non hai scelto la mappa", "ERRORE!", JOptionPane.ERROR_MESSAGE);
		} else {
			gameMode.setMappa(gameMode.getInitGame().getPreviewMap().getMappa());
			if (gameMode.getMappaGrafica() == null) {
				gameMode.createAndSetMappaGrafica();
			}
			gameMode.setSelectionUnitMode(true);

			// gestisco il player 1
			// gestisco il nome
			String txt1 = gameMode.getInitGame().getTextFieldNome(1).getText();
			Player p1 = null;

			if (txt1.equals(null) || txt1.equals("")) {
				p1 = new Player("Player 1", 1);
			} else {
				p1 = new Player(txt1, 1);
			}
			gameMode.setPlayer(p1, 1);

			// gestisco soldi
			int soldi1 = Integer.parseInt(gameMode.getInitGame()
					.getTextFieldSoldi(1).getText());
			if (soldi1 < Player.MINMONEY || soldi1 > Player.MAXMONEY) {
				JOptionPane.showMessageDialog(gameMode.getGameWin(), "Valore soldi Player 1 errato!", "ERRORE!", JOptionPane.ERROR_MESSAGE);

			} else {
				gameMode.getPlayer(1).setMoney(soldi1);
			}

			// gestisco il plyer 2
			// gestisco il nome
			String txt2 = gameMode.getInitGame().getTextFieldNome(2).getText();
			Player p2 = null;

			if (txt2.equals(null) || txt2.equals("")) {
				p2 = new Player("Player 2", 2);
			} else {
				p2 = new Player(txt2, 2);
			}
			gameMode.setPlayer(p2, 2);

			// gestisco soldi

			int soldi2 = Integer.parseInt(gameMode.getInitGame()
					.getTextFieldSoldi(2).getText());

			if (soldi2 < Player.MINMONEY || soldi2 > Player.MAXMONEY) {
				JOptionPane.showMessageDialog(gameMode.getGameWin(), "Valore soldi Player 2 errato!", "ERRORE!", JOptionPane.ERROR_MESSAGE);

			} else {
				gameMode.getPlayer(2).setMoney(soldi2);
			}

			if (!error) {
				GameWin gameWin = gameMode.getGameWin();
				gameWin.setResizable(true);
				gameWin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Container c = gameWin.getContentPane();
				UnitPanel unitPanel = gameMode.getUnitPanel();

				// rimuovo gli eventuali altri pannelli presenti sulla finestra
				// e aggiungo quelli nuovi
				c.removeAll();
				c.add(unitPanel, BorderLayout.EAST);
				JScrollPane jsp = new JScrollPane();
				jsp.setViewportView(gameMode.getMappaGrafica());
				jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
				c.add(jsp, BorderLayout.CENTER);

				// ridisegno della finestra
				gameWin.repaint();
				gameWin.validate();
			}
		}
	}

	private void chooseMapOpt() {
		if (gameMode.caricaMappa()) {
			// setto la nuova mappa nell'anteprima
			MappaGrafica anteprima = gameMode.getInitGame().getPreviewMap();
			GameWin gameWin = gameMode.getGameWin();
			anteprima.setMappa(gameMode.getMappa());
			scelta = true;
			anteprima.paint(anteprima.getGraphics());
			gameWin.repaint();
			gameWin.validate();
		}
	}

	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		if (source.equals(gameMode.getInitGame().getSlider(1))) {
			if (!source.getValueIsAdjusting()) {
				int soldi = source.getValue();
				gameMode.getInitGame().getTextFieldSoldi(1).setText("" + soldi);
			}
		} else {
			if (!source.getValueIsAdjusting()) {
				int soldi = source.getValue();
				gameMode.getInitGame().getTextFieldSoldi(2).setText("" + soldi);
			}
		}
	}
}
