package model;

import java.util.Observable;

public class Esagono extends Observable {
	private int id;		// numerdo d'ordine all'interno della lista
	private int[] coordinate; // settore - livello - posizione
	private Esagono[] adiacenze; // contiene le adiacenze: i lati degli esagoni sono numerati a partire da quello in alto da 0 a 5 in senso orario
	private Unit� unit; // unit� presente sull'esagono
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
					
					int k=id-startLiv(this.coordinate[1]-1);
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
	
	public Unit� getUnit(){
		return this.unit;
	}
	
	public Territorio getTerritorio(){
		return this.territorio;
	}
	
	// n � il lato dell'esagono "e" passato come parametro
	public void setAdiacenza(Esagono e, int n){
		if(e==null)
			throw new IllegalArgumentException("l'esagono pezzente �: "+this.id);
		if(n>5)
			throw new IllegalArgumentException("Invalid range. Use number from 0 to 5");
		
		this.adiacenze[n]= e;
		e.adiacenze[Esagono.mod(n+3, 6)]=this;
		
	}
	
	public void setUnit(Unit� u){
		this.unit=u;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setTerritorio(Territorio t){
		this.territorio=t;
		this.setChanged();
		this.notifyObservers();
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
		}

		return s;
	}

}

