package model;

public class Utilities {
	
	//Da Esagono
	
	// metodo per calcolare n mod p
	public static int mod(int n, int base) {
		if (n >= base)
			n = n % base;
		return n;
	}
	
	//Da Esagono
	// metodo per calcolare il valore iniziale dato il livello
	public static int startLiv(int x) {
		return (3 * x * x - 3 * x + 1);
	}

	//Da Esagono
	// metodo per calcolare il valore finale dato il livello
	public static int endLiv(int x) {
		return (3 * x * x + 3 * x);
	}
	
	//Da Esagono
	public static int sup(double d) {
		int s = 0;

		if (d < 0 || d > 6) {
			throw new IllegalArgumentException(
					"Use double value between 0 and 6");
		}
		if (d < 1)
			s = 1;
		else if (d < 2)
			s = 2;
		else if (d < 3)
			s = 3;
		else if (d < 4)
			s = 4;
		else if (d < 5)
			s = 5;
		else if (d < 6)
			s = 6;
		return s;
	}
	
	
	//Da MappaListener
	public static int calulateMoneyEarned(int prevNum, int postNum, Unità u) {
		int gain = 0;
		int diff = prevNum - postNum;
		double percent = 2 / 5;

		if (diff > prevNum) {
			diff = prevNum;
		}

		double d = diff * percent / 10;

		if (u instanceof Artiglieria) {
			gain = (int) (d * Artiglieria.COSTO);
		} else if (u instanceof Aereo) {
			gain = (int) (d * Aereo.COSTO);
		} else if (u instanceof FanteriaPesante) {
			gain = (int) (d * FanteriaPesante.COSTO);
		} else if (u instanceof FanteriaLeggera) {
			gain = (int) (d * FanteriaLeggera.COSTO);
		} else if (u instanceof Panzer) {
			gain = (int) (d * Panzer.COSTO);
		}
		return gain;
	}

}
