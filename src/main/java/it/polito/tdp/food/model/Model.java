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
	FoodDao dao;
	
	SimpleWeightedGraph <String, DefaultWeightedEdge> grafo;
	List <String> migliore= new ArrayList<>();
	double pesoMax=0;
	public Model() {
		dao= new FoodDao();
		
	}
	
	public void creaGrafo(Double calorie) {
		grafo= new SimpleWeightedGraph <String, DefaultWeightedEdge> (DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici(calorie));
		
		for(Adiacenza a: dao.getAdiacenza(calorie)) {
			if(grafo.containsVertex(a.getTipo1())&&grafo.containsVertex(a.getTipo2()))
				Graphs.addEdge(this.grafo, a.getTipo1(), a.getTipo2(), a.getPeso());
		}
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public List<Connesso> getConnessi(String selezionato){
		List <Connesso> connessi=new ArrayList<>();
		for(String tipo: Graphs.neighborListOf(this.grafo, selezionato)) {
			String t=tipo;
			double peso=grafo.getEdgeWeight(grafo.getEdge(selezionato, t));
			Connesso c= new Connesso(t, peso);
			connessi.add(c);
		}
		return connessi;
	}
	
	public List <String> trovaPercorso(String partenza, int NPassi){
		this.migliore=new ArrayList<>();
		List <String> parziale= new ArrayList<String>();
		parziale.add(partenza);
		this.pesoMax=Integer.MIN_VALUE;
		cerca(parziale,NPassi, 0);
		return migliore;
	}
	
	
	
	private void cerca(List<String> parziale, int nPassi, double peso) {
		//Caso terminale
		if(parziale.size()==nPassi+1) {
			if(this.migliore.size()==0) {
				this.pesoMax=peso;
				this.migliore= new ArrayList <>(parziale);
				return;
			}
			if(peso>pesoMax) {
				pesoMax=peso;
				this.migliore= new ArrayList<>(parziale);
				return;
			}
			return;
		}
		String tipoUltimo=parziale.get(parziale.size()-1);
		for(DefaultWeightedEdge e: this.grafo.edgesOf(tipoUltimo)) {
			String prova=Graphs.getOppositeVertex(grafo, e, tipoUltimo);
			double pesoProva= grafo.getEdgeWeight(e);
			if(!parziale.contains(prova)) {
				parziale.add(prova);
				peso=peso+pesoProva;
				cerca(parziale,nPassi, peso);
				parziale.remove(prova);
				peso=peso-pesoProva;
			}
		}
		
	}

	public FoodDao getDao() {
		return dao;
	}

	
	public SimpleWeightedGraph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(SimpleWeightedGraph<String, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}
	
	
}
