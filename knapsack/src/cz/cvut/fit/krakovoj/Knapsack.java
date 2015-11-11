package cz.cvut.fit.krakovoj;

import java.util.ArrayList;
import java.util.List;

public class Knapsack {
	private int id;
	private int size;
	private int capacity;
	private List<KnapsackItem> items = new ArrayList<KnapsackItem>();
	private KnapsackItem solution;
	
	public Knapsack(){
		this.size = 0;
		solution = new KnapsackItem();
	}
	
	public void addItem(KnapsackItem item){
		this.items.add(item);
		this.size++;
	}
	
	public void addItem(int cost, int weight){
		this.items.add(new KnapsackItem(cost,weight));
		this.size++;
	}
	
	public int getItemCost(int idx){
		return items.get(idx).getCost();
	}
	
	public int getItemWeight(int idx){
		return items.get(idx).getWeight();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int itemSum) {
		this.size = itemSum;
	}

	public int getSolutionCost() {
		return solution.getCost();
	}
	
	public int getSolutionWeight() {
		return solution.getWeight();
	}
	
	public int getTotalItemsCost(){
		int tmp = 0;
		for (int i = 0; i < this.size;i++){
			tmp += this.getItemCost(i);
		}
		
		return tmp;
	}
	
	public void setSolutionCost(int cost) {
		solution.setCost(cost);
	}
	
	public void setSolutionWeight(int weight) {
		solution.setWeight(weight);
	}
	
	public void fillKnapsack(String[] line){
		this.id = Integer.parseInt(line[0]);
		this.capacity = Integer.parseInt(line[2]);
		
		for(int i = 3; i < line.length; i += 2){
			addItem(Integer.parseInt(line[i+1]),Integer.parseInt(line[i]));
		}
	}
	
	
	public void clear(){
		this.id = 0;
		this.size = 0;
		this.capacity = 0;
		this.items.clear();
		this.solution.clear();
	}
	
	public void bubbleSort(){
		for (int i = 0; i < this.size -1; i++){
			for (int j = 0; j < this.size - i - 1; j++){
				if(Float.compare(items.get(j).heuristic(),items.get(j+1).heuristic()) < 0){
					KnapsackItem tmp = items.get(j);
					items.set(j, items.get(j+1));
					items.set(j+1,tmp);
				}
			}
		}
	}
	
	public void print(){
		for (int i = 0; i < this.size; i++){
			System.out.println("c: "+ items.get(i).getCost() + " w:" + items.get(i).getWeight() + " h:" + items.get(i).heuristic() );
		}
	}
	
	public void fillCapacity(){
		int tmpCost = 0, tmpWeight = 0;
		for(int i = 0; i < this.size;i++){
			if(this.capacity < (tmpWeight + items.get(i).getWeight())){
				continue;
			}
			tmpCost += items.get(i).getCost();
			tmpWeight += items.get(i).getWeight();
		}
		
		this.solution.setCost(tmpCost);
		this.solution.setWeight(tmpWeight);
	}
	
	public int getShift(Double eps){
		return -1;
	}
	
	public int getItemsMaxCost(){
		int tmp = Integer.MIN_VALUE;
		for(KnapsackItem it : items)
			tmp = Math.max(tmp, it.getCost());
		
		return tmp;
	}
	
	public static double logb(int base, int num){
		return Math.log(num) / Math.log(base);
	}
}
