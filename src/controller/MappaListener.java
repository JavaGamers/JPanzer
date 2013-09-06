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
import model.Unità;
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
	 * Questo metodo è quello che si occupa di mostrare le label di popup
	 * contenenti il numero di unità presenti sull'esagono su cui si è
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
			 * Il popup è un elemento grafico della MappaGrafica, pertanto è in
			 * essa contenuto
			 */
			Popup popup = MappaGrafica.getPopup();

			// (xLabel, yLabel) indicheranno la posizione del popup
			int xLabel = 0;
			int yLabel = 0;

			// esagono su cui è presente il mouse(in questo momento)
			Esagono e = mappaGrafica.contains(x, y);

			if (e != null) {
				/*
				 * se il popup era già apparso in un esagono e questo è diverso
				 * da quello su cui ora si è posizionato il mouse
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
						Unità u = e.getUnit();
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
				 * Se non era già apparso non ho bisogno di nasconderlo dalla
				 * posizione precedente
				 */
				else {
					esagonoGrafico.newSet(e.getId(), xC, yC, raggio);
					xLabel = esagonoGrafico.xpoints[2];
					yLabel = esagonoGrafico.ypoints[2];
					Point point = new Point(xLabel, yLabel);
					SwingUtilities.convertPointToScreen(point, mappaGrafica);
					Unità u = e.getUnit();
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
		 * 		-selectedUnit = eventuale unità appartenente all'esagono selezionato
		 * 		-otherUnit = eventuale unità con cui quella selezionata interagisce
		 * 		-nuovo = ultimo esagono su cui si è cliaccato
		 * 		-vecchio = eventuale esagono precedentemente cliccato
		 * 		-oldSelected = indice del vecchio esagono selezionato
		 * 		-newSelected = indice del nuovo esagono selezionato
		 */
		Unità selectedUnit = null;
		Unità otherUnit = null;
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

		// esagono su cui si è cliccato (in questo momento)
		nuovo = mappaGrafica.contains(x, y);

		if (nuovo != null) {
			/*
			 * Setto il nuovo valore della variabile selezionato di mappa
			 * che userò alla fine del metodo per colorare il bordo
			 * dell'esagono selezionato di rosso
			 */
			newSelected = nuovo.getId();
			m.setSelezionato(newSelected);
			eG.newSet(newSelected, xC, yC, raggio);
			EsagonoGrafico eG2 = new EsagonoGrafico(0, xC, yC, raggio);

			selectedUnit = nuovo.getUnit();
			gameMode.getCommandPanel().setUnitLabel(selectedUnit);

			/*
			 * A seconda di quale bit di modalità è posto a true si invoca il metodo
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
			EsagonoGrafico eG, Unità selectedUnit, Unità otherUnit, Image img,
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
		 * Posso muovere l'unità nel nuovo esagono solo se è contenuto nei suoi
		 * esagoni raggiungibili e se non ha già un'unità su di esso
		 */
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

			selectedUnit.setPassi(nuoviP);
		}
		//Esco dal movingMode
		gameMode.setMovingMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}

	private static void attackMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unità selectedUnit, Unità otherUnit, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		//L'unità selezionata può attaccare solo se non ha già attaccato
		if (!selectedUnit.hasAlreadyAttack()) {
			otherUnit = nuovo.getUnit();

			// se esiste un'unità attaccata e se appartiene all'avversario
			if (otherUnit != null && otherUnit.getPlayer() != gameMode.getTurno()
					&& vecchio.isAdiacente(nuovo)) {

				GameWin gameWin = gameMode.getGameWin();
				gameMode.getSound().startAttackMusic();

				//informazioni relative all'unità attaccante
				int numSelectedRemaining = selectedUnit.getNumUnits(); //unità rimanenti
				int defSelected = selectedUnit.getDef(); //difesa
				int attSelected = selectedUnit.getAtt(); //attacco

				//informazioni relative all'unità attaccata
				int numOtherUnitRemaining = otherUnit.getNumUnits(); //unità rimanenti
				int defOtherUnit = otherUnit.getDef(); //difesa
				int attOtherUnit = otherUnit.getAtt(); //attacco

				CommandPanel commandPanel = gameMode.getCommandPanel();
				commandPanel.setInfoBattleLabel(selectedUnit, otherUnit);

				/*
				 * Il numero di unità rimaste all'unità attaccata è
				 * calcolato attraverso il seguente algoritmo
				 */
				numOtherUnitRemaining = (int) ((numOtherUnitRemaining * defOtherUnit - numSelectedRemaining
						* attSelected) / (defOtherUnit));
				int moneyEarned = Utilities.calulateMoneyEarned(
						otherUnit.getNumUnits(), numOtherUnitRemaining, otherUnit);

				//Se al battaglione attaccato è rimasta almeno un'unità esso contrattaccherà
				if (numOtherUnitRemaining > 0) {
					otherUnit.setNumUnits(numOtherUnitRemaining);
					numSelectedRemaining = (int) ((numSelectedRemaining
							* defSelected - numOtherUnitRemaining * attOtherUnit) / (defSelected));
					otherUnit.updateEsp();
				}
				//Altrimenti si rimuove l'unità sia logicamente che graficamente
				else {
					Player p = gameMode.getPlayer(otherUnit.getPlayer());
					p.rimuoviUnità(otherUnit);
					nuovo.setUnit(null);
					eG2.newSet(newSelected, xC, yC, raggio);
					img = nuovo.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);

					/*
					 * setto il numero di unità rimanenti a 0 così da aggiornare
					 * correttamente la label del CommandPanel
					 */
					otherUnit.setNumUnits(0);

				}

				/*
				 * Se dopo il contrattacco il battaglione che ha attaccato
				 * per primo ha ancora almeno un'unità setto i suoi attributi
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
				//Altrimenti si rimuove l'unità sia logicamente che graficamente
				else {
					Player p = gameMode.getPlayer(selectedUnit.getPlayer());
					p.rimuoviUnità(selectedUnit);
					vecchio.setUnit(null);
					eG2.newSet(oldSelected, xC, yC, raggio);
					img = vecchio.getTerritorio().getImage();
					mappaGrafica.paintImage(g2, eG2, img);
					/*
					 * setto il numero di unità rimanenti a 0 così da aggiornare
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
			EsagonoGrafico eG2, Unità selectedUnit, Unità otherUnit, Image img,
			int newSelected, int oldSelected) {

		selectedUnit = vecchio.getUnit();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		/*Posso effettivamente accorpare un'unità coon un'altra se:
		 * -la seconda può essere raggiunta dalla prima
		 * -la seconda si trova in una delle adiacenze dell'esagono della prima
		 * -se le due unità sono dello stesso tipo*/
		if (esagoniRaggiungibili.contains(nuovo) && vecchio.isAdiacente(nuovo)
				&& !vecchio.equals(nuovo)) {
			otherUnit = nuovo.getUnit();
			if (otherUnit != null && selectedUnit.isSameUnitOf(otherUnit)) {
				gameMode.getSound().startMoveMusic();
				
				//Setto i valori della nuova unità che si è creata dall'accorpamento
				int numUnits = selectedUnit.getNumUnits() + otherUnit.getNumUnits();
				double esperienza = (selectedUnit.getEsp()
						* selectedUnit.getNumUnits() + otherUnit.getEsp()
						* otherUnit.getNumUnits())
						/ numUnits;
				/*
				 * si settare come valore dei passi rimanenti della nuova unità
				 * il risultato della sottrazione tra il numero di passi dell'unità
				 * che ne aveva rimasti di meno e il costo di attraversamento del
				 * territorio di partenza. Va distinto però il caso dell'aereo per
				 * il quale il costo di attraversamento è sempre unitario
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
				player.rimuoviUnità(selectedUnit);

				// cancello l'unità accorpata
				vecchio.setUnit(null);
			}
		}
		//esco dall'accorpaMode
		gameMode.setAccorpaMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}

	private static void scorporaMode(Esagono nuovo, Esagono vecchio,
			EsagonoGrafico eG2, Unità selectedUnit, Unità otherUnit, Image img,
			int newSelected, int oldSelected) {

		// selectedUnit è l'unità da scorporare
		selectedUnit = vecchio.getUnit();
		int turno = gameMode.getTurno();
		LinkedList<Esagono> esagoniRaggiungibili = selectedUnit
				.getEsagoniRaggiungibili();

		/*
		 * posso scorporare l'unità solo se:
		 * -l'esagono di destinazione è raggiungibile dall'unità
		 * -non è presente alcuna unità su di esso
		 * -l'esagono destinazione è adiacente a quello dell'unità
		 */
		if (esagoniRaggiungibili.contains(nuovo)) {
			otherUnit = nuovo.getUnit();
			if (otherUnit == null && vecchio.isAdiacente(nuovo)) {
				gameMode.getSound().startMoveMusic();
				double esp = selectedUnit.getEsp();

				/*
				 * scorporando un battaglione di unità se ne ottengono 2 aventi
				 * la metà delle unità di partenza
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
				 * setto i valori della nuova unità in base ai valori dell'unità
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
				player.aggiungiUnità(otherUnit);

			}
		}

		//esco dallo scorporaMode
		gameMode.setScorporaMode(false);
		gameMode.getGameWin().repaint();
		gameMode.getGameWin().validate();
	}
}
