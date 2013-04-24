package controller;

public class GameMode {
	private static GameMode gM = null;
	
	private GameMode(){
		
	}
	
	// Metodo della classe impiegato per accedere al Singleton
    public static synchronized GameMode getGameMode() {
        if (gM == null) 
            gM = new GameMode();
        return gM;
    }
    
    public void manageAction(String action){
    	
    }

}
