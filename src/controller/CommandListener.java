package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;

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

import view.GameWin;
import view.MappaGrafica;

public class CommandListener implements ActionListener {
	public final static String ZOOMOPT = "zoom";
	public final static String MUOVIOPT = "muovi";
	public final static String ATTACCAOPT = "attacca";
	public final static String ABBANDONAOPT = "abbandona";
	public final static String SALVAOPT = "salva";
	public final static String CARICAOPT = "carica";
	public final static String SCORPORAOPT = "scorpora";
	public final static String ACCORPAOPT = "accorpa";
	public final static String PASSAOPT = "passa";
	public final static String SHOPOPT = "shop";
	public static GameMode gameMode = GameMode.getGameMode();

	

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(ZOOMOPT)){
			zoomOpt();
		}
		else if(com.equals(MUOVIOPT)){
			muoviOpt();
		}
		else if(com.equals(ABBANDONAOPT)){
			abbandonaOpt();
		}
		else if(com.equals(ACCORPAOPT)){
			accorpaOpt();
		}
		else if(com.equals(ATTACCAOPT)){
			attaccaOpt();
		}
		else if(com.equals(CARICAOPT)){
			caricaOpt();
		}
		else if(com.equals(PASSAOPT)){
			passaOpt();
		}
		else if(com.equals(SALVAOPT)){
			salvaOpt();
		}
		else if(com.equals(SCORPORAOPT)){
			scorporaOpt();
		}
		else if(com.equals(SHOPOPT)){
			shopOpt();
		}
		
	}

	private void shopOpt() {
		// TODO Auto-generated method stub
		
	}

	private void scorporaOpt() {
		// TODO Auto-generated method stub
		
	}

	// da rivedere
	private void salvaOpt() {
		GameWin gW = gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		JFileChooser jfc = new JFileChooser();		
		int returnVal = jfc.showSaveDialog(mG);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	File file = jfc.getSelectedFile();  
            FileWriter fw;
    		BufferedWriter bw;
    		
    		try{
    			
    		fw = new FileWriter(file);
    		bw = new BufferedWriter(fw);
    		
    		bw.write(""+mG.getMappa().getDim());
    		bw.write("\n");
    		//manca di scrivere di chi è il turno
    		for(int i=0;i<mG.getMappa().getComponent().length;i++){
    			bw.write(mG.getMappa().getComponent()[i].saveToString());
    			bw.write("\n");
    		}
    		bw.close();
    		fw.close();
    		
    		} catch(IOException io){
    			System.out.println(io.toString());
    		}
        }
        		
	}


	private void passaOpt() {
		gameMode.switchTurno();
		
	}

	// da rivedere
	private void caricaOpt() {
		GameWin gW = this.gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(mG);
		 
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();  
            BufferedReader br;
    		String[] elements;
    		if (file.isFile()) {
    			try {
    				br = new BufferedReader(new FileReader(file));
    				// leggo la 1° riga del file
    				String text = br.readLine();
    				int dim = Integer.parseInt(text);
    				Mappa m = new Mappa(dim);
    				//manca di leggere di chi è il turno
    				Esagono e;
    				int player=0;	// variabile d'appoggio inizializzata a 0 per comodità e ridefinita in seguito
    				//leggo le altre righe
    				while ((text = br.readLine()) != null){
    					elements=getElements(text);
    					e = m.getComponent()[Integer.parseInt(elements[0])];
    					// setto il territorio dell'esagono
    					if(elements[1].equals("Pianura")){
    						e.setTerritorio(new Pianura());
    					}
    					else if(elements[1].equals("Collina")){
    						e.setTerritorio(new Collina());
    					}
    					else if(elements[1].equals("Montagna")){
    						e.setTerritorio(new Montagna());
    					}
    					else if(elements[1].equals("Lago")){
    						e.setTerritorio(new Lago());
    					}
    					else if(elements[1].equals("Foresta")){
    						e.setTerritorio(new Foresta());
    					}
    					else{
    						e.setTerritorio(null);
    					}
    					// controllo se è presente un'unità sul territorio
    					if(!elements[3].equals(" ")){
    						player= Integer.parseInt(elements[3]);
    					}
    					
    					/* se player = 0 significa che non è stata ridefinita e quindi non è presente alcun unità sull'esagono
    					 se player = 0 allora sono sicuro che i campi elements[i] con i=3,4,5 contengono valori corretti e posso
    					 evitare di controllarli
    					 */
    					if(player!=0){
    						if(elements[3].equals("aereo")){
    							e.setUnit(new Aereo(Integer.parseInt(elements[4]),player));
    							e.getUnit().setEsp(Integer.parseInt(elements[5]));
    						}
    						else if(elements[3].equals("artiglieria")){
    							e.setUnit(new Artiglieria(Integer.parseInt(elements[4]),player));
    							e.getUnit().setEsp(Integer.parseInt(elements[5]));
    						}
    						else if(elements[3].equals("fanterialeggera")){
    							e.setUnit(new FanteriaLeggera(Integer.parseInt(elements[4]),player));
    							e.getUnit().setEsp(Integer.parseInt(elements[5]));
    						}
    						else if(elements[3].equals("fanteriapesante")){
    							e.setUnit(new FanteriaPesante(Integer.parseInt(elements[4]),player));
    							e.getUnit().setEsp(Integer.parseInt(elements[5]));
    						}
    						else if(elements[3].equals("panzer")){
    							e.setUnit(new Panzer(Integer.parseInt(elements[4]),player));
    							e.getUnit().setEsp(Integer.parseInt(elements[5]));
    						}
    					}
    					
    					
    				}
    				br.close();
    				
    				//setto la nuova mappa nel pannello
    				mG.setMappa(m);
    			} 
    			catch (IOException ioException) {
    			}

    		}
        }
		
	}

	private void attaccaOpt() {	
		
	}


	private void accorpaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void abbandonaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void muoviOpt() {
		// TODO Auto-generated method stub
		
	}


	private void zoomOpt() {
		GameWin gameWin = gameMode.getGameWin();
		MappaGrafica mG = gameMode.getMappaGrafica();
		if(mG.getRaggio()== MappaGrafica.STDRAGGIO){
			mG.setRaggio(MappaGrafica.ZOOMRAGGIO);
		}
		else{
			mG.setRaggio(MappaGrafica.STDRAGGIO);
		}
		gameWin.repaint();
		mG.validate();
	}
	
	private static String[] getElements(String s){
		String[] elements= new String[6];
		StringTokenizer str = new StringTokenizer(s,"-");
		int i=0;
		while(str.hasMoreTokens()){
			elements[i]= str.nextToken();
			i++;
		}
		return elements;
	}

}
