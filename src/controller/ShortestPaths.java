package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import model.GraphMap;

public class ShortestPaths {

	public static List<Nodo> dijkstra(GraphMap gm, Nodo source){
		int lenght = gm.getList().length;
		int[] color = new int[lenght]; // 0-white	1-gray
		
		for(int i=0;i<lenght;i++){
			color[i]=0;
		}
		source.setDistance(0);
		PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
		List<Nodo> shortestPathTree = new LinkedList<Nodo>();
		q.add(source);
		color[source.getId()]=1;
		
		while(!q.isEmpty()){
			Nodo u = q.poll();
			shortestPathTree.add(u);
			color[u.getId()]=1;
			Iterator<Nodo> it = gm.getList()[u.getId()].iterator();
			
			while(it.hasNext()){
				Nodo v = it.next();
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
		return shortestPathTree;
	}
}