package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import model.Esagono;
import model.GraphMap;
import model.Mappa;

public class Dijkstra {
	
	public static List<Esagono> shortestPath(Mappa m, Esagono source, int limit){
		int lenght = m.getComponent().length;
		int[] color = new int[lenght]; // 0-white	1-gray
		
		for(int i=0;i<lenght;i++){
			color[i]=0;
		}
		source.setDistance(0);
		PriorityQueue<Esagono> q = new PriorityQueue<Esagono>();
		List<Esagono> shortestPathTree = new LinkedList<Esagono>();
		q.add(source);
		color[source.getId()]=1;
		boolean finito= false;
		
		while(!q.isEmpty()&& !finito){
			Esagono u = q.poll();
			if(u.getMinDistance()>limit){
				finito = true;
			}
			else{
				shortestPathTree.add(u);
				color[u.getId()]=1;
				
				for(int i=0;i<6;i++){
					Esagono v = u.getAdiacenze()[i];
					if(v!=null){
						int weight = v.getCosto();
						long distanceToU = u.getMinDistance()+weight;
						if(distanceToU<v.getMinDistance()){
							q.remove(v);
							if(color[v.getId()]==0){
								v.setDistance(distanceToU);
								q.add(v);
								color[v.getId()]=1;
							}
						}
					}
				}
			}
		}
		return shortestPathTree;
	}
}
