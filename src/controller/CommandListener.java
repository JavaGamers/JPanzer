package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Popup;

import model.Esagono;
import model.Mappa;
import model.Player;
import model.Unit�;
import view.CommandPanel;
import view.GameWin;
import view.MappaGrafica;

public class CommandListener implements ActionListener {
	public final static String ZOOMOPT = "zoom";
	public final static String MUOVIOPT = "muovi";
	public final static String ATTACCAOPT = "attacca";
	public final static String ABBANDONAOPT = "abbandona";
	public final static String SALVAOPT = "salva";
	public final static String CARICAOPT = "carica";
	public final static String SCORPORAOPT = "scorpora";
	public final static String ACCORPAOPT = "accorpa";
	public final static String PASSAOPT = "passa";
	public final static String SHOPOPT = "shop";
	public static GameMode gameMode = GameMode.getGameMode();

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(ZOOMOPT)) {
			zoomOpt();
		} else if (com.equals(MUOVIOPT)) {
			muoviOpt();
		} else if (com.equals(ABBANDONAOPT)) {
			abbandonaOpt();
		} else if (com.equals(ACCORPAOPT)) {
			accorpaOpt();
		} else if (com.equals(ATTACCAOPT)) {
			attaccaOpt();
		} else if (com.equals(CARICAOPT)) {
			caricaOpt();
		} else if (com.equals(PASSAOPT)) {
			passaOpt();
		} else if (com.equals(SALVAOPT)) {
			salvaOpt();
		} else if (com.equals(SCORPORAOPT)) {
			scorporaOpt();
		} else if (com.equals(SHOPOPT)) {
			shopOpt();
		}
	}

	/*
	 * Se clicco il pulsante shop deve comparire lo UnitPanel e devo entrare in
	 * selectionUnitMode
	 */
	private void shopOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		if (gameMode.isAccorpaMode() || gameMode.isAttackMode()
				|| gameMode.isMovingMode() || gameMode.isScorporaMode()) {

			gameMode.setAccorpaMode(false);
			gameMode.setAttackMode(false);
			gameMode.setMovingMode(false);
			gameMode.setScorporaMode(false);
			c.repaint();
			c.validate();
		}

		gameMode.setSelectionUnitMode(true);

		c.removeAll();
		gameMode.getUnitPanel().updateLabel();
		JScrollPane jsp = new JScrollPane();
		jsp.setViewportView(gameMode.getMappaGrafica());
		jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		c.add(gameMode.getUnitPanel(), BorderLayout.EAST);
		c.add(jsp, BorderLayout.CENTER);
		c.repaint();
		c.validate();
	}

	/*
	 * Se clicco il pulsante scorpora, se l'operazione � possibile, devono
	 * evidenziarsi gli esagoni che potrebbero ospitare una nuova unit� e si
	 * deve entrare in selectionUnitMode
	 */
	private void scorporaOpt() {

		if (gameMode.isAccorpaMode() || gameMode.isAttackMode()
				|| gameMode.isMovingMode() || gameMode.isSelecionUnitMode()) {

			gameMode.setAccorpaMode(false);
			gameMode.setAttackMode(false);
			gameMode.setMovingMode(false);
			gameMode.setSelectionUnitMode(false);
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = null;
		int turno = gameMode.getTurno();
		Unit� unitSelected = null;
		Esagono adiacenza = null;
		Unit� u = null;
		if (m.getSelezionato() != -1) {
			selected = m.getComponent()[m.getSelezionato()];
			unitSelected = selected.getUnit();
		}
		
		/*
		 * L'operazione di scorporamento � possibile solo se
		 * -si � selezionata una unit�
		 * -questa appartiene al player di turno
		 * -ed ha pi� di una unit�
		 */
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno) {
				if (unitSelected.getNumUnits() > 1) {
					
					List<Esagono> esagoniRaggiungibili = unitSelected
							.getEsagoniRaggiungibili();
					/*
					 * Scorro le adiacenze dell'esagono per trovare quelli
					 * adatti ad ospitare la nuova unit�. Questi devono essere
					 * vuoti e raggiungibili
					 */
					for (int i = 0; i < 6 && !gameMode.isScorporaMode(); i++) {
						adiacenza = selected.getAdiacenze()[i];
						if (esagoniRaggiungibili.contains(adiacenza)) {
							u = adiacenza.getUnit();
							if (u == null) {
								gameMode.setScorporaMode(true);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(gameMode.getGameWin(),
							"Ti � rimasta una sola unit�!", "ERRORE!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unit�!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
		}
		if(gameMode.isScorporaMode()){
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.repaint();
			c.validate();
		}
	}

	private void salvaOpt() {
		gameMode.salvaPartita();
	}

	// metodo per il passa turno
	private void passaOpt() {

		gameMode.setAccorpaMode(false);
		gameMode.setAttackMode(false);
		gameMode.setMovingMode(false);
		gameMode.setScorporaMode(false);
		gameMode.setSelectionUnitMode(false);

		// resettiamo i valori delle unit�
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		Unit� u = null;
		for (int i = 0; i < m.getComponent().length; i++) {
			e = m.getComponent()[i];
			u = e.getUnit();
			if (u != null) {
				u.setAlreadyAttack(false);
				u.resetPassi();
			}
		}

		// aggiorniamo i soldi del player che ha passato il turno
		Player p = gameMode.getPlayer(gameMode.getTurno());
		p.setMoney(p.getSoldi() + Player.MONEYPERTURNO);

		// cambia turno (ovviamente)
		gameMode.switchTurno();

		// settiamo le label del CommandPanel
		p = gameMode.getPlayer(gameMode.getTurno());
		gameMode.getCommandPanel().setPlayerLabel(p);

		// aggiorno le label dello SwitchPanel
		gameMode.getSwitchPanel().updateLabel();

		// faccio comparire lo SwitchPanel
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		c.removeAll();
		c.add(gameMode.getSwitchPanel(), BorderLayout.CENTER);

		// elimino l'ultimo popup se c'�
		Popup popup = MappaGrafica.getPopup();
		if (popup != null) {
			popup.hide();
		}

		// ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();
	}

	private void caricaOpt() {
		int option = JOptionPane
				.showConfirmDialog(
						gameMode.getGameWin(),
						"Stai abbandonando la partita corrente: vuoi salvare prima di uscire?",
						"LOAD GAME", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
		switch (option) {
		case (JOptionPane.YES_OPTION):
			yesOptLoad();
			break;
		case (JOptionPane.NO_OPTION):
			noOptLoad();
			break;

		}
	}

	private void yesOptLoad() {
		if (gameMode.salvaPartita()) {

			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();

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
	}

	private void noOptLoad() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();

		if (gameMode.caricaPartita()) {
			gameWin = gameMode.getGameWin();
			c = gameWin.getContentPane();
			c.removeAll();
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(gameMode.getMappaGrafica());
			jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
			c.add(jsp, BorderLayout.CENTER);
			c.repaint();
			c.validate();
		}
	}

	/*
	 * Se clicco il pulsante attacca, se l'operazione � possibile, devono
	 * evidenziarsi gli esagoni contenenti un'unit� nemica adiacente a quella
	 * selezionata e si deve entrare in attackMode
	 */
	private void attaccaOpt() {

		if (gameMode.isAccorpaMode() || gameMode.isMovingMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAccorpaMode(false);
			gameMode.setMovingMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = null;
		int turno = gameMode.getTurno();
		Unit� unitSelected = null;
		Esagono adiacenza = null;
		Unit� u = null;
		if (m.getSelezionato() != -1) {
			selected = m.getComponent()[m.getSelezionato()];
			unitSelected = selected.getUnit();
		}
		/*
		 * Si pu� entrare in attackMode solo se:
		 * -si � selezionata una unit�
		 * -questa � del player di turno
		 * -questa non ha gi� attaccato
		 */
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno
					&& !unitSelected.hasAlreadyAttack()) {
				
				/*
				 * Scorro le adiacenze dell'esagono per trovare quelli
				 * contenenti un'unit� nemica.
				 */
				for (int i = 0; i < 6 && !gameMode.isAttackMode(); i++) {
					adiacenza = selected.getAdiacenze()[i];
					if (adiacenza != null) {
						u = adiacenza.getUnit();
						if (u != null && u.getPlayer() != turno) {
							gameMode.setAttackMode(true);						
						}
					}
				}
			}
		}
		
		if(gameMode.isAttackMode()){
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.repaint();
			c.validate();
		}		
	}

	/*
	 * Se clicco il pulsante accorpa, se l'operazione � possibile, devono
	 * evidenziarsi gli esagoni contenenti un'unit� del player di turno adiacente a quella
	 * selezionata e si deve entrare in accorpaMode
	 */
	private void accorpaOpt() {

		if (gameMode.isAttackMode() || gameMode.isMovingMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAttackMode(false);
			gameMode.setMovingMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = null;
		int turno = gameMode.getTurno();
		Unit� unitSelected = null;
		Esagono adiacenza = null;
		Unit� u = null;
		if (m.getSelezionato() != -1) {
			selected = m.getComponent()[m.getSelezionato()];
			unitSelected = selected.getUnit();
		}
		
		/*
		 * Si pu� entrare in accorpaMode solo se:
		 * -si � selezionata una unit�
		 * -questa � del player di turno
		 */
		
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno) {
				
				/*
				 * Scorro le adiacenze dell'esagono per trovare quelli
				 * contenenti un'unit� nemica. Questi devono essere
				 * raggiungibili
				 */
				List<Esagono> esagoniRaggiungibili = unitSelected
						.getEsagoniRaggiungibili();
				for (int i = 0; i < 6 && !gameMode.isAccorpaMode(); i++) {
					adiacenza = selected.getAdiacenze()[i];
					if (esagoniRaggiungibili.contains(adiacenza)) {
						u = adiacenza.getUnit();
						if (u != null && unitSelected.isSameUnitOf(u)
								&& u.getPlayer() == turno) {
							gameMode.setAccorpaMode(true);
						}
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unit�!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
		}
		if(gameMode.isAccorpaMode()){
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.repaint();
			c.validate();
		}
	}

	private void abbandonaOpt() {
		int option = JOptionPane.showConfirmDialog(gameMode.getGameWin(),
				"Stai uscendo dal gioco: vuoi salvare la partita?",
				"EXIT GAME", JOptionPane.YES_NO_CANCEL_OPTION,
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
		if (gameMode.salvaPartita()) {

			if (MappaGrafica.getPopup() != null) {
				MappaGrafica.getPopup().hide();
			}

			GameWin gameWin = gameMode.getGameWin();
			gameWin.setResizable(false);
			int height = gameWin.getBackgroundImage().getHeight(null);
			int width = gameWin.getBackgroundImage().getWidth(null);
			gameWin.setSize(width, height);
			Container c = gameWin.getContentPane();

			/*
			 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
			 * e aggiungo quelli nuovi
			 */
			c.removeAll();
			gameMode.getLandPanel().enableAll();
			gameMode.resetAll();
			c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
			gameWin.repaint();
			gameWin.validate();
		}
	}

	private void noOpt() {

		if (MappaGrafica.getPopup() != null) {
			MappaGrafica.getPopup().hide();
		}

		GameWin gameWin = gameMode.getGameWin();
		gameWin.setResizable(false);
		int height = gameWin.getBackgroundImage().getHeight(null);
		int width = gameWin.getBackgroundImage().getWidth(null);
		gameWin.setSize(width, height);
		Container c = gameWin.getContentPane();

		/*
		 *  rimuovo gli eventuali altri pannelli presenti sulla finestra
		 * e aggiungo quelli nuovi
		 */
		c.removeAll();
		gameMode.getLandPanel().enableAll();
		gameMode.resetAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
	}

	/*
	 * Se clicco il pulsante muovi, se l'operazione � possibile, devono
	 * evidenziarsi gli esagoni raggiungibili dall'unit� e si deve entrare in moveMode
	 */
	private void muoviOpt() {

		
		if (gameMode.isAttackMode() || gameMode.isAccorpaMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAttackMode(false);
			gameMode.setAccorpaMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = null;
		int turno = gameMode.getTurno();
		Unit� u = null;
		if (m.getSelezionato() != -1) {
			selected = m.getComponent()[m.getSelezionato()];
			u = selected.getUnit();
		}

		/*
		 * Si pu� entrare in moveMode solo se:
		 * -si � selezionata una unit�
		 * -questa � del player di turno
		 */
		if (u != null) {
			if (u.getPlayer() == turno) {
				gameMode.setMovingMode(true);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unit�!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
		}
		if(gameMode.isMovingMode()){
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.repaint();
			c.validate();
		}
	}

	private void zoomOpt() {
		GameWin gameWin = gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		CommandPanel commandPanel = gameMode.getCommandPanel();
		if (mG.getRaggio() == MappaGrafica.STDRAGGIO) {
			gameMode.setZoomOutMode(true);
			mG.setRaggio(MappaGrafica.ZOOMRAGGIO);
			commandPanel.silenceAll();
		} else {
			gameMode.setZoomOutMode(false);
			mG.setRaggio(MappaGrafica.STDRAGGIO);
			commandPanel.enableAll();
		}
		gameWin.repaint();
		gameWin.validate();
	}
}
