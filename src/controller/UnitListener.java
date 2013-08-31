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
import model.Unità;
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
						"Devi posizionare almeno un'unità per giocare!",
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

	private void artiglieriaOpt() {
		MappaGrafica mG = gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e = null;
		EsagonoGrafico eG = null;
		int turno = gameMode.getTurno();
		Player player = gameMode.getPlayer(turno);
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
						e.setUnit(new Artiglieria(Unità.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						if (!gameMode.isZoomOutMode()) {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getImage());
						} else {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getXImage());
						}

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Artiglieria.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof Artiglieria
							&& !(e.getTerritorio() instanceof Lago)) {
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove = unitàPrec + Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);

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
										"C'è già un'unità di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unità di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unità negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unità!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void aereoOpt() {
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
						e.setUnit(new Aereo(Unità.UNITACOMPRABILI, gameMode
								.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						if (!gameMode.isZoomOutMode()) {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getImage());
						} else {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getXImage());
						}

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Aereo.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof Aereo) {
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove = unitàPrec + Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);

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
										"C'è già un'unità di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unità negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unità!",
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
						e.setUnit(new Panzer(Unità.UNITACOMPRABILI, gameMode
								.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						if (!gameMode.isZoomOutMode()) {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getImage());
						} else {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getXImage());
						}

						// aggiorno i soldi del player
						player = gameMode.getPlayer(gameMode.getTurno());
						int soldiPrecedenti = player.getSoldi();
						int soldiNuovi = soldiPrecedenti - Panzer.COSTO;
						player.setMoney(soldiNuovi);
						UnitPanel unitPanel = gameMode.getUnitPanel();
						unitPanel.setSoldiLabel(soldiNuovi);

					} else if (e.getUnit() instanceof Panzer
							&& !(e.getTerritorio() instanceof Lago)) {
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove = unitàPrec + Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);

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
										"C'è già un'unità di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unità di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unità negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unità!",
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
						e.setUnit(new FanteriaLeggera(Unità.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						if (!gameMode.isZoomOutMode()) {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getImage());
						} else {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getXImage());
						}

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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove = unitàPrec + Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);

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
										"C'è già un'unità di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unità di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unità negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unità!",
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
						e.setUnit(new FanteriaPesante(Unità.UNITACOMPRABILI,
								gameMode.getTurno()));

						eG = new EsagonoGrafico(m.getSelezionato(),
								mG.getXCentro(), mG.getYCentro(),
								mG.getRaggio());
						if (!gameMode.isZoomOutMode()) {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getImage());
						} else {
							mG.paintImage(mG.getGraphics(), eG, e.getUnit()
									.getXImage());
						}

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
						int unitàPrec = e.getUnit().getNumUnits();
						int unitàNuove = unitàPrec + Unità.UNITACOMPRABILI;
						e.getUnit().setNumUnits(unitàNuove);

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
										"C'è già un'unità di tipo diverso su questo territorio!",
										"ERRORE!", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(gameMode.getGameWin(),
								"Non puoi mettere un'unità di terra nel lago!",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane
							.showMessageDialog(
									gameMode.getGameWin(),
									"Devi posizionare l'unità negli esagoni evidenziati!",
									"ERRORE!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(gameMode.getGameWin(),
						"Non hai selezionato alcun territorio!", "ERRORE!",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(gameMode.getGameWin(),
					"Non hai abbastanza soldi per comprare questa unità!",
					"ERRORE!", JOptionPane.ERROR_MESSAGE);
		}
	}
}