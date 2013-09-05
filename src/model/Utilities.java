package model;

public class Utilities {


	// metodo per calcolare n mod (base)
	public static int mod(int n, int base) {
		if (n >= base)
			n = n % base;
		return n;
	}

	// metodo per calcolare l'id dell'esagono iniziale del livello x
	public static int startLiv(int x) {
		return (3 * x * x - 3 * x + 1);
	}

	// metodo per calcolare l'id dell'esagono finale del livello x
	public static int endLiv(int x) {
		return (3 * x * x + 3 * x);
	}

	//metodo per ottenere un int corrispondente alla parte intera superiore del double d
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

	//metodo per calcolare il guadagno ottenuto dall'attacco dell'unità u
	public static int calulateMoneyEarned(int prevNum, int postNum, Unità u) {
		int gain = 0;
		double diff = prevNum - postNum;
		double percent = 0.25;

		if (diff > prevNum) {
			diff = prevNum;
		}

		double d = ((diff * percent) / 10.0);

		//gain deve essere proporzionale alle unità uccise (diff) e al costo di tali unità
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
