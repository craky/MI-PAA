package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class knapsackProblem {

	public static void main(String[] args) {
		try {
			//knapsackHeuristic();
			knapsackProblemBruteForce();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void knapsackHeuristic() throws IOException {
		String line;
		InputStream fis = new FileInputStream("./data/knap_40.inst.dat");
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		long startTime, estimatedTime = 0, totalTime = 0;
		
		while ((line = br.readLine()) != null) {
			knapsack.fillKnapsack(line.split(" "));
			for (int i = 0; i < 100000; i++) {
				startTime = System.currentTimeMillis();
				knapsack.bubbleSort();
				knapsack.fillCapacity();
				estimatedTime += System.currentTimeMillis() - startTime;
			}
			estimatedTime /=100000;
			System.out.println(knapsack.getId() +"," + estimatedTime +"," + knapsack.getSolutionCost());
			totalTime += estimatedTime;
			estimatedTime = 0;
			knapsack.clear();
		}
		System.out.println("Total avarage time is " + (totalTime/50));
		br.close();
	}

	public static void knapsackProblemBruteForce() throws IOException {
		String line;
		InputStream fis = new FileInputStream("./data/knap_25.inst.dat");
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		KnapsackItem solution = new KnapsackItem();
		long startTime, estimatedTime = 0, totalTime = 0;

		while ((line = br.readLine()) != null) {
			knapsack.fillKnapsack(line.split(" "));
			for (int i = 0; i <5; i++) {
				startTime = System.currentTimeMillis();
				knapsackProblemBruteForceRec(knapsack, 0, solution, knapsack.getTotalItemsCost());
				estimatedTime += System.currentTimeMillis() - startTime;
			}
			estimatedTime /=5;
			System.out.println(knapsack.getId() +"," + estimatedTime +"," + knapsack.getSolutionCost());
			totalTime += estimatedTime;
			estimatedTime = 0;
			knapsack.clear();
			solution.clear();
		}
		System.out.println("Total avarage time is " + (totalTime/50));
		br.close();
	}

	public static void knapsackProblemBruteForceRec(Knapsack knapsack,
			int item, KnapsackItem solution, int remainingCost) {
		KnapsackItem tempSolution = new KnapsackItem(solution.getCost(),
				solution.getWeight());
		if (item >= knapsack.getSize()) {
			if (solution.getCost() > knapsack.getSolutionCost()
					&& solution.getWeight() <= knapsack.getCapacity()) {
				knapsack.setSolutionCost(solution.getCost());
				knapsack.setSolutionWeight(solution.getWeight());
			}
			return;
		}
		if((tempSolution.getCost() + (remainingCost - knapsack.getItemCost(item))) > knapsack.getSolutionCost())
			knapsackProblemBruteForceRec(knapsack, item + 1, tempSolution, remainingCost - knapsack.getItemCost(item));
		solution.increaseCost(knapsack.getItemCost(item));
		solution.increaseWeight(knapsack.getItemWeight(item));
		if((solution.getCost() + (remainingCost - knapsack.getItemCost(item))) > knapsack.getSolutionCost())
			knapsackProblemBruteForceRec(knapsack, item + 1, solution, remainingCost - knapsack.getItemCost(item));
	}
	
	public static void knapsackDynamic(String inputFile) throws IOException{
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		
		while ((line = br.readLine()) != null) {
			knapsack.fillKnapsack(line.split(" "));
			//There will be method for dynamic coding
		}
	}
	
	/**
	 * filled array
	 * @param x_size number of columns
	 * @param y_size number of rows
	 * @return array[x_size, y_size] where all values are filled with 0
	 */
	public static int[][] filledArr(int x_size, int y_size){
		if(x_size < 0 || y_size < 0)
			return null;
		int[][] ret = new int[x_size][y_size];
		for(int i = 0; i < x_size; i++){
			for(int j = 0; j < y_size; j++){
				ret[i][j] = 0;
			}
		}
		return ret;
	}

}
