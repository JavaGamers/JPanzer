package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitListener implements ActionListener {
	
	public final static String FANTLEGGOPT = "fanteriaLegg";
	public final static String FANTPESOPT = "fanteriaPes";
	public final static String PANZEROPT = "panzer";
	public final static String AEREOOPT = "aereo";
	public final static String ARTIGLIERIAOPT = "artiglieria";
	public final static String STARTOPT = "start";
	
	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(FANTLEGGOPT)){
			fanteriaLeggOpt();
		}
		else if(com.equals(FANTPESOPT)){
			fanteriaPesOpt();
		}
		else if(com.equals(PANZEROPT)){
			panzerOpt();
		}
		else if(com.equals(AEREOOPT)){
			aereoOpt();
		}
		else if(com.equals(ARTIGLIERIAOPT)){
			artiglieriaOpt();
		}
		else if(com.equals(STARTOPT)){
			startOpt();
		}
	}


	private void startOpt() {
		// TODO Auto-generated method stub
		
	}


	private void artiglieriaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void aereoOpt() {
		// TODO Auto-generated method stub
		
	}


	private void panzerOpt() {
		// TODO Auto-generated method stub
		
	}


	private void fanteriaPesOpt() {
		// TODO Auto-generated method stub
		
	}


	private void fanteriaLeggOpt() {
		// TODO Auto-generated method stub
		
	}

}
