package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

import view.GameWin;

public class SwitchPanelListener extends MouseAdapter {

	public static GameMode gameMode = GameMode.getGameMode();

	public void mouseClicked(MouseEvent mE) {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		c.removeAll();

		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(gameMode.getMappaGrafica());
		jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
		c.add(jsp, BorderLayout.CENTER);

		// ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();
	}

}
