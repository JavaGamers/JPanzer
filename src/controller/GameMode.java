package controller;

import model.GraphMap;
import model.Mappa;
import model.Player;
import view.GameWin;

public class GameMode {
	private static GameMode gM = null;
	private GameWin gameWin;
	private Mappa mappa;
	private Player player1;
	private Player player2;
	private GraphMap graphMap;
	
	
	private GameMode(){
		this.gameWin= null;
		this.mappa=null;
		this.player1 = new Player(1);
		this.player2 = new Player(2);
		this.graphMap=null;
	}
	
	public GameWin getGameWin(){
		return this.gameWin;
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
	
	// Metodo della classe impiegato per accedere al Singleton
    public static synchronized GameMode getGameMode() {
        if (gM == null){
            gM = new GameMode();
        }
        return gM;
    }
}
