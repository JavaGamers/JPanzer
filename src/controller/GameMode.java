package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Aereo;
import model.Artiglieria;
import model.Collina;
import model.Esagono;
import model.FanteriaLeggera;
import model.FanteriaPesante;
import model.Foresta;
import model.Lago;
import model.Mappa;
import model.Montagna;
import model.Panzer;
import model.Pianura;
import model.Player;
import model.Unità;
import view.CommandPanel;
import view.FinalPanel;
import view.GameWin;
import view.InitGame;
import view.InitMapPanel;
import view.LandPanel;
import view.MappaGrafica;
import view.StartPanel;
import view.UnitPanel;

public class GameMode {
	private static GameMode gM = null;
	private GameWin gameWin;
	private Mappa mappa;
	private Player player1;
	private Player player2;
	private MappaGrafica mappaGrafica;
	private CommandPanel commandPanel;
	private InitGame initGame;
	private InitMapPanel initMapPanel;
	private LandPanel landPanel;
	private StartPanel startPanel;
	private UnitPanel unitPanel;
	private FinalPanel finalPanel;
	private int turno;
	private boolean selectionUnitMode;
	private boolean playingMode;
	private boolean movingMode;
	private boolean attackMode;
	private boolean accorpaMode;
	private boolean scorporaMode;
	private boolean zoomOutMode;
	private final static String INTESTAZIONEPARTITA = "Partita Jpanzer";
	private final static String INTESTAZIONEMAPPA = "Mappa Jpanzer";

	private GameMode() {
		this.gameWin = null;
		this.mappa = null;
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		this.mappaGrafica = null;
		this.commandPanel = null;
		this.initGame = null;
		this.initMapPanel = null;
		this.landPanel = null;
		this.startPanel = null;
		this.unitPanel = null;
		this.finalPanel = null;
		this.turno = 1;
		this.selectionUnitMode = false;
		this.playingMode = false;
		this.movingMode = false;
		this.attackMode = false;
		this.accorpaMode = false;
		this.scorporaMode = false;
		this.zoomOutMode = false;
	}

	public boolean isScorporaMode() {
		return this.scorporaMode;
	}

	public boolean isAccorpaMode() {
		return this.accorpaMode;
	}

	public boolean isZoomOutMode() {
		return this.zoomOutMode;
	}

	public int getTurno() {
		return this.turno;
	}

	public boolean isAttackMode() {
		return this.attackMode;
	}

	public boolean isPlayingMode() {
		return this.playingMode;
	}

	public boolean isMovingMode() {
		return this.movingMode;
	}

	public boolean isSelecionUnitMode() {
		return this.selectionUnitMode;
	}

	public GameWin getGameWin() {
		if (this.gameWin == null) {
			this.gameWin = new GameWin("JPanzer");
		}
		return this.gameWin;
	}

	public MappaGrafica getMappaGrafica() {
		return this.mappaGrafica;
	}

	public CommandPanel getCommandPanel() {
		if (this.commandPanel == null) {
			this.commandPanel = new CommandPanel();
		}
		return this.commandPanel;
	}

	public InitGame getInitGame() {
		if (this.initGame == null) {
			this.initGame = new InitGame();
		}
		return this.initGame;
	}

	public InitMapPanel getInitMapPanel() {
		if (this.initMapPanel == null) {
			this.initMapPanel = new InitMapPanel();
		}
		return this.initMapPanel;
	}

	public LandPanel getLandPanel() {
		if (this.landPanel == null) {
			this.landPanel = new LandPanel();
		}
		return this.landPanel;
	}

	public StartPanel getStartPanel() {
		if (this.startPanel == null) {
			this.startPanel = new StartPanel();
		}
		return this.startPanel;
	}

	public UnitPanel getUnitPanel() {
		if (this.unitPanel == null) {
			this.unitPanel = new UnitPanel();
		}
		return this.unitPanel;
	}

	public FinalPanel getFinalPanel() {
		if (this.finalPanel == null) {
			this.finalPanel = new FinalPanel();
		}
		return this.finalPanel;
	}

	public Mappa getMappa() {
		return this.mappa;
	}

	public Player getPlayer(int player) {
		if (player < 1 || player > 2) {
			throw new IllegalArgumentException("Si può giocare solo in 2");
		}
		if (player == 1) {
			return this.player1;
		} else {
			return this.player2;
		}
	}

	public void setScorporaMode(boolean value) {
		this.scorporaMode = value;
	}

	public void setAccorpaMode(boolean value) {
		this.accorpaMode = value;
	}

	public void setAttackMode(boolean value) {
		this.attackMode = value;
	}

	public void setMovingMode(boolean value) {
		this.movingMode = value;
	}

	public void setSelectionUnitMode(boolean value) {
		this.selectionUnitMode = value;
	}

	public void setPlayingMode(boolean value) {
		this.playingMode = value;
	}

	public void setZoomOutMode(boolean value) {
		this.zoomOutMode = value;
	}

	public void setGameWin(GameWin gW) {
		if (gW == null) {
			throw new IllegalArgumentException("GameWin nulla");
		}
		this.gameWin = gW;
	}

	public void setMappa(Mappa m) {
		if (m == null) {
			throw new IllegalArgumentException("Mappa nulla");
		}
		this.mappa = m;
		if (this.mappaGrafica != null) {
			this.mappaGrafica.setMappa(m);
		}
	}

	public void setPlayer(Player p, int num) {
		if (num < 1 || num > 2) {
			throw new IllegalArgumentException(
					"invalid number in setplayer method of gameMode");
		}
		if (num == 1) {
			this.player1 = p;
		} else
			this.player2 = p;
	}

	public void setMappa(int dim) {
		Mappa m = new Mappa(dim);
		setMappa(m);
	}

	public void createAndSetMappaGrafica() {
		if (this.mappa != null) {
			this.mappaGrafica = new MappaGrafica(this.mappa,
					this.gameWin.getWidth() / 2, this.gameWin.getHeight() / 2);
		} else {
			System.out.println("no mappa");
		}
	}

	public void switchTurno() {
		if (this.turno == 1) {
			this.turno = 2;
		} else {
			this.turno = 1;
		}
	}

	public boolean salvaPartita() {
		boolean done = false;
		MappaGrafica mG = getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showSaveDialog(mG);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			FileWriter fw;
			BufferedWriter bw;

			try {

				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);

				// stampo intestazione file
				bw.write(INTESTAZIONEPARTITA + '\n');

				// stampo info sui player
				bw.write("" + this.player1 + '\n' + this.player2 + '\n');
				// stampo il turno
				bw.write("" + this.turno + '\n');
				// stampo la mappa
				bw.write(this.mappa.toString());

				bw.close();
				fw.close();

			} catch (IOException io) {
				System.out.println(io.toString());
			}
			done = true;
		}
		return done;
	}

	public boolean caricaPartita() {
		boolean done = false;
		MappaGrafica mG = getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(mG);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			BufferedReader br;
			LinkedList<String> elements;
			if (file.isFile()) {
				try {
					br = new BufferedReader(new FileReader(file));
					// leggo l'intestazione del file
					String text = br.readLine();
					if (text.equals(INTESTAZIONEPARTITA)) {

						// leggo la 1° riga del file (info player1)
						text = br.readLine();
						elements = getElements(text);
						String nome1 = elements.remove();
						int player1 = Integer.parseInt(elements.remove());
						int money1 = Integer.parseInt(elements.remove());
						Player p1 = new Player(nome1, player1);
						p1.setMoney(money1);
						this.setPlayer(p1, player1);

						// leggo la 2° riga del file (info player2)
						text = br.readLine();
						elements = getElements(text);
						String nome2 = elements.remove();
						int player2 = Integer.parseInt(elements.remove());
						int money2 = Integer.parseInt(elements.remove());
						Player p2 = new Player(nome2, player2);
						p2.setMoney(money2);
						this.setPlayer(p2, player2);

						// leggo la 3° riga (turno)
						text = br.readLine();
						int turno = Integer.parseInt(text);
						this.turno = turno;

						// leggo la 4° riga (dimensione mappa)
						text = br.readLine();
						int dim = Integer.parseInt(text);
						Mappa m = new Mappa(dim);

						Esagono e;
						// leggo le altre righe
						while ((text = br.readLine()) != null) {
							elements = getElements(text);
							e = m.getComponent()[Integer.parseInt(elements
									.remove())];
							// setto il territorio dell'esagono
							String territorio = elements.remove();
							if (territorio.equals("Pianura")) {
								e.setTerritorio(new Pianura());
							} else if (territorio.equals("Collina")) {
								e.setTerritorio(new Collina());
							} else if (territorio.equals("Montagna")) {
								e.setTerritorio(new Montagna());
							} else if (territorio.equals("Lago")) {
								e.setTerritorio(new Lago());
							} else if (territorio.equals("Foresta")) {
								e.setTerritorio(new Foresta());
							} else {
								e.setTerritorio(null);
							}

							// controllo se è presento un'unità sul territorio
							String nomeUnità = elements.remove();
							if (!nomeUnità.equals(" ")) {
								int player = Integer
										.parseInt(elements.remove());
								int numUnits = Integer.parseInt(elements
										.remove());
								double esp = Double.parseDouble(elements
										.remove());
								int passi = Integer.parseInt(elements.remove());
								boolean alreadyAttacked = Boolean
										.parseBoolean(elements.remove());

								if (nomeUnità.equals("aereo")) {
									e.setUnit(new Aereo(numUnits, player));

								} else if (nomeUnità.equals("artiglieria")) {
									e.setUnit(new Artiglieria(numUnits, player));

								} else if (nomeUnità.equals("fanterialeggera")) {
									e.setUnit(new FanteriaLeggera(numUnits,
											player));

								} else if (nomeUnità.equals("fanteriapesante")) {
									e.setUnit(new FanteriaPesante(numUnits,
											player));

								} else if (nomeUnità.equals("panzer")) {
									e.setUnit(new Panzer(numUnits, player));

								}
								e.getUnit().setEsp(esp);
								e.getUnit().setPassi(passi);
								e.getUnit().setAlreadyAttack(alreadyAttacked);
							}

						}
						br.close();

						// setto la nuova mappa nel GameMode
						this.setMappa(m);
						this.createAndSetMappaGrafica();

						done = true;
					} else {
						/*
						 * this.getErrorWindow().setErrorLabel(
						 * "devi caricare un file partita valido");
						 * this.getErrorWindow().setVisible(true);
						 */

						JOptionPane.showMessageDialog(this.gameWin,
								"devi caricare un file partita valido",
								"ERRORE!", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException ioException) {
				}
			}

		}
		return done;
	}

	public boolean salvaMappa() {
		boolean done = false;
		MappaGrafica mG = getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showSaveDialog(mG);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			FileWriter fw;
			BufferedWriter bw;

			try {

				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				// scrivo intestazione mappa
				bw.write(INTESTAZIONEMAPPA + '\n');

				bw.write(getMappa().toString());

				bw.close();
				fw.close();

			} catch (IOException io) {
				System.out.println(io.toString());
			}
			done = true;
		}
		return done;
	}

	public boolean caricaMappa() {
		boolean done = false;
		MappaGrafica mG = getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(mG);
		int i = 0;

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			BufferedReader br;
			LinkedList<String> elements;
			if (file.isFile()) {
				try {
					br = new BufferedReader(new FileReader(file));
					// leggo intestazione mappa
					String text = br.readLine();
					if (text.equals(INTESTAZIONEMAPPA)) {

						// leggo la 1° riga del file
						text = br.readLine();
						int dim = Integer.parseInt(text);
						Mappa m = new Mappa(dim);
						Esagono e;
						// leggo le altre righe

						while ((text = br.readLine()) != null) {
							i++;
							elements = getElements(text);
							e = m.getComponent()[Integer.parseInt(elements
									.remove())];
							String territorio = elements.remove();
							// setto il territorio dell'esagono
							if (territorio.equals("Pianura")) {
								e.setTerritorio(new Pianura());
							} else if (territorio.equals("Collina")) {
								e.setTerritorio(new Collina());
							} else if (territorio.equals("Montagna")) {
								e.setTerritorio(new Montagna());
							} else if (territorio.equals("Lago")) {
								e.setTerritorio(new Lago());
							} else if (territorio.equals("Foresta")) {
								e.setTerritorio(new Foresta());
							} else {
								e.setTerritorio(null);
							}
						}
						br.close();

						// setto la nuova mappa nel pannello
						this.setMappa(m);
						this.createAndSetMappaGrafica();

						done = true;
					} else {
						JOptionPane.showMessageDialog(this.gameWin,
								"devi caricare un file mappa valido");
					}
				} catch (IOException ioException) {
					System.out.println(ioException.toString());
				}
			}
		}
		System.out.println(i);
		return done;
	}

	private static LinkedList<String> getElements(String s) {
		LinkedList<String> elements = new LinkedList<String>();
		StringTokenizer str = new StringTokenizer(s, "-");
		while (str.hasMoreTokens()) {
			elements.add(str.nextToken());
		}
		return elements;
	}

	/*
	 * metodo per controllare se qualcuno ha vinto la partita valori di ritorno:
	 * 0 nessuo ha ancora vinto - 1 vittoria player 1 - 2 vittoria player 2 - 3
	 * pareggio
	 */
	public int checkVictory() {
		int victory = 0;
		List<Unità> p1Unit = this.player1.getUnitList();
		List<Unità> p2Unit = this.player2.getUnitList();
		if (p1Unit.isEmpty() && p2Unit.isEmpty())
			victory = 3;
		else if (p1Unit.isEmpty())
			victory = 2;
		else if (p2Unit.isEmpty())
			victory = 1;

		return victory;
	}

	public void resetAll() {
		this.mappa = null;
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		this.mappaGrafica = null;
		this.initGame = null;
		this.commandPanel = null;
		this.finalPanel = null;
		this.unitPanel = null;
		this.turno = 1;
		this.selectionUnitMode = false;
		this.playingMode = false;
		this.movingMode = false;
		this.attackMode = false;
		this.accorpaMode = false;
		this.scorporaMode = false;
		this.zoomOutMode = false;
	}

	// Metodo della classe impiegato per accedere al Singleton
	public static synchronized GameMode getGameMode() {
		if (gM == null) {
			gM = new GameMode();
		}
		return gM;
	}
}
