package controller;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

import model.Aereo;
import model.Artiglieria;
import model.Esagono;
import model.EsagonoGrafico;
import model.FanteriaLeggera;
import model.FanteriaPesante;
import model.Mappa;
import model.Panzer;
import model.Player;
import model.Unità;
import view.CommandPanel;
import view.FinalPanel;
import view.GameWin;
import view.MappaGrafica;

public class MappaListener extends MouseAdapter {

	public static GameMode gameMode = GameMode.getGameMode();
	private static Esagono prec = null;
	private static EsagonoGrafico esagonoGrafico = new EsagonoGrafico(0, 0, 0,
			0);
	private static Popup popup = null;

	public void mouseMoved(MouseEvent mE) {
		double x = mE.getX();
		double y = mE.getY();

		if (gameMode.isPlayingMode()) {
			// mappaGrafica e suoi attributi
			MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
			int xC = mappaGrafica.getXCentro();
			int yC = mappaGrafica.getYCentro();
			double raggio = mappaGrafica.getRaggio();

			// posizione del popup
			int xLabel = 0;
			int yLabel = 0;

			// esagono su cui è presente il mouse(in questo momento)
			Esagono e = mappaGrafica.contains(x, y);

			if (e != null) {
				if (prec != null) {
					if (!e.equals(prec)) {
						if (popup != null) {
							popup.hide();
						}
						esagonoGrafico.newSet(e.getId(), xC, yC, raggio);
						xLabel = esagonoGrafico.xpoints[2];
						yLabel = esagonoGrafico.ypoints[2];
						Point point = new Point(xLabel, yLabel);
						SwingUtilities
								.convertPointToScreen(point, mappaGrafica);
						Unità u = e.getUnit();
						if (u != null) {
							JLabel label = new JLabel("" + u.getNumUnits());
							PopupFactory factory = PopupFactory
									.getSharedInstance();
							popup = factory.getPopup(mappaGrafica, label,
									(int) point.getX(), (int) point.getY());
							popup.show();
						}
					}
					prec = e;
				} else {
					esagonoGrafico.newSet(e.getId(), xC, yC, raggio);
					xLabel = esagonoGrafico.xpoints[2];
					yLabel = esagonoGrafico.ypoints[2];
					Point point = new Point(xLabel, yLabel);
					SwingUtilities.convertPointToScreen(point, mappaGrafica);
					Unità u = e.getUnit();
					if (u != null) {
						JLabel label = new JLabel("" + u.getNumUnits());
						PopupFactory factory = PopupFactory.getSharedInstance();
						popup = factory.getPopup(mappaGrafica, label,
								(int) point.getX(), (int) point.getY());
						popup.show();
					}
					prec = e;
				}
			}
		}
	}

	public void mouseClicked(MouseEvent mE) {
		// coordinate del click
		double x = mE.getX();
		double y = mE.getY();

		// mappaGrafica e suoi attributi
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();

		Mappa m = gameMode.getMappa();
		int turno = gameMode.getTurno();

		// variabili puntatore
		Unità selectedUnit = null;
		Unità other = null;
		Image img = null;
		Esagono nuovo = null;
		Esagono vecchio = null;
		int oldSelected = m.getSelezionato();
		int newSelected = Integer.MAX_VALUE;

		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		g2.setStroke(new BasicStroke(3));

		// così funziona
		if (g2 != null) {
			g2.setColor(Color.BLACK);
		}
		EsagonoGrafico eG = new EsagonoGrafico(0, xC, yC, raggio);

		// se qualcuno era selezionato (prima di questo click)
		if (oldSelected != -1) {
			vecchio = m.getComponent()[oldSelected];
			eG.newSet(oldSelected, xC, yC, raggio);

			if (gameMode.isSelecionUnitMode()) {
				int settore = vecchio.getCoordinate()[0];
				int posizione = vecchio.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					g2.setColor(Color.BLUE);

				}
			}
			g2.draw(eG);
		}

		// esagono su cui si è cliccato (in questo momento)
		nuovo = mappaGrafica.contains(x, y);

		if (nuovo != null) {
			newSelected = nuovo.getId();
			m.setSelezionato(newSelected);
			eG.newSet(newSelected, xC, yC, raggio);
			EsagonoGrafico eG2 = new EsagonoGrafico(0, xC, yC, raggio);

			selectedUnit = nuovo.getUnit();
			gameMode.getCommandPanel().setUnitLabel(selectedUnit);

			if (gameMode.isMovingMode()) {
				movingMode(nuovo, vecchio, eG2, selectedUnit, other, img,
						newSelected, oldSelected);
			}

			if (selectedUnit != null) {
				if (gameMode.isAttackMode() && !selectedUnit.hasAlreadyAttack()) {
					attackMode(nuovo, vecchio, eG2, selectedUnit, other, img,
							newSelected, oldSelected);
				}
			}

			if (gameMode.isAccorpaMode()) {
				accorpaMode(nuovo, vecchio, eG2, selectedUnit, other, img,
						newSelected, oldSelected);
			}

			if (gameMode.isScorporaMode()) {
				scorporaMode(nuovo, vecchio, eG2, selectedUnit, other, img,
						newSelected, oldSelected);
			}

			// così funziona
			if (g2 != null) {
				g2.setColor(Color.RED);
				g2.draw(eG);
			}
		}
	}

	private static void movingMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG, Unità selectedUnit, Unità other, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();
		if (esagoniRaggiungibili.contains(nuovo) && nuovo.getUnit() == null) {

			gameMode.getSound().startMoveMusic();

			// cancello l'unità dalla vecchia posizione
			vecchio.setUnit(null);
			img = vecchio.getTerritorio().getImage();
			eG.newSet(oldSelected, xC, yC, raggio);
			mappaGrafica.paintImage(g2, eG, img);

			// disegno l'unità sulla destinazione
			eG.newSet(newSelected, xC, yC, raggio);
			nuovo.setUnit(selectedUnit);
			img = selectedUnit.getImage();
			mappaGrafica.paintImage(g2, eG, img);
			int nuoviP = selectedUnit.getPassi() - (int) nuovo.getMinDistance();
			/*
			 * if(nuoviP<0){ nuoviP = 0; }
			 */
			selectedUnit.setPassi(nuoviP);
		}
		gameMode.setMovingMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}

	private static void attackMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unità selectedUnit, Unità other, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		if (!selectedUnit.hasAlreadyAttack()) {
			other = nuovo.getUnit();

			// se esiste un'unità attaccata e se appartiene all'avversario
			if (other != null && other.getPlayer() != gameMode.getTurno()
					&& vecchio.isAdiacente(nuovo)) {

				gameMode.getSound().startAttackMusic();

				// unità attaccante
				int numSelectedRemaining = selectedUnit.getNumUnits();
				int defSelected = selectedUnit.getDef();
				int attSelected = selectedUnit.getAtt();

				// unità attaccata
				int numOtherRemaining = other.getNumUnits();
				int defOther = other.getDef();
				int attOther = other.getAtt();

				CommandPanel commandPanel = gameMode.getCommandPanel();
				commandPanel.setInfoBattleLabel(selectedUnit, other);

				numOtherRemaining = (int) ((numOtherRemaining * defOther - numSelectedRemaining
						* attSelected) / (defOther));
				int moneyEarned = calulateMoneyEarned(other.getNumUnits(),
						numOtherRemaining, other);

				if (numOtherRemaining > 0) {
					other.setNumUnits(numOtherRemaining);
					// se l'unità di difesa non è morta contrattacca
					numSelectedRemaining = (int) ((numSelectedRemaining
							* defSelected - numOtherRemaining * attOther) / (defSelected));
					other.updateEsp();
				} else {
					Player p = gameMode.getPlayer(other.getPlayer());
					p.rimuoviUnità(other);
					nuovo.setUnit(null);
					eG2.newSet(newSelected, xC, yC, raggio);
					img = nuovo.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);

					// setto il numero di unità rimanenti a 0 così da aggiornare
					// correttamente la label
					other.setNumUnits(0);

				}

				if (numSelectedRemaining > 0) {
					selectedUnit.setNumUnits(numSelectedRemaining);
					selectedUnit.updateEsp();
					selectedUnit.setNumUnits(numSelectedRemaining);
					selectedUnit.setAlreadyAttack(true);
					Player p = gameMode.getPlayer(gameMode.getTurno());
					p.setMoney(p.getSoldi() + moneyEarned);
				} else {
					Player p = gameMode.getPlayer(selectedUnit.getPlayer());
					p.rimuoviUnità(selectedUnit);
					vecchio.setUnit(null);
					eG2.newSet(oldSelected, xC, yC, raggio);
					img = vecchio.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);

					// setto il numero di unità rimanenti a 0 così da aggiornare
					// correttamente la label
					selectedUnit.setNumUnits(0);
				}

				commandPanel.setBattleStatsLabel(selectedUnit, other);

			}
			gameMode.setAttackMode(false);

			int winner = gameMode.checkVictory();
			if (winner != 0) {

				GameWin gameWin = gameMode.getGameWin();
				gameWin.setResizable(false);
				FinalPanel finalPanel = gameMode.getFinalPanel();
				int height = finalPanel.getVictoryImage().getHeight(null);
				int width = finalPanel.getVictoryImage().getWidth(null);
				gameWin.setSize(width, height);
				Container c = gameWin.getContentPane();

				// rimuovo gli eventuali altri pannelli presenti sulla finestra
				// e aggiungo quelli nuovi
				c.removeAll();
				c.add(finalPanel, BorderLayout.CENTER);
				if (winner == 3) {
					finalPanel.setText("nessuno ha vinto!");
				} else {
					finalPanel.setWinner(gameMode.getPlayer(winner));
				}
				gameWin.repaint();
				gameWin.validate();
			}
		}
	}

	private static int calulateMoneyEarned(int prevNum, int postNum, Unità u) {
		int gain = 0;
		int diff = prevNum - postNum;
		double percent = 2 / 5;

		if (diff > prevNum) {
			diff = prevNum;
		}

		double d = diff * percent / 10;

		if (u instanceof Artiglieria) {
			gain = (int) (d * Artiglieria.COSTO);
		} else if (u instanceof Aereo) {
			gain = (int) (d * Aereo.COSTO);
		} else if (u instanceof FanteriaPesante) {
			gain = (int) (d * FanteriaPesante.COSTO);
		} else if (u instanceof FanteriaLeggera) {
			gain = (int) (d * FanteriaLeggera.COSTO);
		} else if (u instanceof Panzer) {
			gain = (int) (d * Panzer.COSTO);
		}
		return gain;
	}

	private static void accorpaMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unità selectedUnit, Unità other, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		List<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		if (esagoniRaggiungibili.contains(nuovo)) {
			other = nuovo.getUnit();
			if (other != null && selectedUnit.isSameUnitOf(other)) {
				gameMode.getSound().startMoveMusic();
				int numUnits = selectedUnit.getNumUnits() + other.getNumUnits();
				double esperienza = (selectedUnit.getEsp()
						* selectedUnit.getNumUnits() + other.getEsp()
						* other.getNumUnits())
						/ numUnits;
				int passi = 0;
				if (selectedUnit.getPassi() < other.getPassi()) {
					passi = selectedUnit.getPassi();
				} else {
					passi = other.getPassi();
				}
				other.setNumUnits(numUnits);
				other.setEsp(esperienza);
				other.setPassi(passi);

				// rimuovo selectedUnit dalla UnitList del suo player
				Player player = gameMode.getPlayer(gameMode.getTurno());
				player.rimuoviUnità(selectedUnit);

				// cancello il disegno dell'unità accorpata
				vecchio.setUnit(null);
				img = vecchio.getTerritorio().getImage();
				eG2.newSet(oldSelected, xC, yC, raggio);
				mappaGrafica.paintImage(g2, eG2, img);
				gameMode.setAccorpaMode(false);

				// repaint intenzionale XD XD
				gameMode.getGameWin().repaint();
				gameMode.getGameWin().validate();
			}
		}
	}

	private static void scorporaMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unità selectedUnit, Unità other, Image img,
			int newSelected, int oldSelected) {

		gameMode.getSound().startMoveMusic();
		selectedUnit = vecchio.getUnit();
		int turno = gameMode.getTurno();
		List<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		if (esagoniRaggiungibili.contains(nuovo)) {
			other = nuovo.getUnit();
			if (other == null) {
				gameMode.getSound().startMoveMusic();
				double esp = selectedUnit.getEsp();
				int num1 = 0;
				int num2 = 0;
				if (selectedUnit.getNumUnits() % 2 == 0) {
					num1 = selectedUnit.getNumUnits() / 2;
					num2 = selectedUnit.getNumUnits() / 2;
				} else {
					num1 = (selectedUnit.getNumUnits() + 1) / 2;
					num2 = (selectedUnit.getNumUnits() - 1) / 2;
				}

				selectedUnit.setNumUnits(num1);

				if (selectedUnit instanceof Artiglieria) {
					other = new Artiglieria(num2, turno);
				} else if (selectedUnit instanceof Aereo) {
					other = new Aereo(num2, turno);
				} else if (selectedUnit instanceof FanteriaPesante) {
					other = new FanteriaPesante(num2, turno);
				} else if (selectedUnit instanceof FanteriaLeggera) {
					other = new FanteriaLeggera(num2, turno);
				} else if (selectedUnit instanceof Panzer) {
					other = new Panzer(num2, turno);
				}

				other.setEsp(esp);
				int nuoviP = other.getPassi() - nuovo.getCosto();
				other.setPassi(nuoviP);
				nuovo.setUnit(other);

				// aggiungo other alla UnitList del suo player
				Player player = gameMode.getPlayer(gameMode.getTurno());
				player.aggiungiUnità(other);

				gameMode.setScorporaMode(false);

				// repaint intenzionale XD XD
				gameMode.getGameWin().repaint();
				gameMode.getGameWin().validate();
			}
		}
	}
}
