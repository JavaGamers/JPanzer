package controller;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import view.CommandPanel;
import view.GameWin;

public class StartPanelListener implements ActionListener {
	public static GameMode gameMode = GameMode.getGameMode();
	public final static String NEWOPT = "new";
	public final static String LOADOPT = "load";
	public final static String EDITOPT = "edit";
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(NEWOPT)){
			newOpt();
		}
		else if(com.equals(LOADOPT)){
			loadOpt();
		}
		else if(com.equals(EDITOPT)){
			editOpt();
		}

	}

	private void editOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitMapPanel(),BorderLayout.CENTER);
		
		//ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();
		
	}

	private void loadOpt() {
		JFileChooser jfc = new JFileChooser();
		int returnVal = jfc.showOpenDialog(gameMode.getInitMapPanel());
		 
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
    				gameMode.setMappa(m);
    				if(gameMode.getMappaGrafica()==null){
    					gameMode.createAndSetMappaGrafica();
    				}
    				GameWin gameWin = gameMode.getGameWin();
    				CommandPanel commandPanel = gameMode.getCommandPanel();
    				
    				Container c = gameWin.getContentPane();
    				
    				// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
    				c.removeAll();
    				c.add(gameMode.getMappaGrafica(),BorderLayout.WEST);
    				c.add(commandPanel, BorderLayout.EAST);
    				//ridisegno della finestra
    				gameWin.repaint();
    				gameWin.validate();
    				
    			} 
    			catch (IOException ioException) {
    			}
    		}
        }
	}

	private void newOpt() {
		GameWin gameWin = gameMode.getGameWin();
		Container c = gameWin.getContentPane();
		
		// rimuovo gli eventuali altri pannelli presenti sulla finestra e aggiungo quelli nuovi
		c.removeAll();
		c.add(gameMode.getInitGame(),BorderLayout.CENTER);
		
		//ridisegno della finestra
		gameWin.repaint();
		gameWin.validate();
		
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
