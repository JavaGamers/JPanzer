package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import model.GraphMap;

public class ShortestPaths {
	
	
	private static long[] InitializeSingleSource(GraphMap gm, Nodo n){
		int lenght = gm.getList().length;
		long [] distance = new long[lenght];
		for(int i=0;i<lenght;i++){
			distance[i]= Integer.MAX_VALUE;
		}
		distance[n.getId()]=0;
		return distance;
	}
	
	private static void relax(Nodo u, Nodo v, long costo, long[] distance){
		if(distance[v.getId()]>distance[u.getId()]+costo){
			distance[v.getId()]=distance[u.getId()]+costo;
		}
	}
	
	public static List<Nodo> dijkstra(GraphMap gm, Nodo source){
		long[] distance = InitializeSingleSource(gm,source);
		int lenght = gm.getList().length;
		int[] color = new int[lenght]; // 0-white	1-gray	
		
		for(int i=0;i<lenght;i++){
			color[i]=0;
		}
		
		List<Nodo> shortestPathTree = new LinkedList<Nodo>();
		PriorityQueue<Nodo> q = new PriorityQueue<Nodo>();
		color[source.getId()]=1;
		q.add(source);
		
		
		while(!q.isEmpty()){
			Nodo u = q.poll();
			//System.out.println("ho pescato il nodo "+u.getId());
			u.setCosto(distance[u.getId()]);
			shortestPathTree.add(u);
			Iterator<Nodo> it = gm.getList()[u.getId()].iterator();
			
			while(it.hasNext()){
				Nodo v = it.next();
				relax(u,v,gm.getList()[u.getId()].getFirst().getCosto(),distance);
				if(color[v.getId()]==0){
					color[v.getId()]=1;
					q.add(v);
					System.out.println("ho aggiunto il nodo "+v.getId());

				}
			}
		}
		
		return shortestPathTree;
	}

}
