package cz.cvut.fit.krakovoj;

public class knapsackProblem {
	
	public static void main(String [] args){
		knapsackProblemBruteForce();
	}
	
	public static void knapsackProblemBruteForce(){
		Knapsack knapsack = new Knapsack();
		KnapsackItem solution = new KnapsackItem();
		knapsack.addItem(114, 18);
		knapsack.addItem(136,42);
		knapsack.addItem(192,88);
		knapsack.addItem(223,3);
		
		knapsackProblemBruteForceRec(knapsack,0,solution);
	}
	
	public static void knapsackProblemBruteForceRec(Knapsack knapsack, int item, KnapsackItem solution){
		KnapsackItem tempSolution = new KnapsackItem(solution.getCost(),solution.getWeight());
		if (item >= knapsack.getSize()){
			System.out.println("*****Sol.cost = " + solution.getCost() + " -- Sol.weight = " + solution.getWeight() + " ");
			return;
		}
		knapsackProblemBruteForceRec(knapsack,item+1,tempSolution);
		solution.increaseCost(knapsack.getItemCost(item));
		solution.increaseWeight(knapsack.getItemWeight(item));
		knapsackProblemBruteForceRec(knapsack,item+1,solution);
	}

}
