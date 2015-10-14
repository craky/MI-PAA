package cz.cvut.fit.krakovoj;

public class KnapsackItem {
	private int cost;
	private int weight;
	
	public KnapsackItem(int cost, int weight) {
		this.cost = cost;
		this.weight = weight;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
