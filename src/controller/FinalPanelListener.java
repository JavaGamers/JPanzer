package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.GameWin;
import view.InitGame;

public class FinalPanelListener implements ActionListener {
	public static GameMode gameMode = GameMode.getGameMode();

	public void actionPerformed(ActionEvent e) {

		gameMode.resetAll();

		GameWin gameWin = gameMode.getGameWin();
		gameWin.setResizable(false);
		int height = gameWin.getBackgroundImage().getHeight(null);
		int width = gameWin.getBackgroundImage().getWidth(null);
		gameWin.setSize(width, height);
		Container c = gameWin.getContentPane();

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
		gameMode.getInitGame().getPreviewMap().setMappa(InitGame.DEFMAP);

	}
}
