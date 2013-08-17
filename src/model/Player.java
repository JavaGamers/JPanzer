package model;

import java.util.LinkedList;
import java.util.List;

public class Player {
	private String nome;
	private int player;
	private boolean turno;
	private int soldi;
	private LinkedList<Unit�> unitList;
	public static final int STDMONEY = 1000;
	public static final int MINMONEY = 500;
	public static final int MAXMONEY = 2000;
	public static final int MONEYPERTURNO = 100;
	
	
	public Player(String nome, int player){
		if(player<1||player>2){
			throw new IllegalArgumentException("Si pu� giocare solo in 2");
		}
		this.nome = nome;
		this.player=player;
		this.turno=false;
		this.soldi= STDMONEY;
		this.unitList = new LinkedList<Unit�>();
		
	}
	
	public Player(int player){
		if(player<1||player>2){
			throw new IllegalArgumentException("Si pu� giocare solo in 2");
		}
		this.nome = "Player "+player;
		this.player=player;
		this.turno=false;
		this.soldi= STDMONEY;
		this.unitList = new LinkedList<Unit�>();
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public int getPlayer(){
		return this.player;
	}
	
	public int getSoldi(){
		return this.soldi;
	}
	
	public List<Unit�> getUnitList(){
		return this.unitList;
	}
	
	public boolean isTurno(){
		return this.turno;
	}
	
	public void setTurno(){
		this.turno=true;
	}
	
	public void setNome(String nome){
		this.nome=nome;
	}
	
	public void setMoney(int dindi){
		if(dindi<0){
			throw new IllegalArgumentException("non puoi andare in debito");
		}
		this.soldi= dindi;
	}
	
	public boolean aggiungiUnit�(Unit� u){
		boolean aggiunto = false;
		if(!this.unitList.contains(u)){
			this.unitList.add(u);
			aggiunto = true;
		}
		return aggiunto;
	}
	
	public boolean rimuoviUnit�(Unit� u){
		return this.unitList.remove(u);
	}
	
	public boolean hasUnits(){
		return !this.unitList.isEmpty();
	}
	
}
