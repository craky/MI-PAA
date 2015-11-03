package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
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
		InputStream fis = new FileInputStream("./data/knap_15.inst.dat");
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
		knapsackProblemBruteForceRec(knapsack, item + 1, tempSolution, remainingCost - knapsack.getItemCost(item));
		solution.increaseCost(knapsack.getItemCost(item));
		solution.increaseWeight(knapsack.getItemWeight(item));
		knapsackProblemBruteForceRec(knapsack, item + 1, solution, remainingCost - knapsack.getItemCost(item));
	}

}
