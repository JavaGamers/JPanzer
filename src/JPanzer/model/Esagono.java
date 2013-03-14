package JPanzer.model;

public class Esagono {
	int id;		// numerdo d'ordine all'interno della lista
	int[] coordinate; // settore - livello - posizione
	Esagono[] adiacenze; // contiene le adiacenze: i lati degli esagoni sono numerati a partire da quello in alto da 0 a 5 in senso orario
	
	public Esagono(int[] c){
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		// settare id
		
		if(c.length!=3)
			throw new IllegalArgumentException("Invalid format, use settore - livello - posizione");
		
		for(int i=0; i<coordinate.length; i++){
			this.coordinate[i] = c[i];
		}
	}	
		
	public Esagono(int id){
		this.id = id;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		//settare coordinate
	}
	
	public int getId(){
		return this.id;
	}
	
	public int[] getCoordinate(){
		return this.coordinate;
	}
	
	public int getSettore(){
		return this.coordinate[0];
	}
	
	public int getLivello(){
		return this.coordinate[1];
	}
	
	public int getPosizione(){
		return this.coordinate[2];
	}
	
	public Esagono[] getAdiacenze(){
		return this.adiacenze;
	}
	
	// n è il lato dell'esagono "e" passato come parametro
	public void setAdiacenza(Esagono e, int n){ 
		if(n>5)
			throw new IllegalArgumentException("Invalid range. Use number from 0 to 5");
		
		this.adiacenze[n]= e;
		if(e.getAdiacenze()[Esagono.mod(n+3, 6)]==null)
			e.setAdiacenza(this, Esagono.mod(n+3, 6));
		
	}
	
	// metodo per calcolare n mod p
	public static int mod(int n, int base){
		if(n>=base)
			n= n%base;
		return n;
	}
	
}

