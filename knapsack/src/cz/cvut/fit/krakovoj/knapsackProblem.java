package cz.cvut.fit.krakovoj;

public class knapsackProblem {
	
	public static void main(String [] args){
		System.out.println("Hello knapsack");
		knapsackProblemBruteForce();
	}
	
	public static void knapsackProblemBruteForce(){
		Knapsack knapsack = new Knapsack();
		knapsack.addItem(114, 18);
		knapsack.addItem(136,42);
		knapsack.addItem(192,88);
		knapsack.addItem(223,3);
	}
	
	public static void knapsackProblemBruteForceRec(Knapsack knapsack, int item, KnapsackItem solution){
		if (item > knapsack.getSize()){
			return;
		}
		
		knapsackProblemBruteForceRec(knapsack,++item,solution);
		//TODO: add item to solution
		knapsackProblemBruteForceRec(knapsack,++item,solution);
	}

}
