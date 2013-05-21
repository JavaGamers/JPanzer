package controller;

import model.GraphMap;
import model.Mappa;
import model.Player;
import view.CommandPanel;
import view.ErrorWindow;
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
	private GraphMap graphMap;
	private MappaGrafica mappaGrafica;
	private CommandPanel commandPanel;
	private InitGame initGame;
	private InitMapPanel initMapPanel;
	private LandPanel landPanel;
	private StartPanel startPanel;
	private UnitPanel unitPanel;
	private ErrorWindow errorWindow;
	private int turno;
	
	
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
		this.errorWindow= new ErrorWindow();
		this.turno=1;
	}
	
	public int getTurno(){
		return this.turno;
	}
	
	public ErrorWindow getErrorWindow(){
		if(this.errorWindow==null){
			this.errorWindow = new ErrorWindow();
		}
		return this.errorWindow;
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
	
	public Mappa getMappa(){// c,jsdbfgvug syvhkw
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
		// setto anche il graphMap in quanto questi 2 oggetti sono strettamente correlati
//		this.setGraphMap(mappa);
		// risetto anche la MappaGrafica per lo stesso motivo
		if(this.mappaGrafica!=null){
			this.mappaGrafica.setMappa(m);
		}
	}
	
	private void setGraphMap(Mappa m){
		this.graphMap= new GraphMap(m);
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
	
	// Metodo della classe impiegato per accedere al Singleton
    public static synchronized GameMode getGameMode() {
        if (gM == null){
            gM = new GameMode();
        }
        return gM;
    }
}
