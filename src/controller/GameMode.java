package controller;

import model.GraphMap;
import model.Mappa;
import model.Player;
import view.CommandPanel;
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
	}
	
	public GameWin getGameWin(){
		return this.gameWin;
	}
	
	public MappaGrafica getMappaGrafica(){
		return this.mappaGrafica;
	}
	
	public CommandPanel getCommandPanel(){
		return this.commandPanel;
	}
	
	public InitGame getInitGame(){
		return this.initGame;
	}
	
	public InitMapPanel getInitMapPanel(){
		return this.initMapPanel;
	}
	
	public LandPanel getLandPanel(){
		return this.landPanel;
	}
	
	public StartPanel getStartPanel(){
		return this.startPanel;
	}
	
	public UnitPanel getUnitPanel(){
		return this.unitPanel;
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
		this.setGraphMap(mappa);
	}
	
	private void setGraphMap(Mappa m){
		this.graphMap= new GraphMap(m);
	}
	
	public void createAndSetMappa(int dim){
		Mappa m = new Mappa(dim);
		setMappa(m);
	}
	
	public void createAndSetMappaGrafica(){
		if(this.mappa!=null){
			this.mappaGrafica = new MappaGrafica(this.mappa, 700, 500);
		}
		else{
			System.out.println("no mappa");
		}
	}
	
	public void createAndSetCommandPanel(){
		this.commandPanel = new CommandPanel();
	}
	
	public void createAndSetInitGame(){
		this.initGame = new InitGame();
	}
	
	public void createAndSetInitMapPanel(){
		this.initMapPanel = new InitMapPanel();
	}
	
	public void createAndSetLandPanel(){
		this.landPanel = new LandPanel();
	}
	
	public void createAndSetStartPanel(){
		this.startPanel = new StartPanel();
	}
	
	public void createAndSetUnitPanel(){
		this.unitPanel = new UnitPanel();
	}
	
	// Metodo della classe impiegato per accedere al Singleton
    public static synchronized GameMode getGameMode() {
        if (gM == null){
            gM = new GameMode();
        }
        return gM;
    }
}
