package cz.cvut.fit.krakovoj;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
	private int id;
	private int size;
	private List<KnapsackItem> items = new ArrayList<KnapsackItem>();
	private KnapsackItem solution;
	
	public Knapsack(){
		this.size = 0;
	}
	
	public void addItem(KnapsackItem item){
		this.items.add(item);
		this.size++;
	}
	
	public void addItem(int cost, int weight){
		this.items.add(new KnapsackItem(cost,weight));
		this.size++;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int itemSum) {
		this.size = itemSum;
	}

	public KnapsackItem getSolution() {
		return solution;
	}
	public void setSolution(KnapsackItem solution) {
		this.solution = solution;
	}
}
