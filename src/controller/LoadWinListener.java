package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameWin;

public class LoadWinListener implements ActionListener {

	private GameMode gameMode = GameMode.getGameMode();

	public final static String SIOPT = "si";
	public final static String NOOPT = "no";
	public final static String ANNULLAOPT = "annulla";

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(SIOPT)) {
			siOpt();
		} else if (com.equals(NOOPT)) {
			noOpt();
		} else if (com.equals(ANNULLAOPT)) {
			annullaOpt();
		}
	}

	private void annullaOpt() {
		gameMode.getLoadWin().setVisible(false);
		gameMode.getCommandPanel().enableAll();

	}

	private void noOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		gameMode.getLoadWin().setVisible(false);

		if (gameMode.caricaPartita()) {
			gameWin = gameMode.getGameWin();
			c = gameWin.getContentPane();
			c.removeAll();
			c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
			c.add(gameMode.getMappaGrafica(), BorderLayout.CENTER);
			c.repaint();
			c.validate();
		}
		gameMode.getCommandPanel().enableAll();
	}

	private void siOpt() {
		if (gameMode.salvaPartita()) {

			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();

			gameMode.getLoadWin().setVisible(false);

			if (gameMode.caricaPartita()) {
				gameWin = gameMode.getGameWin();
				c = gameWin.getContentPane();
				c.removeAll();
				c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
				c.add(gameMode.getMappaGrafica(), BorderLayout.CENTER);
				c.repaint();
				c.validate();
			}
		}
		gameMode.getCommandPanel().enableAll();
	}
}
