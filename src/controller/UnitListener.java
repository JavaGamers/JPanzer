package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import model.Aereo;
import model.Artiglieria;
import model.Esagono;
import model.EsagonoGrafico;
import model.FanteriaLeggera;
import model.FanteriaPesante;
import model.Lago;
import model.Mappa;
import model.Panzer;
import model.Player;
import model.Unit�;
import view.CommandPanel;
import view.GameWin;
import view.MappaGrafica;
import view.UnitPanel;

public class UnitListener extends MouseAdapter implements ActionListener {

	public final static String FANTLEGGOPT = "fanteriaLegg";
	public final static String FANTPESOPT = "fanteriaPes";
	public final static String PANZEROPT = "panzer";
	public final static String AEREOOPT = "aereo";
	public final static String ARTIGLIERIAOPT = "artiglieria";
	public final static String GIOCAOPT = "gioca";
	public final static String ZOOMOPT = "zoom";
	public static GameMode gameMode = GameMode.getGameMode();

	/*
	 * Metodo che al passaggio del mouse sopra i
	 * pulsanti fa comparire il popup contente
	 * il costo dell'unit� corrispondente
	 */
	public void mouseEntered(MouseEvent mE) {

		if (mE.getSource() instanceof JButton) {
			JButton source = (JButton) mE.getSource();
			String action = source.getActionCommand();
			Popup popup = UnitPanel.getPopup();
			JLabel label = UnitPanel.getPopupLabel();
			int x = source.getLocationOnScreen().x;
			int y = source.getLocationOnScreen().y + source.getHeight();
			if (action.equals(AEREOOPT)) {
				label.setText("Costo: " + Aereo.COSTO);
				PopupFactory factory = PopupFactory.getSharedInstance();
				popup = factory.getPopup(null, label, x, y);
				UnitPanel.setPopup(popup);
				popup.show();
			} else if (action.equals(ARTIGLIERIAOPT)) {
				label.setText("Costo: " + Artiglieria.COSTO);
				PopupFactory factory = PopupFactory.getSharedInstance();
				popup = factory.getPopup(null, label, x, y);
				UnitPanel.setPopup(popup);
				popup.show();
			} else if (action.equals(FANTLEGGOPT)) {
				label.setText("Costo: " + FanteriaLeggera.COSTO);
				PopupFactory factory = PopupFactory.getSharedInstance();
				popup = factory.getPopup(null, label, x, y);
				UnitPanel.setPopup(popup);
				popup.show();
			} else if (action.equals(FANTPESOPT)) {
				label.setText("Costo: " + FanteriaPesante.COSTO);
				PopupFactory factory = PopupFactory.getSharedInstance();
				popup = factory.getPopup(null, label, x, y);
				UnitPanel.setPopup(popup);
				popup.show();
			} else if (action.equals(PANZEROPT)) {
				label.setText("Costo: " + Panzer.COSTO);
				PopupFactory factory = PopupFactory.getSharedInstance();
				popup = factory.getPopup(null, label, x, y);
				UnitPanel.setPopup(popup);
				popup.show();
			}
		}
	}

	/*
	 * Metodo che all'usicta del puntatore del mouse
	 * da un pulsante fa scomparire il popup
	 * corrispondente
	 */
	public void mouseExited(MouseEvent mE) {
		Popup popup = UnitPanel.getPopup();
		if (popup != null) {
			popup.hide();
		}
	}

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();

		if (com.equals(FANTLEGGOPT)) {
			fanteriaLeggOpt();
		} else if (com.equals(FANTPESOPT)) {
			fanteriaPesOpt();
		} else if (com.equals(PANZEROPT)) {
			panzerOpt();
		} else if (com.equals(AEREOOPT)) {
			aereoOpt();
		} else if (com.equals(ARTIGLIERIAOPT)) {
			artiglieriaOpt();
		} else if (com.equals(GIOCAOPT)) {
			giocaOpt();
		} else if (com.equals(ZOOMOPT)) {
			zoomOpt();
		}
	}

	//Metodo di zoom
	private void zoomOpt() {
		GameWin gameWin = gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		if (mG.getRaggio() == MappaGrafica.STDRAGGIO) {
			gameMode.setZoomOutMode(true);
			mG.setRaggio(MappaGrafica.ZOOMRAGGIO);
			gameMode.getCommandPanel().silenceAll();
		} else {
			gameMode.setZoomOutMode(false);
			mG.setRaggio(MappaGrafica.STDRAGGIO);
			gameMode.getCommandPanel().enableAll();
		}
		gameWin.repaint();
		mG.validate();
	}

	/*
	 * Metodo che compie azioni differenti a seconda
	 * dei tre stati del pulsante ad esso associato:
	 * -selectionUnitMode + click player 1 = switch to player 2 in selectionUnitMode
	 * -selectionUnitMode + click player 2 = switch to player 1 in playingMode
	 * -playingMode + click = torna CommandPanel in playingMode*/
	private void giocaOpt() {
		if (!gameMode.isPlayingMode()) {
			int turno = gameMode.getTurno();
			if (turno == 1 && gameMode.getPlayer(turno).hasUnits()) {
				gameMode.switchTurno();
				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();
				c.removeAll();
				gameMode.getUnitPanel().updateLabel();
				c.add(gameMode.getUnitPanel(), BorderLayout.EAST);
				JScrollPane jsp = new JScrollPane();
				jsp.setViewportView(gameMode.getMappaGrafica());
				jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
				c.add(jsp, BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();
			} else if (turno == 2 && gameMode.getPlayer(turno).hasUnits()) {
				gameMode.switchTurno();
				gameMode.setSelectionUnitMode(false);
				GameWin gameWin = gameMode.getGameWin();
				Container c = gameWin.getContentPane();
				c.removeAll();
				// ora inizia il gioco
				gameMode.setPlayingMode(true);
				CommandPanel commandPanel = gameMode.getCommandPanel();
				commandPanel.setPlayerLabel(gameMode.getPlayer(1));
				c.add(commandPanel, BorderLayout.EAST);
				JScrollPane jsp = new JScrollPane();
				jsp.setViewportView(gameMode.getMappaGrafica());
				jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
				c.add(jsp, BorderLayout.CENTER);
				gameWin.repaint();
				gameWin.validate();

			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Devi posizionare almeno un'unit� per giocare!",
						"ERRORE!", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			gameMode.setSelectionUnitMode(false);
			GameWin gameWin = gameMode.getGameWin();
			Container c = gameWin.getContentPane();
			c.removeAll();
			// ricomincia il gioco
			gameMode.setPlayingMode(true);
			c.add(gameMode.getCommandPanel(), BorderLayout.EAST);
			JScrollPane jsp = new JScrollPane();
			jsp.setViewportView(gameMode.getMappaGrafica());
			jsp.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
			c.add(jsp, BorderLayout.CENTER);
			gameWin.repaint();
			gameWin.validate();
		}
	}

	//METODI PER SETTARE LE UNITA' SULLA MAPPA
	private void artiglieriaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		/*
		 * Il giocatore pu� acquistare l'unit� solo se:
		 * -ha abbastanza soldi per farlo
		 * -ha selezionato un esagono
		 * -l'esagono si trova nella sua parte di mappa
		 * -non � un lago
		 * -non contiene gi� un'unit� di tipo diverso
		 */
		if (player.getSoldi() >= Artiglieria.COSTO) {
			if (m.getSelezionato() != -1) {
				e = m.getComponent()[m.getSelezionato()];

				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					if (e.getUnit() == null
							&& !(e.getTerritorio() instanceof Lago)) {
						e.setUnit(new Artiglieria(Unit�.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						//setto l'immagine sulla mappa
						mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Artiglieria.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					}
					/*
					 * Se � presente un'unit� dello stesso tipo sull'esagono
					 * scelto
					 */
					else if (e.getUnit() instanceof Artiglieria
							&& !(e.getTerritorio() instanceof Lago)) {
						int unit�Prec = e.getUnit().getNumUnits();
						int unit�Nuove = unit�Prec + Unit�.UNITACOMPRABILI;
						//Setto il nuovo numero di unit�
						e.getUnit().setNumUnits(unit�Nuove);
						//Setto l'esperienza della nuova unit� come media pesata tra le due unit�
						double esp = e.getUnit().getEsp()*e.getUnit().getNumUnits()/unit�Nuove;
						e.getUnit().setEsp(esp);

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Artiglieria.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (!(e.getTerritorio() instanceof Lago)) {
						JOptionPane
								.showMessageDialog(
										gameMode.getGameWin(),
										"C'� gi� un'unit� di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unit� di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unit� negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unit�!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}

	/*
	 *Si tralascia di commentare i metodi relativi 
	 *alle altre unit� in quanto sono assai simili
	 *a quello precedentemente commentato
	 */
	private void aereoOpt() {
		/*
		 *per questa unit� non si fa il controllo
		 *sul tipo di territorio in quanto questa
		 *pu� muoversi ovunque
		 */
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);

		if (player.getSoldi() >= Aereo.COSTO) {
			if (m.getSelezionato() != -1) {
				e = m.getComponent()[m.getSelezionato()];

				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					if (e.getUnit() == null) {
						e.setUnit(new Aereo(Unit�.UNITACOMPRABILI, gameMode
								.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());

						mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Aereo.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof Aereo) {
						int unit�Prec = e.getUnit().getNumUnits();
						int unit�Nuove = unit�Prec + Unit�.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unit�Nuove);
						double esp = e.getUnit().getEsp()*e.getUnit().getNumUnits()/unit�Nuove;
						e.getUnit().setEsp(esp);

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Aereo.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else {
						JOptionPane
								.showMessageDialog(
										gameMode.getGameWin(),
										"C'� gi� un'unit� di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unit� negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unit�!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void panzerOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);

		if (player.getSoldi() >= Panzer.COSTO) {

			if (m.getSelezionato() != -1) {

				e = m.getComponent()[m.getSelezionato()];

				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					if (e.getUnit() == null
							&& !(e.getTerritorio() instanceof Lago)) {
						e.setUnit(new Panzer(Unit�.UNITACOMPRABILI, gameMode
								.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						
						mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Panzer.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof Panzer
							&& !(e.getTerritorio() instanceof Lago)) {
						int unit�Prec = e.getUnit().getNumUnits();
						int unit�Nuove = unit�Prec + Unit�.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unit�Nuove);
						double esp = e.getUnit().getEsp()*e.getUnit().getNumUnits()/unit�Nuove;
						e.getUnit().setEsp(esp);

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Panzer.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (!(e.getTerritorio() instanceof Lago)) {
						JOptionPane
								.showMessageDialog(
										gameMode.getGameWin(),
										"C'� gi� un'unit� di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unit� di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unit� negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unit�!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fanteriaLeggOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		if (player.getSoldi() >= FanteriaLeggera.COSTO) {
			if (m.getSelezionato() != -1) {
				e = m.getComponent()[m.getSelezionato()];

				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					if (e.getUnit() == null
							&& !(e.getTerritorio() instanceof Lago)) {
						e.setUnit(new FanteriaLeggera(Unit�.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());

						mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti
								- FanteriaLeggera.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof FanteriaLeggera
							&& !(e.getTerritorio() instanceof Lago)) {
						int unit�Prec = e.getUnit().getNumUnits();
						int unit�Nuove = unit�Prec + Unit�.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unit�Nuove);
						double esp = e.getUnit().getEsp()*e.getUnit().getNumUnits()/unit�Nuove;
						e.getUnit().setEsp(esp);

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti
								- FanteriaLeggera.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (!(e.getTerritorio() instanceof Lago)) {
						JOptionPane
								.showMessageDialog(
										gameMode.getGameWin(),
										"C'� gi� un'unit� di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unit� di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unit� negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unit�!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void fanteriaPesOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
		if (player.getSoldi() >= FanteriaPesante.COSTO) {
			if (m.getSelezionato() != -1) {
				e = m.getComponent()[m.getSelezionato()];

				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					if (e.getUnit() == null
							&& !(e.getTerritorio() instanceof Lago)) {
						e.setUnit(new FanteriaPesante(Unit�.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						
						mG.paintImage(mG.getGraphics(), eG, e.getUnit().getImage());

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti
								- FanteriaPesante.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof FanteriaPesante
							&& !(e.getTerritorio() instanceof Lago)) {
						int unit�Prec = e.getUnit().getNumUnits();
						int unit�Nuove = unit�Prec + Unit�.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unit�Nuove);
						double esp = e.getUnit().getEsp()*e.getUnit().getNumUnits()/unit�Nuove;
						e.getUnit().setEsp(esp);

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti
								- FanteriaPesante.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (!(e.getTerritorio() instanceof Lago)) {
						JOptionPane
								.showMessageDialog(
										gameMode.getGameWin(),
										"C'� gi� un'unit� di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unit� di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unit� negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unit�!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}
}