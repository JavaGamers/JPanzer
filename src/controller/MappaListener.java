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
import java.util.LinkedList;

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
import model.Unit�;
import model.Utilities;
import view.CommandPanel;
import view.FinalPanel;
import view.GameWin;
import view.MappaGrafica;

public class MappaListener extends MouseAdapter {

	public static GameMode gameMode = GameMode.getGameMode();

	private static Esagono prec = null;/*
										 * Esagono su cui precedentemente era
										 * apparso il popup. Utile per
										 * l'avvicendamento dei popup sullo
										 * schermo
										 */
	private static EsagonoGrafico esagonoGrafico = new EsagonoGrafico(0, 0, 0,
			0);

	/*
	 * Questo metodo � quello che si occupa di mostrare le label di popup
	 * contenenti il numero di unit� presenti sull'esagono su cui si �
	 * posizionato il mouse senza cliccare
	 */
	public void mouseMoved(MouseEvent mE) {
		double x = mE.getX();
		double y = mE.getY();

		// Il popup va mostrato solo se si sta giocando altrimenti il metodo non
		// deve fare nulla
		if (gameMode.isPlayingMode()) {
			// mappaGrafica e suoi attributi
			MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
			int xC = mappaGrafica.getXCentro();
			int yC = mappaGrafica.getYCentro();
			double raggio = mappaGrafica.getRaggio();
			/*
			 * Il popup � un elemento grafico della MappaGrafica, pertanto � in
			 * essa contenuto
			 */
			Popup popup = MappaGrafica.getPopup();

			// (xLabel, yLabel) indicheranno la posizione del popup
			int xLabel = 0;
			int yLabel = 0;

			// esagono su cui � presente il mouse(in questo momento)
			Esagono e = mappaGrafica.contains(x, y);

			if (e != null) {
				/*
				 * se il popup era gi� apparso in un esagono e questo � diverso
				 * da quello su cui ora si � posizionato il mouse
				 */
				if (prec != null) {
					if (!e.equals(prec)) {
						// Si nasconde il popup mostrato in precedenza
						if (popup != null) {
							popup.hide();
						}
						esagonoGrafico.newSet(e.getId(), xC, yC, raggio);
						xLabel = esagonoGrafico.xpoints[2];
						yLabel = esagonoGrafico.ypoints[2];

						Point point = new Point(xLabel, yLabel);
						/*
						 * il popup dovra mostrarsi in un punto dello schermo le
						 * cui coordinate si possono ricavare da quelle del
						 * pannello di partenza attraverso il seguente metodo
						 */
						SwingUtilities
								.convertPointToScreen(point, mappaGrafica);
						Unit� u = e.getUnit();
						if (u != null) {
							JLabel label = new JLabel("" + u.getNumUnits());
							PopupFactory factory = PopupFactory
									.getSharedInstance();
							popup = factory.getPopup(null, label,
									(int) point.getX(), (int) point.getY());
							MappaGrafica.setPopup(popup);
							popup.show();
						}
					}
					prec = e;
				}
				/*
				 * Se non era gi� apparso non ho bisogno di nasconderlo dalla
				 * posizione precedente
				 */
				else {
					esagonoGrafico.newSet(e.getId(), xC, yC, raggio);
					xLabel = esagonoGrafico.xpoints[2];
					yLabel = esagonoGrafico.ypoints[2];
					Point point = new Point(xLabel, yLabel);
					SwingUtilities.convertPointToScreen(point, mappaGrafica);
					Unit� u = e.getUnit();
					if (u != null) {
						JLabel label = new JLabel("" + u.getNumUnits());
						PopupFactory factory = PopupFactory.getSharedInstance();
						popup = factory.getPopup(null, label,
								(int) point.getX(), (int) point.getY());
						MappaGrafica.setPopup(popup);
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

		/*
		 * variabili puntatore:
		 * 		-selectedUnit = eventuale unit� appartenente all'esagono selezionato
		 * 		-otherUnit = eventuale unit� con cui quella selezionata interagisce
		 * 		-nuovo = ultimo esagono su cui si � cliaccato
		 * 		-vecchio = eventuale esagono precedentemente cliccato
		 * 		-oldSelected = indice del vecchio esagono selezionato
		 * 		-newSelected = indice del nuovo esagono selezionato
		 */
		Unit� selectedUnit = null;
		Unit� otherUnit = null;
		Image img = null;
		Esagono nuovo = null;
		Esagono vecchio = null;
		int oldSelected = m.getSelezionato();
		int newSelected = Integer.MAX_VALUE;

		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		g2.setStroke(new BasicStroke(3));

		if (g2 != null) {
			g2.setColor(Color.BLACK);
		}
		EsagonoGrafico eG = new EsagonoGrafico(0, xC, yC, raggio);
		/*
		 * se qualcuno era selezionato (prima del click che si sta gestendo)
		 * si ricolora il suo contorno di nero o di blue a seconda che ci si trovi
		 * o meno in SelectionUnitMode
		 */
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

		// esagono su cui si � cliccato (in questo momento)
		nuovo = mappaGrafica.contains(x, y);

		if (nuovo != null) {
			/*
			 * Setto il nuovo valore della variabile selezionato di mappa
			 * che user� alla fine del metodo per colorare il bordo
			 * dell'esagono selezionato di rosso
			 */
			newSelected = nuovo.getId();
			m.setSelezionato(newSelected);
			eG.newSet(newSelected, xC, yC, raggio);
			EsagonoGrafico eG2 = new EsagonoGrafico(0, xC, yC, raggio);

			selectedUnit = nuovo.getUnit();
			gameMode.getCommandPanel().setUnitLabel(selectedUnit);

			/*
			 * A seconda di quale bit di modalit� � posto a true si invoca il metodo
			 * corrispondente alla situazione in cui ci si trova
			 */
			if (gameMode.isMovingMode()) {
				movingMode(nuovo, vecchio, eG2, selectedUnit, otherUnit, img,
						newSelected, oldSelected);
			}

			if (selectedUnit != null) {
				if (gameMode.isAttackMode() && !selectedUnit.hasAlreadyAttack()) {
					attackMode(nuovo, vecchio, eG2, selectedUnit, otherUnit, img,
							newSelected, oldSelected);
				}
			}

			if (gameMode.isAccorpaMode()) {
				accorpaMode(nuovo, vecchio, eG2, selectedUnit, otherUnit, img,
						newSelected, oldSelected);
			}

			if (gameMode.isScorporaMode()) {
				scorporaMode(nuovo, vecchio, eG2, selectedUnit, otherUnit, img,
						newSelected, oldSelected);
			}

			if (g2 != null) {
				g2.setColor(Color.RED);
				g2.draw(eG);
			}
		}
	}
	
	
	private static void movingMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG, Unit� selectedUnit, Unit� otherUnit, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();
		/*
		 * Posso muovere l'unit� nel nuovo esagono solo se � contenuto nei suoi
		 * esagoni raggiungibili e se non ha gi� un'unit� su di esso
		 */
		if (esagoniRaggiungibili.contains(nuovo) && nuovo.getUnit() == null) {

			gameMode.getSound().startMoveMusic();

			// cancello l'unit� dalla vecchia posizione
			vecchio.setUnit(null);
			img = vecchio.getTerritorio().getImage();
			eG.newSet(oldSelected, xC, yC, raggio);
			mappaGrafica.paintImage(g2, eG, img);

			// disegno l'unit� sulla destinazione
			eG.newSet(newSelected, xC, yC, raggio);
			nuovo.setUnit(selectedUnit);
			img = selectedUnit.getImage();
			mappaGrafica.paintImage(g2, eG, img);
			int nuoviP = selectedUnit.getPassi() - (int) nuovo.getMinDistance();

			selectedUnit.setPassi(nuoviP);
		}
		//Esco dal movingMode
		gameMode.setMovingMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}

	private static void attackMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unit� selectedUnit, Unit� otherUnit, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		//L'unit� selezionata pu� attaccare solo se non ha gi� attaccato
		if (!selectedUnit.hasAlreadyAttack()) {
			otherUnit = nuovo.getUnit();

			// se esiste un'unit� attaccata e se appartiene all'avversario
			if (otherUnit != null && otherUnit.getPlayer() != gameMode.getTurno()
					&& vecchio.isAdiacente(nuovo)) {

				GameWin gameWin = gameMode.getGameWin();
				gameMode.getSound().startAttackMusic();

				//informazioni relative all'unit� attaccante
				int numSelectedRemaining = selectedUnit.getNumUnits(); //unit� rimanenti
				int defSelected = selectedUnit.getDef(); //difesa
				int attSelected = selectedUnit.getAtt(); //attacco

				//informazioni relative all'unit� attaccata
				int numOtherUnitRemaining = otherUnit.getNumUnits(); //unit� rimanenti
				int defOtherUnit = otherUnit.getDef(); //difesa
				int attOtherUnit = otherUnit.getAtt(); //attacco

				CommandPanel commandPanel = gameMode.getCommandPanel();
				commandPanel.setInfoBattleLabel(selectedUnit, otherUnit);

				/*
				 * Il numero di unit� rimaste all'unit� attaccata �
				 * calcolato attraverso il seguente algoritmo
				 */
				numOtherUnitRemaining = (int) ((numOtherUnitRemaining * defOtherUnit - numSelectedRemaining
						* attSelected) / (defOtherUnit));
				int moneyEarned = Utilities.calulateMoneyEarned(
						otherUnit.getNumUnits(), numOtherUnitRemaining, otherUnit);

				//Se al battaglione attaccato � rimasta almeno un'unit� esso contrattaccher�
				if (numOtherUnitRemaining > 0) {
					otherUnit.setNumUnits(numOtherUnitRemaining);
					numSelectedRemaining = (int) ((numSelectedRemaining
							* defSelected - numOtherUnitRemaining * attOtherUnit) / (defSelected));
					otherUnit.updateEsp();
				}
				//Altrimenti si rimuove l'unit� sia logicamente che graficamente
				else {
					Player p = gameMode.getPlayer(otherUnit.getPlayer());
					p.rimuoviUnit�(otherUnit);
					nuovo.setUnit(null);
					eG2.newSet(newSelected, xC, yC, raggio);
					img = nuovo.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);

					/*
					 * setto il numero di unit� rimanenti a 0 cos� da aggiornare
					 * correttamente la label del CommandPanel
					 */
					otherUnit.setNumUnits(0);

				}

				/*
				 * Se dopo il contrattacco il battaglione che ha attaccato
				 * per primo ha ancora almeno un'unit� setto i suoi attributi
				 * in modo che siano coerenti con la nuova situazione creata
				 */
				if (numSelectedRemaining > 0) {
					selectedUnit.setNumUnits(numSelectedRemaining);
					selectedUnit.updateEsp();
					selectedUnit.setNumUnits(numSelectedRemaining);
					selectedUnit.setAlreadyAttack(true);
					Player p = gameMode.getPlayer(gameMode.getTurno());
					p.setMoney(p.getSoldi() + moneyEarned);
				} 
				//Altrimenti si rimuove l'unit� sia logicamente che graficamente
				else {
					Player p = gameMode.getPlayer(selectedUnit.getPlayer());
					p.rimuoviUnit�(selectedUnit);
					vecchio.setUnit(null);
					eG2.newSet(oldSelected, xC, yC, raggio);
					img = vecchio.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);
					/*
					 * setto il numero di unit� rimanenti a 0 cos� da aggiornare
					 * correttamente la label del Commandpanel
					 */
					selectedUnit.setNumUnits(0);
				}

				commandPanel.setBattleStatsLabel(selectedUnit, otherUnit);

				//A questo punto controllo se uno dei due vincitori ha vinto
				int winner = gameMode.checkVictory();
				if (winner != 0) {
					Popup popup = MappaGrafica.getPopup();
					if (popup != null) {
						popup.hide();
					}

					gameWin.setResizable(false);
					FinalPanel finalPanel = gameMode.getFinalPanel();
					int height = finalPanel.getVictoryImage().getHeight(null);
					int width = finalPanel.getVictoryImage().getWidth(null);
					gameWin.setSize(width, height);
					Container c = gameWin.getContentPane();

				   /*
					* rimuovo gli eventuali altri pannelli presenti sulla finestra
					* e aggiungo il FinalPanel
					*/
					c.removeAll();
					c.add(finalPanel, BorderLayout.CENTER);
					if (winner == 3) {
						finalPanel.setText("nessuno ha vinto!");
					} else {
						finalPanel.setWinner(gameMode.getPlayer(winner));
					}
				}
				gameWin.repaint();
				gameWin.validate();
			}
		}
		//Esco dall'attackMode
		gameMode.setAttackMode(false);
	}

	private static void accorpaMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unit� selectedUnit, Unit� otherUnit, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		/*Posso effettivamente accorpare un'unit� coon un'altra se:
		 * -la seconda pu� essere raggiunta dalla prima
		 * -la seconda si trova in una delle adiacenze dell'esagono della prima
		 * -se le due unit� sono dello stesso tipo*/
		if (esagoniRaggiungibili.contains(nuovo) && vecchio.isAdiacente(nuovo)
				&& !vecchio.equals(nuovo)) {
			otherUnit = nuovo.getUnit();
			if (otherUnit != null && selectedUnit.isSameUnitOf(otherUnit)) {
				gameMode.getSound().startMoveMusic();
				
				//Setto i valori della nuova unit� che si � creata dall'accorpamento
				int numUnits = selectedUnit.getNumUnits() + otherUnit.getNumUnits();
				double esperienza = (selectedUnit.getEsp()
						* selectedUnit.getNumUnits() + otherUnit.getEsp()
						* otherUnit.getNumUnits())
						/ numUnits;
				/*
				 * si settare come valore dei passi rimanenti della nuova unit�
				 * il risultato della sottrazione tra il numero di passi dell'unit�
				 * che ne aveva rimasti di meno e il costo di attraversamento del
				 * territorio di partenza. Va distinto per� il caso dell'aereo per
				 * il quale il costo di attraversamento � sempre unitario
				 */
				int passi = 0;
				if (selectedUnit instanceof Aereo) {
					if ((selectedUnit.getPassi() - 1) < otherUnit.getPassi()) {
						passi = selectedUnit.getPassi() - 1;
					} else {
						passi = otherUnit.getPassi();
					}
				} else {
					if ((selectedUnit.getPassi() - nuovo.getCosto()) < otherUnit
							.getPassi()) {
						passi = selectedUnit.getPassi() - nuovo.getCosto();
					} else {
						passi = otherUnit.getPassi();
					}
				}
				otherUnit.setNumUnits(numUnits);
				otherUnit.setAlreadyAttack(selectedUnit.hasAlreadyAttack() || otherUnit.hasAlreadyAttack());
				otherUnit.setEsp(esperienza);
				otherUnit.setPassi(passi);

				// rimuovo selectedUnit dalla UnitList del suo player
				Player player = gameMode.getPlayer(gameMode.getTurno());
				player.rimuoviUnit�(selectedUnit);

				// cancello l'unit� accorpata
				vecchio.setUnit(null);
			}
		}
		//esco dall'accorpaMode
		gameMode.setAccorpaMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}

	private static void scorporaMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unit� selectedUnit, Unit� otherUnit, Image img,
			int newSelected, int oldSelected) {

		// selectedUnit � l'unit� da scorporare
		selectedUnit = vecchio.getUnit();
		int turno = gameMode.getTurno();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		/*
		 * posso scorporare l'unit� solo se:
		 * -l'esagono di destinazione � raggiungibile dall'unit�
		 * -non � presente alcuna unit� su di esso
		 * -l'esagono destinazione � adiacente a quello dell'unit�
		 */
		if (esagoniRaggiungibili.contains(nuovo)) {
			otherUnit = nuovo.getUnit();
			if (otherUnit == null && vecchio.isAdiacente(nuovo)) {
				gameMode.getSound().startMoveMusic();
				double esp = selectedUnit.getEsp();

				/*
				 * scorporando un battaglione di unit� se ne ottengono 2 aventi
				 * la met� delle unit� di partenza
				 */
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
					otherUnit = new Artiglieria(num2, turno);
				} else if (selectedUnit instanceof Aereo) {
					otherUnit = new Aereo(num2, turno);
				} else if (selectedUnit instanceof FanteriaPesante) {
					otherUnit = new FanteriaPesante(num2, turno);
				} else if (selectedUnit instanceof FanteriaLeggera) {
					otherUnit = new FanteriaLeggera(num2, turno);
				} else if (selectedUnit instanceof Panzer) {
					otherUnit = new Panzer(num2, turno);
				}

				/*
				 * setto i valori della nuova unit� in base ai valori dell'unit�
				 * di partenza
				 */
				int nuoviP;
				otherUnit.setEsp(esp);
				if (!(selectedUnit instanceof Aereo)) {
					nuoviP = selectedUnit.getPassi() - nuovo.getCosto();
				} else {
					nuoviP = selectedUnit.getPassi() - 1;
				}
				otherUnit.setPassi(nuoviP);
				otherUnit.setAlreadyAttack(selectedUnit.hasAlreadyAttack());
				nuovo.setUnit(otherUnit);

				// aggiungo otherUnit alla UnitList del suo player
				Player player = gameMode.getPlayer(gameMode.getTurno());
				player.aggiungiUnit�(otherUnit);

			}
		}

		//esco dallo scorporaMode
		gameMode.setScorporaMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}
}
