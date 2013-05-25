package model;

import java.util.Observable;

// gli esagoni sono comparabili in base al costo del loro territorio
public class Esagono implements Comparable<Esagono> {
	private int id;		// numerdo d'ordine all'interno della lista
	private int[] coordinate; // settore - livello - posizione
	private Esagono[] adiacenze; // contiene le adiacenze: i lati degli esagoni sono numerati a partire da quello in alto da 0 a 5 in senso orario
	private Unità unit; // unità presente sull'esagono
	private Territorio territorio;
	private long minDistance;
	
	public Esagono(int[] c){
		this.territorio=null;
		this.unit=null;
		this.coordinate = new int[3];
		this.adiacenze = new Esagono[6];
		this.minDistance = Integer.MAX_VALUE;
		
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
		this.minDistance = Integer.MAX_VALUE;
		
		// algoritmo che dato id fornisce le coordinate
		this.coordinate[1]=0;
		if(id==0){
			this.coordinate[0]=0;
			this.coordinate[2]=0;
		} else{
			int i=0;
				while(id>=startLiv(i)){
					i++;
				}
				this.coordinate[1]=i-1;
		
				this.coordinate[2]=0;
				this.coordinate[0]=0;
		
				if(this.coordinate[1]==1){
					this.coordinate[0]=id;
		
				} else{
					
					int k=id-startLiv(this.coordinate[1]);
					this.coordinate[2]= Esagono.mod(k, this.coordinate[1]);
		
					if(k==0){
						this.coordinate[0]=1;
					} else{
						if(this.coordinate[2]==0)
							this.coordinate[0]= k/this.coordinate[1]+1;
						else 
							this.coordinate[0]=sup(k/this.coordinate[1]);
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
	
	public int getCosto(){
		if(this.getTerritorio()!=null){
			return this.getTerritorio().getCosto();
		}
		else{
			return 0;
		}
	}
	
	public long getMinDistance(){
		return this.minDistance;
	}
	
	public void setDistance(long distance){
		if(distance<0){
			throw new IllegalArgumentException("non possono esistere pesi negativi");
		}
		this.minDistance=distance;
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
		u.setPos(this);
	}
	
	public void setTerritorio(Territorio t){
		this.territorio=t;
	}
	
	public static int[] getCoord(int id){
		int[] coordinate = new int[3];
		coordinate[1]=0;
		if(id==0){
			coordinate[0]=0;
			coordinate[2]=0;
		} else{
			int i=0;
				while(id>=startLiv(i)){
					i++;
				}
				coordinate[1]=i-1;
		
				coordinate[2]=0;
				coordinate[0]=0;
		
				if(coordinate[1]==1){
					coordinate[0]=id;
		
				} else{
					
					int k=id-startLiv(coordinate[1]);
					coordinate[2]= Esagono.mod(k,coordinate[1]);
		
					if(k==0){
						coordinate[0]=1;
					} else{
						if(coordinate[2]==0)
							coordinate[0]= k/coordinate[1]+1;
						else {
							 coordinate[0]=sup(k/coordinate[1]);
						}
					}
		
				}
		}
		return coordinate;
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
	
	public String toString(){
		String s="";
		for(int i=0; i<6;i++){
			if(this.getAdiacenze()[i]!=null)
				s+=this.getAdiacenze()[i].getId()+" - " + i + " || ";
		}
		return "Esagono numero "+this.id+" di coordinate: settore "+this.coordinate[0]+" livello "
	    +this.coordinate[1]+" posizione "+this.coordinate[2]+ " con adiacenze "+s;
	}
	
	public String saveToString(){
		
		String s="";
		s+=this.id;
		s+="-";
		
		if(this.getTerritorio()!=null){
			s+=this.getTerritorio().getNome();
			s+="-";
		}
		else{
			s+=" ";
			s+="-";
		}
		
		if(this.unit!=null){
			s+=this.unit.getPlayer();
			s+="-";
			s+=this.unit.getNome();
			s+="-";
			s+=this.unit.getNumUnits();
			s+="-";
			s+=this.unit.getEsp();
			
		}
		else{
			s+=" ";
			s+="-";
			s+=" ";
			s+="-";
			s+=" ";
			s+="-";
			s+=" ";
		}

		return s;
	}
	
	private static int sup(double d){
		int s=0;
		
		if(d<0||d>6){
			throw new IllegalArgumentException("Use double value between 0 and 6");
		}
		if(d<1)
			s=1;
		else if(d<2)
			s=2;
		else if(d<3)
			s=3;
		else if(d<4)
			s=4;
		else if(d<5)
			s=5;
		else if(d<6)
			s=6;
		return s;
	}

	public int compareTo(Esagono o) {
		return Long.compare(this.minDistance,o.minDistance);
	}

}

