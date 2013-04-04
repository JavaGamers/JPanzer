package model;

public class Esagono {
	private int id;		// numerdo d'ordine all'interno della lista
	private int[] coordinate; // settore - livello - posizione
	private Esagono[] adiacenze; // contiene le adiacenze: i lati degli esagoni sono numerati a partire da quello in alto da 0 a 5 in senso orario
	private static int[] startNums = {1,7,19,37,61,91,127,169,217,271,331,397,469,547,631,721,817,919,1027,1141,1261}; // indici dei primi elementi di ogni livello
	private Unità unit; // unità presente sull'esagono
	private Territorio territorio;
	
	public Esagono(int[] c){
		this.territorio=null;
		this.unit=null;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		
		if(c.length!=3)
			throw new IllegalArgumentException("Invalid format, use settore - livello - posizione");
		
		for(int i=0; i<coordinate.length; i++){
			this.coordinate[i] = c[i];
		}
		
		// algoritmo che date le coordinate fornisce id
		if (c[1] == 0)
			this.id = 0;
		if (c[1] == 1)
			this.id = c[0];
		else
			this.id = (c[0]-1)*c[1] + c[2] + startLiv(c[1]);
	}	

	public Esagono(int id){
		this.territorio=null;
		this.unit=null;
		this.id = id;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		
		// algoritmo che dato id fornisce le coordinate
		this.coordinate[1]=0;
		if(id==0){
			this.coordinate[0]=0;
			this.coordinate[2]=0;
		} else{
				
				for(int i=0; i<startNums.length;i++){
					if(id<startNums[i]){
						this.coordinate[1]=i;
						break;	
					}
				}
		
				this.coordinate[2]=0;
				this.coordinate[0]=0;
		
				if(this.coordinate[1]==1){
					this.coordinate[0]=id;
		
				} else{
					
					int k=id-startNums[this.coordinate[1]-1];
					this.coordinate[2]= Esagono.mod(k, this.coordinate[1]);
		
					if(k==0){
						this.coordinate[0]=1;
					} else{
						if(this.coordinate[2]==0)
							this.coordinate[0]= k/this.coordinate[1]+1;
						else 
							this.coordinate[0]=(int)(Math.ceil(k/this.coordinate[1]));
					}
		
				}
		}
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
	
	public Unità getUnit(){
		return this.unit;
	}
	
	public Territorio getTerritorio(){
		return this.territorio;
	}
	
	// n è il lato dell'esagono "e" passato come parametro
	public void setAdiacenza(Esagono e, int n){
		if(e==null)
			throw new IllegalArgumentException("l'esagono pezzente è: "+this.id);
		if(n>5)
			throw new IllegalArgumentException("Invalid range. Use number from 0 to 5");
		
		this.adiacenze[n]= e;
		e.adiacenze[Esagono.mod(n+3, 6)]=this;
		
	}
	
	public void setUnit(Unità u){
		this.unit=u;
	}
	
	public void setTerritorio(Territorio t){
		this.territorio=t;
	}
	
	// metodo per calcolare n mod p
	public static int mod(int n, int base){
		if(n>=base)
			n= n%base;
		return n;
	}
	
	
	//metodo per calcolare il valore iniziale dato il livello
	public static int startLiv (int x){
			return (3*x*x - 3*x + 1);
		}
	
	//metodo per calcolare il valore finale dato il livello
	public static int endLiv (int x){
			return (3*x*x + 3*x) ;
		}
	
	public static int[] getStartNums(){
		return startNums;
	
	}
	public String toString(){
		String s="";
		for(int i=0; i<6;i++){
			if(this.getAdiacenze()[i]!=null)
				s+=this.getAdiacenze()[i].getId()+" - " + i + " || ";
		}
		return "Esagono numero "+this.id+" di coordinate: settore "+this.coordinate[0]+" livello "
	    +this.coordinate[1]+" posizione "+this.coordinate[2]+ " con adiacenze "+s;
	}

}

