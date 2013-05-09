package model;

public class Test {

	public static void main(String[] args) {
		int id = 26;
		int[] coord = Esagono.getCoord(id);
		System.out.println("Settore "+ coord[0]+ " Livello "+ coord[1]+ " Posizione "+coord[2]);

	}

}
