package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	FoodDao dao;
	List <String> migliore;
	int pesoMax;
	
	public Model() {
		dao=new FoodDao();
	}
	
	public void creaGrafo(Integer cal) {
		grafo= new SimpleWeightedGraph <String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici(cal));
		for(Adiacenza a: dao.getAdiacenze(cal)) {
			if(grafo.containsVertex(a.getP1())&& grafo.containsVertex(a.getP2())) {
				Graphs.addEdge(this.grafo, a.getP1(), a.getP2(), a.getPeso());
			}
		}
	}
	
	
	public List <Connesso> connessi(String selezionata){
		List <Connesso> result= new ArrayList <>();
		for(String s: Graphs.neighborListOf(this.grafo, selezionata)) {
			Connesso c= new Connesso (s, grafo.getEdgeWeight(grafo.getEdge(selezionata, s)));
			result.add(c);
		}
		return result;
	}
	
	public List <String> trovaPercorso(Integer N, String selezionato){
		this.migliore= new ArrayList <>();
		List <String> parziale= new ArrayList<>();
		parziale.add(selezionato);
		this.pesoMax=0;
		cerca(parziale, N);
		return migliore;
	}
	
	
	private void cerca(List<String> parziale,  Integer N) {
		if(parziale.size()== N) {
			
			if(calcolaPeso(parziale)>pesoMax) {
				migliore=new ArrayList<String>(parziale);
				return;
				
				
			}
			
			return;
			
		}
		
		String ultimo=parziale.get(parziale.size()-1);
		for(String s: Graphs.neighborListOf(grafo, ultimo)) {
			if(!parziale.contains(s)) {
				parziale.add(s);
				cerca(parziale,  N);
				parziale.remove(s);
				
				
			}
		}
		
		
		
	}

	public int calcolaPeso(List<String> parziale) {
		int peso=0;
		for(int i=1; i<parziale.size(); i++) {
			String s1= parziale.get(i-1);
			String s2=parziale.get(i);
			peso=(int) (peso+grafo.getEdgeWeight(grafo.getEdge(s1, s2)));
		}
		return peso;
	}

	public int getNVertici() {
		return grafo.vertexSet().size();
	}
	public int getNArchi() {
		return grafo.edgeSet().size();
	}

	public SimpleWeightedGraph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	

	public FoodDao getDao() {
		return dao;
	}

	
	
	
	
}
