package model;

public class Player {
	private String nome;
	private int player;
	private boolean turno;
	private int soldi;
	public static final int STDMONEY = 1000;
	public static final int MINMONEY = 500;
	public static final int MAXMONEY = 2000;
	
	
	public Player(String nome, int player){
		if(player<1||player>2){
			throw new IllegalArgumentException("Si può giocare solo in 2");
		}
		this.nome = nome;
		this.player=player;
		this.turno=false;
		this.soldi= STDMONEY;
		
	}
	
	public Player(int player){
		if(player<1||player>2){
			throw new IllegalArgumentException("Si può giocare solo in 2");
		}
		this.nome = "";
		this.player=player;
		this.turno=false;
		this.soldi= STDMONEY;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public int getPlayer(){
		return this.player;
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
	
}
