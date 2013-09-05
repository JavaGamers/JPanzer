package controller;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Popup;

import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;
import model.Player;
import model.Unità;
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
	 * Se clicco il pulsante scorpora, se l'operazione è possibile, devono
	 * evidenziarsi gli esagoni che potrebbero ospitare una nuova unità e si
	 * deve entrare in selectionUnitMode
	 */
	private void scorporaOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		if (gameMode.isAccorpaMode() || gameMode.isAttackMode()
				|| gameMode.isMovingMode() || gameMode.isSelecionUnitMode()) {

			gameMode.setAccorpaMode(false);
			gameMode.setAttackMode(false);
			gameMode.setMovingMode(false);
			gameMode.setSelectionUnitMode(false);
			c.repaint();
			c.validate();
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = m.getComponent()[m.getSelezionato()];
		int turno = gameMode.getTurno();
		Unità unitSelected = selected.getUnit();
		Esagono adiacenza = null;
		Unità u = null;
		/*
		 * L'operazione di scorporamento è possibile solo se
		 * -si è selezionata una unità
		 * -questa appartiene al player di turno
		 * -ed ha più di una unità
		 */
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno) {
				if (unitSelected.getNumUnits() > 1) {
					MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
					int xC = mappaGrafica.getXCentro();
					int yC = mappaGrafica.getYCentro();
					double raggio = MappaGrafica.STDRAGGIO;
					int id = 0;
					EsagonoGrafico eG = new EsagonoGrafico(id, xC, yC, raggio);
					Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
					List<Esagono> esagoniRaggiungibili = unitSelected
							.getEsagoniRaggiungibili();
					/*
					 * Scorro le adiacenze dell'esagono per trovare quelli
					 * adatti ad ospitare la nuova unità. Questi devono essere
					 * vuoti e raggiungibili
					 */
					for (int i = 0; i < 6; i++) {
						adiacenza = selected.getAdiacenze()[i];
						if (esagoniRaggiungibili.contains(adiacenza)) {
							u = adiacenza.getUnit();
							if (u == null) {
								eG.newSet(adiacenza.getId(), xC, yC, raggio);
								g2.setColor(Color.MAGENTA);
								g2.setStroke(new BasicStroke(3));
								g2.draw(eG);
								gameMode.setScorporaMode(true);
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(gameMode.getGameWin(),
							"Ti è rimasta una sola unità!", "ERRORE!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unità!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
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

		// resettiamo i valori delle unità
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		Unità u = null;
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

		// elimino l'ultimo popup se c'è
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
			c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
			c.add(gameMode.getMappaGrafica(), BorderLayout.CENTER);
			c.repaint();
			c.validate();
		}
	}

	/*
	 * Se clicco il pulsante attacca, se l'operazione è possibile, devono
	 * evidenziarsi gli esagoni contenenti un'unità nemica adiacente a quella
	 * selezionata e si deve entrare in attackMode
	 */
	private void attaccaOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		if (gameMode.isAccorpaMode() || gameMode.isMovingMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAccorpaMode(false);
			gameMode.setMovingMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
			c.repaint();
			c.validate();
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = m.getComponent()[m.getSelezionato()];
		int turno = gameMode.getTurno();
		Unità unitSelected = selected.getUnit();
		Esagono adiacenza = null;
		Unità u = null;
		/*
		 * Si può entrare in attackMode solo se:
		 * -si è selezionata una unità
		 * -questa è del player di turno
		 * -questa non ha già attaccato
		 */
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno
					&& !unitSelected.hasAlreadyAttack()) {
				MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
				int xC = mappaGrafica.getXCentro();
				int yC = mappaGrafica.getYCentro();
				double raggio = MappaGrafica.STDRAGGIO;
				int id = 0;
				EsagonoGrafico eG = new EsagonoGrafico(id, xC, yC, raggio);
				Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();

				/*
				 * Scorro le adiacenze dell'esagono per trovare quelli
				 * contenenti un'unità nemica. Questi devono essere
				 * raggiungibili
				 */
				for (int i = 0; i < 6; i++) {
					adiacenza = selected.getAdiacenze()[i];
					if (adiacenza != null) {
						id = adiacenza.getId();
						u = adiacenza.getUnit();
						if (u != null && u.getPlayer() != turno) {
							eG.newSet(id, xC, yC, raggio);
							g2.setColor(Color.MAGENTA);
							g2.setStroke(new BasicStroke(3));
							g2.draw(eG);
							gameMode.setAttackMode(true);
						}
					}
				}
				g2.setColor(Color.BLACK);
			}
		}
	}

	/*
	 * Se clicco il pulsante accorpa, se l'operazione è possibile, devono
	 * evidenziarsi gli esagoni contenenti un'unità del player di turno adiacente a quella
	 * selezionata e si deve entrare in accorpaMode
	 */
	private void accorpaOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		if (gameMode.isAttackMode() || gameMode.isMovingMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAttackMode(false);
			gameMode.setMovingMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
			c.repaint();
			c.validate();
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = m.getComponent()[m.getSelezionato()];
		int turno = gameMode.getTurno();
		Unità unitSelected = selected.getUnit();
		Esagono adiacenza = null;
		Unità u = null;
		if (unitSelected != null) {
			if (unitSelected.getPlayer() == turno) {
				MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
				int xC = mappaGrafica.getXCentro();
				int yC = mappaGrafica.getYCentro();
				double raggio = MappaGrafica.STDRAGGIO;
				int id = 0;
				EsagonoGrafico eG = new EsagonoGrafico(id, xC, yC, raggio);
				Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
				List<Esagono> esagoniRaggiungibili = unitSelected
						.getEsagoniRaggiungibili();
				for (int i = 0; i < 6; i++) {
					adiacenza = selected.getAdiacenze()[i];
					if (esagoniRaggiungibili.contains(adiacenza)) {
						u = adiacenza.getUnit();
						if (u != null && unitSelected.isSameUnitOf(u)
								&& u.getPlayer() == turno) {
							eG.newSet(adiacenza.getId(), xC, yC, raggio);
							g2.setColor(Color.MAGENTA);
							g2.setStroke(new BasicStroke(3));
							g2.draw(eG);
							gameMode.setAccorpaMode(true);
						}
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unità!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
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

		// rimuovo gli eventuali altri pannelli presenti sulla finestra e
		// aggiungo quelli nuovi
		c.removeAll();
		gameMode.getLandPanel().enableAll();
		gameMode.resetAll();
		c.add(gameMode.getStartPanel(), BorderLayout.CENTER);
		gameWin.repaint();
		gameWin.validate();
	}

	private void muoviOpt() {

		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		if (gameMode.isAttackMode() || gameMode.isAccorpaMode()
				|| gameMode.isSelecionUnitMode() || gameMode.isScorporaMode()) {

			gameMode.setAttackMode(false);
			gameMode.setAccorpaMode(false);
			gameMode.setScorporaMode(false);
			gameMode.setSelectionUnitMode(false);
			c.repaint();
			System.out.println("dopo repaint prima di validate");
			c.validate();
			System.out.println("ho validato");
		}

		Mappa m = gameMode.getMappa();
		Esagono selected = m.getComponent()[m.getSelezionato()];
		int turno = gameMode.getTurno();
		Unità u = selected.getUnit();

		if (u != null) {
			if (u.getPlayer() == turno) {
				MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
				int xC = mappaGrafica.getXCentro();
				int yC = mappaGrafica.getYCentro();
				double raggio = MappaGrafica.STDRAGGIO;
				int id = 0;
				EsagonoGrafico eG = new EsagonoGrafico(id, xC, yC, raggio);
				Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
				List<Esagono> esagoniRaggiungibili = u
						.getEsagoniRaggiungibili();
				Iterator<Esagono> it = esagoniRaggiungibili.iterator();

				while (it.hasNext()) {
					Esagono e = it.next();
					id = e.getId();
					eG.newSet(id, xC, yC, raggio);
					g2.setColor(Color.YELLOW);
					g2.setStroke(new BasicStroke(3));
					// System.out.println("sto per colorare gli esagoni");
					g2.draw(eG);
					// System.out.println("ho colorato gli esagoni");
				}
				gameMode.setMovingMode(true);
				g2.setColor(Color.BLACK);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai selezionato alcuna unità!", "ERRORE!",
					JOptionPane.ERROR_MESSAGE);
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
		mG.validate();
	}
}
