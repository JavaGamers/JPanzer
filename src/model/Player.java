package model;

import java.util.LinkedList;
import java.util.List;

public class Player {

	private String nome;
	private int player;
	private int soldi;
	private LinkedList<Unit�> unitList; // Lista di unit� appartenenti al player
	public static final int STDMONEY = 1000;
	public static final int MINMONEY = 500;
	public static final int MAXMONEY = 2000;
	public static final int MONEYPERTURNO = 100;// Soldi ricevuti ogni turno

	public Player(String nome, int player) {
		if (player < 1 || player > 2) {
			throw new IllegalArgumentException("Si pu� giocare solo in 2");
		}
		this.nome = nome;
		this.player = player;
		this.soldi = STDMONEY;
		this.unitList = new LinkedList<Unit�>();

	}

	public Player(int player) {
		if (player < 1 || player > 2) {
			throw new IllegalArgumentException("Si pu� giocare solo in 2");
		}
		this.nome = "Player " + player;
		this.player = player;
		this.soldi = STDMONEY;
		this.unitList = new LinkedList<Unit�>();
	}

	public String getNome() {
		return this.nome;
	}

	public int getPlayer() {
		return this.player;
	}

	public int getSoldi() {
		return this.soldi;
	}

	public List<Unit�> getUnitList() {
		return this.unitList;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMoney(int money) {
		if (money < 0) {
			throw new IllegalArgumentException("non puoi andare in debito");
		}
		this.soldi = money;
	}

	//Aggiunge un'unit� alla lista di unit� del player
	public boolean aggiungiUnit�(Unit� u) {
		boolean aggiunto = false;
		if (!this.unitList.contains(u)) {
			this.unitList.add(u);
			aggiunto = true;
		}
		return aggiunto;
	}

	//Rimuove un'unit� dalla lista di unit� del player
	public boolean rimuoviUnit�(Unit� u) {
		return this.unitList.remove(u);
	}

	//Ritorna true se il player possiede almeno un'unit�
	public boolean hasUnits() {
		return !this.unitList.isEmpty();
	}

	public String toString() {
		String s = "";
		s += this.nome;
		s += "-";
		s += this.player;
		s += "-";
		s += this.soldi;
		return s;
	}

}
