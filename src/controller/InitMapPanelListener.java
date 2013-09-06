package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.GameWin;
import view.LandPanel;

import model.Mappa;
import model.Pianura;

public class InitMapPanelListener implements ActionListener,
		ListSelectionListener {
	public static GameMode gameMode = GameMode.getGameMode();
	private static final String NEWOPT = "new";
	private static final String EDITOPT = "edit";
	private static final String BACKOPT = "back";
	private static final String FORWARDOPT = "forward";
	private static int choosedOpt = 1; // dimensione mappa di default: media

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(NEWOPT)) {
			newOpt();
		} else if (com.equals(EDITOPT)) {
			editOpt();
		} else if (com.equals(BACKOPT)) {
			backOpt();
		} else if (com.equals(FORWARDOPT)) {
			forwardOpt();
		}

	}

	private void forwardOpt() {
		if (gameMode.getInitMapPanel().getDimList().isEnabled()) {

			switch (choosedOpt) {
			case 0:
				gameMode.setMappa(Mappa.SMALL);
				break;
			case 1:
				gameMode.setMappa(Mappa.MEDIUM);
				break;
			case 2:
				gameMode.setMappa(Mappa.LARGE);
				break;
			case 3:
				gameMode.setMappa(Mappa.EPIC);
				break;

			}
			
			// la nuova mappa invece che essere vuota viene creata con esagoni Pianura
			Mappa m = gameMode.getMappa();
			for(int i=0;i<m.getComponent().length;i++){
				m.getComponent()[i].setTerritorio(new Pianura());
			}

			if (gameMode.getMappaGrafica() == null) {
				gameMode.createAndSetMappaGrafica();
			}

			GameWin gameWin = gameMode.getGameWin();
			gameWin.setResizable(true);
			gameWin.setExtendedState(JFrame.MAXIMIZED_BOTH);
			LandPanel landPanel = gameMode.getLandPanel();

			Container c = gameWin.getContentPane();

			/*
			 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
			 * e aggiungo quelli nuovi
			 */
			c.removeAll();
			c.add(landPanel, BorderLayout.EAST);
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(gameMode.getMappaGrafica());
			jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			c.add(jsp, BorderLayout.CENTER);

			gameWin.repaint();
			gameWin.validate();

			// disattivo la JList
			gameMode.getInitMapPanel().getDimList().setEnabled(false);
		}
	}

	private void backOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		/*
		 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
		 * e aggiungo quelli nuovi
		 */
		c.removeAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
	}

	private void editOpt() {
		if (gameMode.caricaMappa()) {
			GameWin gameWin = gameMode.getGameWin();
			gameWin.setResizable(true);
			gameWin.setExtendedState(JFrame.MAXIMIZED_BOTH);
			LandPanel landPanel = gameMode.getLandPanel();

			Container c = gameWin.getContentPane();

			/*
			 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
			 * e aggiungo quelli nuovi
			 */
			c.removeAll();
			c.add(landPanel, BorderLayout.EAST);
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(gameMode.getMappaGrafica());
			jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			c.add(jsp, BorderLayout.CENTER);;

			gameWin.repaint();
			gameWin.validate();
		}
	}

	private void newOpt() {
		// rendo sensibile la JList di scelta
		gameMode.getInitMapPanel().setDimListEnable();

	}

	//Metodo di gesione della JList
	public void valueChanged(ListSelectionEvent e) {
		JList list = null;
		if (e.getSource() instanceof JList) {
			list = (JList) e.getSource();
		}

		if (e.getValueIsAdjusting() == false) {
			if (list.getSelectedIndex() != -1) {
				switch (list.getSelectedIndex()) {
				case 0:
					choosedOpt = 0;
					break;
				case 1:
					choosedOpt = 1;
					break;
				case 2:
					choosedOpt = 2;
					break;
				case 3:
					choosedOpt = 3;
					break;
				}
			}
		}
	}
}
