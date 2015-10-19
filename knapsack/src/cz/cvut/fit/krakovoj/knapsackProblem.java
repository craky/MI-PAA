package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class knapsackProblem {
	
	public static void main(String [] args){		
		try {
			knapsackHeuristic();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void knapsackHeuristic() throws IOException{
		String line;
		InputStream fis = new FileInputStream("./data/knap_4.inst.dat");
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		
		while((line = br.readLine()) != null){
			knapsack.fillKnapsack(line.split(" "));			
			knapsack.bubbleSort();
			knapsack.fillCapacity();
			System.out.println("Best cost is " + knapsack.getSolutionCost() + " and weight is " + knapsack.getSolutionWeight());
			knapsack.clear();
		}
		
		br.close();
	}

	
	public static void knapsackProblemBruteForce() throws IOException{
		String line;
		InputStream fis = new FileInputStream("./data/knap_4.inst.dat");
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		KnapsackItem solution = new KnapsackItem();
		
		while((line = br.readLine()) != null){
			knapsack.fillKnapsack(line.split(" "));
			knapsackProblemBruteForceRec(knapsack,0,solution);
			System.out.println("Best cost is " + knapsack.getSolutionCost() + " and weight is " + knapsack.getSolutionWeight());
			knapsack.clear();
			solution.clear();
		}
		
		br.close();
	}
	
	
	public static void knapsackProblemBruteForceRec(Knapsack knapsack, int item, KnapsackItem solution){
		KnapsackItem tempSolution = new KnapsackItem(solution.getCost(),solution.getWeight());
		if (item >= knapsack.getSize()){
			if (solution.getCost() > knapsack.getSolutionCost() && solution.getWeight() <= knapsack.getCapacity()){
				knapsack.setSolutionCost(solution.getCost());
				knapsack.setSolutionWeight(solution.getWeight());
			}
			return;
		}
		knapsackProblemBruteForceRec(knapsack,item+1,tempSolution);
		solution.increaseCost(knapsack.getItemCost(item));
		solution.increaseWeight(knapsack.getItemWeight(item));
		knapsackProblemBruteForceRec(knapsack,item+1,solution);
	}

}
