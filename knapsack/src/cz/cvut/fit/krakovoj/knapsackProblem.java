package cz.cvut.fit.krakovoj;

public class knapsackProblem {
	
	public static void main(String [] args){
		System.out.println("Hello knapsack");
		knapsackProblemBruteForce();
	}
	
	public static void knapsackProblemBruteForce(){
		Knapsack knapsack = new Knapsack();
		KnapsackItem solution = new KnapsackItem();
		knapsack.addItem(114, 18);
		knapsack.addItem(136,42);
		knapsack.addItem(192,88);
		knapsack.addItem(223,3);
		solution.setItemMap(knapsack.getSize());
		
		knapsackProblemBruteForceRec(knapsack,0,solution);
	}
	
	public static void knapsackProblemBruteForceRec(Knapsack knapsack, int item, KnapsackItem solution){
		if (item >= knapsack.getSize()){
			System.out.print("*****Sol.cost = " + solution.getCost() + " -- Sol.weight = " + solution.getWeight() + " ");
			for(int i = 0; i < knapsack.getSize();i++){
				System.out.print(solution.getItem(i) + "");
			}
			System.out.println();
			return;
		}
		knapsackProblemBruteForceRec(knapsack,item+1,solution);
		solution.setItem(item);
		solution.increaseCost(knapsack.getItemCost(item));
		solution.increaseWeight(knapsack.getItemWeight(item));
		knapsackProblemBruteForceRec(knapsack,item+1,solution);
	}

}
