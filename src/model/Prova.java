package model;

public class Prova {

	public static void main(String[] args) {
		Mappa m = new Mappa(4);
		Esagono e = null;
		Esagono e2 = null;
		for (int i = 0; i < m.getComponent().length; i++) {
			e = m.getComponent()[i];
			System.out.println("Esagono " + e.getId());
			for (int j = 0; j < 6; j++) {
				e2 = e.getAdiacenze()[j];
				if (e2 != null) {
					System.out.println(e2.getId());
				} else {
					System.out.println("null");
				}
			}
			System.out.println();
		}
	}
}
