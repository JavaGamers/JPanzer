package controller;

import java.util.List;

import model.Mappa;
import model.Player;
import model.Unità;
import view.CommandPanel;
import view.ErrorWindow;
import view.FinalPanel;
import view.GameWin;
import view.InitGame;
import view.InitMapPanel;
import view.LandPanel;
import view.LeavingWin;
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
	private ErrorWindow errorWindow;
	private LeavingWin leavingWin;
	private int turno;
	private boolean selectionUnitMode;
	private boolean playingMode;
	private boolean movingMode;
	private boolean attackMode;
	private boolean accorpaMode;
	private boolean scorporaMode;
	private boolean zoomOutMode;
	
	
	private GameMode(){
		this.gameWin= null;
		this.mappa=null;
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		this.mappaGrafica=null;
		this.commandPanel=null;
		this.initGame=null;
		this.initMapPanel=null;
		this.landPanel=null;
		this.startPanel=null;
		this.unitPanel=null;
		this.finalPanel = null;
		this.errorWindow= null;
		this.turno=1;
		this.selectionUnitMode=false;
		this.playingMode=false;
		this.movingMode = false;
		this.attackMode = false;
		this.accorpaMode = false;
		this.scorporaMode = false;
		this.zoomOutMode = false;
	}
	
	public boolean isScorporaMode(){
		return this.scorporaMode;
	}
	
	public boolean isAccorpaMode(){
		return this.accorpaMode;
	}
	
	public boolean isZoomOutMode(){
		return this.zoomOutMode;
	}
	
	public int getTurno(){
		return this.turno;
	}
	
	public boolean isAttackMode(){
		return this.attackMode;
	}
	
	public boolean isPlayingMode(){
		return this.playingMode;
	}
	
	public boolean isMovingMode(){
		return this.movingMode;
	}
	
	public boolean isSelecionUnitMode(){
		return this.selectionUnitMode;
	}
	
	public ErrorWindow getErrorWindow(){
		if(this.errorWindow==null){
			this.errorWindow = new ErrorWindow();
		}
		return this.errorWindow;
	}
	
	public LeavingWin getLeavingWin(){
		if(this.leavingWin==null){
			this.leavingWin = new LeavingWin();
		}
		return this.leavingWin;
	}
	
	public GameWin getGameWin(){
		if(this.gameWin==null){
			this.gameWin = new GameWin("JPanzer");
		}
		return this.gameWin;
	}
	
	public MappaGrafica getMappaGrafica(){
		return this.mappaGrafica;
	}
	
	public CommandPanel getCommandPanel(){
		if(this.commandPanel==null){
			this.commandPanel = new CommandPanel();
		}
		return this.commandPanel;
	}
	
	public InitGame getInitGame(){
		if(this.initGame==null){
			this.initGame = new InitGame();
		}
		return this.initGame;
	}
	
	public InitMapPanel getInitMapPanel(){
		if(this.initMapPanel==null){
			this.initMapPanel = new InitMapPanel();
		}
		return this.initMapPanel;
	}
	
	public LandPanel getLandPanel(){
		if(this.landPanel==null){
			this.landPanel = new LandPanel();
		}
		return this.landPanel;
	}
	
	public StartPanel getStartPanel(){
		if(this.startPanel==null){
			this.startPanel = new StartPanel();
		}
		return this.startPanel;
	}
	
	public UnitPanel getUnitPanel(){
		if(this.unitPanel==null){
			this.unitPanel = new UnitPanel();
		}
		return this.unitPanel;
	}
	
	public FinalPanel getFinalPanel(){
		if(this.finalPanel == null){
			this.finalPanel = new FinalPanel();
		}
		return this.finalPanel;
	}
	
	public Mappa getMappa(){
		return this.mappa;
	}
	
	public Player getPlayer(int player){
		if(player<1||player>2){
			throw new IllegalArgumentException("Si può giocare solo in 2");
		}
		if(player==1){
			return this.player1;
		}
		else{
			return this.player2;
		}
	}
	
	public void setScorporaMode(boolean value){
		this.scorporaMode = value;
	}
	
	public void setAccorpaMode(boolean value){
		this.accorpaMode = value;
	}
	
	public void setAttackMode(boolean value){
		this.attackMode=value;
	}
	
	public void setMovingMode(boolean value){
		this.movingMode=value;
	}
	
	public void setSelectionUnitMode(boolean value){
		this.selectionUnitMode=value;
	}
	
	public void setPlayingMode(boolean value){
		this.playingMode=value;
	}
	
	public void setZoomOutMode(boolean value){
		this.zoomOutMode = value;
	}
	
	public void setGameWin(GameWin gW){
		if(gW==null){
			throw new IllegalArgumentException("GameWin nulla");
		}
		this.gameWin=gW;
	}
	
	public void setMappa(Mappa m){
		if(m==null){
			throw new IllegalArgumentException("Mappa nulla");
		}
		this.mappa=m;
		if(this.mappaGrafica!=null){
			this.mappaGrafica.setMappa(m);
		}
	}
		
	public void setPlayer(Player p, int num){
		if(num<1 || num>2){
    		throw new IllegalArgumentException("invalid number in setplayer method of gameMode");
    	}
    	if(num==1){
    		this.player1=p;
    	}
    	else
    		this.player2=p;
	}
	
	public void setMappa(int dim){
		Mappa m = new Mappa(dim);
		setMappa(m);
	}
	
	public void createAndSetMappaGrafica(){
		if(this.mappa!=null){
			this.mappaGrafica = new MappaGrafica(this.mappa, this.gameWin.getWidth()/2,this.gameWin.getHeight()/2);
		}
		else{
			System.out.println("no mappa");
		}
	}
	
	public void switchTurno(){
		if(this.turno==1){
			this.turno=2;
		}
		else{
			this.turno=1;
		}
	}
	
	public void salvaPartita(){
		
	}
	
	public void caricaPartita(){
		
	}
	
	/* metodo per controllare se qualcuno ha vinto la partita
	   valori di ritorno: 0 nessuo ha ancora vinto - 1 vittoria player 1 - 2 vittoria player 2 - 3 pareggio*/
	public int checkVictory(){
		int victory = 0;
		List<Unità> p1Unit = this.player1.getUnitList();
		List<Unità> p2Unit = this.player2.getUnitList();
		if(p1Unit.isEmpty() && p2Unit.isEmpty())
			victory = 3;
		else if(p1Unit.isEmpty())
			victory = 2;
		else if(p2Unit.isEmpty())
			victory = 1;
		
		return victory;	
	}
	
	public void resetAll(){
		this.mappa=null;
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		this.mappaGrafica=null;
		this.commandPanel=null;
		this.initGame=null;
		this.initMapPanel=null;
		this.landPanel=null;
		this.startPanel=null;
		this.unitPanel=null;
		this.finalPanel = null;
		this.errorWindow= null;
		this.turno=1;
		this.selectionUnitMode=false;
		this.playingMode=false;
		this.movingMode = false;
		this.attackMode = false;
		this.accorpaMode = false;
		this.scorporaMode = false;
		this.zoomOutMode = false;
	}
	
	// Metodo della classe impiegato per accedere al Singleton
    public static synchronized GameMode getGameMode() {
        if (gM == null){
            gM = new GameMode();
        }
        return gM;
    }
}
