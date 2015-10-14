package cz.cvut.fit.krakovoj;

public class Knapsack {
	private int id;
	private int itemSum;
	private KnapsackItem[] items;
	private KnapsackItem solution;
	
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
	public KnapsackItem[] getItems() {
		return items;
	}
	public void setItems(KnapsackItem[] items) {
		this.items = items;
	}
	public KnapsackItem getSolution() {
		return solution;
	}
	public void setSolution(KnapsackItem solution) {
		this.solution = solution;
	}
}
