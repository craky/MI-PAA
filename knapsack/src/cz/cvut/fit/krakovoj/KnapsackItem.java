package cz.cvut.fit.krakovoj;

public class KnapsackItem {
	private int cost;
	private int weight;
	private int[] itemMap;
	
	public KnapsackItem(int cost, int weight) {
		this.cost = cost;
		this.weight = weight;
	}
	
	public KnapsackItem(){
		this.cost = 0;
		this.weight = 0;
	}
	
	public void setItemMap(int size){
		if (this.itemMap != null)
			return;
		this.itemMap = new int[size];
	}
	
	public void setItem(int idx){
		this.itemMap[idx] = 1;
	}
	
	public void unsetItem(int idx){
		this.itemMap[idx] = 0;
	}
	
	public int getItem(int idx){
		return this.itemMap[idx];
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
	
	public void increaseCost(int newCost){
		this.cost += newCost;
	}
	
	public void increaseWeight(int newWeight){
		this.weight += newWeight;
	}
}
