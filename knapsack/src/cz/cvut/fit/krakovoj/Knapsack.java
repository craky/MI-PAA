package cz.cvut.fit.krakovoj;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
	private int id;
	private int itemSum;
	private List<KnapsackItem> items = new ArrayList<KnapsackItem>();
	private KnapsackItem solution;
	
	public Knapsack(){
		this.itemSum = 0;
	}
	
	public void addItem(KnapsackItem item){
		items.add(item);
	}
	
	public void addItem(int cost, int weight){
		items.add(new KnapsackItem(cost,weight));
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemSum() {
		return itemSum;
	}
	public void setItemSum(int itemSum) {
		this.itemSum = itemSum;
	}

	public KnapsackItem getSolution() {
		return solution;
	}
	public void setSolution(KnapsackItem solution) {
		this.solution = solution;
	}
}
