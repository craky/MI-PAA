package cz.cvut.fit.krakovoj;

public class KnapsackItem implements Comparable<KnapsackItem>{
	private int cost;
	private int weight;
	
	public KnapsackItem(int cost, int weight) {
		this.cost = cost;
		this.weight = weight;
	}
	
	public KnapsackItem(){
		this.cost = 0;
		this.weight = 0;
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
	
	public void clear(){
		this.weight = 0;
		this.cost = 0;
	}
	
	public float heuristic(){
		return ((float)this.cost)/((float)this.weight);
	}
	
	public int compareTo(KnapsackItem item){
		return Float.compare(heuristic(), item.heuristic());
	}
}
