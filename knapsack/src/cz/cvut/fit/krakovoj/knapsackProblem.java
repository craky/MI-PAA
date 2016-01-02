package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class knapsackProblem {
	private static List<String> files = fill_files();
	private static List<Integer> optimal = new ArrayList<Integer>();
	/**
	 * Number of visited states. It is used for measuring. Only B&B
	 * and Dynamic programming
	 */
	private static int states = 0;
	
	public static void main(String[] args) {
		try {
			KnapsackGA solution = new KnapsackGA();
			//solution.solve("./data/knap_40.inst.dat");
			solution.solveOneLine("./data/knap_40.inst.dat");
			//knapsackDynamic("./data/k+/gen_k+d_2_0.dat");
			//knapsackHeuristic("./data/k+/gen_k+d_2_0.dat");
			/*for(String st : files){
				//knapsackHeuristic();
				//knapsackProblemBruteForce(st);
				//knapsackDynamic(st);
				fptasKnapsack(st,0.001);
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<String> fill_files(){
		List<String> files = new ArrayList<String>();
		files.add("./data/knap_4.inst.dat");
		files.add("./data/knap_10.inst.dat");
		files.add("./data/knap_15.inst.dat");
		files.add("./data/knap_20.inst.dat");
		files.add("./data/knap_22.inst.dat");
		files.add("./data/knap_25.inst.dat");
		files.add("./data/knap_27.inst.dat");
		files.add("./data/knap_30.inst.dat");
		files.add("./data/knap_32.inst.dat");
		files.add("./data/knap_35.inst.dat");
		files.add("./data/knap_37.inst.dat");
		files.add("./data/knap_40.inst.dat");
		files.add("./data/knap_50.dat");
		files.add("./data/knap_75.dat");
		files.add("./data/knap_100.dat");
		files.add("./data/knap_150.dat");
		files.add("./data/knap_200.dat");
		files.add("./data/knap_500.dat");
		return files;
	}
	
	public static List<Integer> optimal_sol(String inputFile) throws IOException{
		List<Integer> opt = new ArrayList<Integer>();
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		while ((line = br.readLine()) != null) {
			opt.add(Integer.parseInt(line.split(" ")[2]));
		}
		br.close();
		return opt;
	}

	public static void knapsackHeuristic(String inputFile) throws IOException {
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		long startTime, estimatedTime = 0, totalTime = 0;
		int numOfLines = 0;
		double err = 0.0;
		
		while ((line = br.readLine()) != null) {
			numOfLines++;
			knapsack.fillKnapsack(line.split(" "));
			for (int i = 0; i < 1; i++) {
				startTime = System.currentTimeMillis();
				knapsack.bubbleSort();
				knapsack.fillCapacity();
				estimatedTime += System.currentTimeMillis() - startTime;
			}
			estimatedTime /=1;
			err += (double) (optimal.get(numOfLines-1)- knapsack.getSolutionCost())/optimal.get(numOfLines-1);
			System.out.println(err);
			totalTime += estimatedTime; 
			estimatedTime = 0;
			knapsack.clear();
		}
		System.out.println("nl is " + (numOfLines));
		System.out.println("Total avarage is " + (err/(numOfLines)) * 100 + "%");
		br.close();
	}

	public static void knapsackProblemBruteForce(String file) throws IOException {
		String line;
		InputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		KnapsackItem solution = new KnapsackItem();
		long startTime, estimatedTime = 0, totalTime = 0, totalStates = 0;
		

		while ((line = br.readLine()) != null) {
			states = 0;
			knapsack.fillKnapsack(line.split(" "));
			for (int i = 0; i <5; i++) {
				startTime = System.nanoTime();
				knapsackProblemBruteForceRec(knapsack, 0, solution, knapsack.getTotalItemsCost());
				estimatedTime += System.nanoTime() - startTime;
			}
			estimatedTime /=5;
			states /= 5;
			//System.out.println("Sum of visited states is " + states);
			//System.out.println(knapsack.getSolutionCost());
			optimal.add(knapsack.getSolutionCost());
			totalTime += estimatedTime;
			totalStates += states;
			estimatedTime = 0;
			knapsack.clear();
			solution.clear();
		}
		System.out.println("AVG states: " + (totalStates/20));
		System.out.println(file + " " + (totalTime/50));
		br.close();
	}

	public static void knapsackProblemBruteForceRec(Knapsack knapsack,
			int item, KnapsackItem solution, int remainingCost) {
		KnapsackItem tempSolution = new KnapsackItem(solution.getCost(),
				solution.getWeight());
		if (item >= knapsack.getSize()) {
			states++;
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
	
	public static void knapsackDynamic(String inputFile) throws Exception{
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		long startTime, totalTime = 0, lines = 0;
		
		states = 0;
		while ((line = br.readLine()) != null) {
			lines++;
			knapsack.fillKnapsack(line.split(" "));
			startTime = System.currentTimeMillis();
			//dynamicProgramming(knapsack);
			Dynamic_2(knapsack);
			totalTime += System.currentTimeMillis() - startTime;
			knapsack.clear();
		}
		System.out.println("States " + ((states/lines)));
		System.out.println(inputFile + " " + ((totalTime/lines)));
		br.close();
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
	
	/**
	 * 
	 * @param knapsack
	 */
	public static void dynamicProgramming(Knapsack knapsack){
		int[][] results = filledArr(knapsack.getSize()+1, knapsack.getCapacity()+1);
		states = 0;
		for(int i = 1; i < knapsack.getSize()+1; i++){
			for(int j = 0; j < knapsack.getCapacity()+1; j++){
				states++;
				if(knapsack.getItemWeight(i-1) <= j)
					results[i][j] = Math.max(results[i-1][j], results[i-1][j-knapsack.getItemWeight(i-1)] + knapsack.getItemCost(i-1));
				else
					results[i][j] = results[i-1][j];
			}	
		}
		
		knapsack.setSolutionCost(results[knapsack.getSize()][knapsack.getCapacity()]);		
	}
	
	
	public static void Dynamic_2(Knapsack knapsack) throws Exception{
		//int[][] results = filledArr(knapsack.getSize()+1, Knapsack.MAX_COST);
		int[][] results = new int[knapsack.getSize()+1][knapsack.getTotalItemsCost()+1];
		int total_cost = knapsack.getTotalItemsCost();
		
		if(total_cost >= Knapsack.MAX_COST)
			throw new Exception("Array is going to be out of bound: " + total_cost + " vs " + Knapsack.MAX_COST);
		
		for(int i = 1; i < results[0].length; i++)
			results[0][i] = Integer.MAX_VALUE/2;
		states = 0;
		for(int i = 1; i <= knapsack.getSize(); i++){
			for(int j = 0; j <= total_cost; j++){
				states++;
				if(knapsack.getItemCost(i-1) <= j){
					results[i][j] = Math.min(knapsack.getItemWeight(i-1)+results[i-1][j-knapsack.getItemCost(i-1)], results[i-1][j]);
				} else {
					results[i][j] = results[i-1][j];
				}
			}
		}
		
		for(int i = total_cost; i > 0; i-- ){
			if(results[knapsack.getSize()][i] <= knapsack.getCapacity()){
				knapsack.setSolutionCost(i);
				break;
			}
		}
	}
	
	
	public static void fptasKnapsack(String inputFile, double eps) throws Exception{
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		Knapsack knapsack = new Knapsack();
		long startTime, totalTime = 0, elapsedTime = 0;
		int lines = 0;
		double max = 0.0, sol = 0.0;
		List<Integer> opt = optimal_sol("/home/craa/Stažené/knap_10.sol.dat");
		
		while ((line = br.readLine()) != null) {
			lines++;
			knapsack.fillKnapsack(line.split(" "));
			for(int i = 0; i < 1; i++){
				startTime = System.currentTimeMillis();
				fptasDynamic(knapsack,eps);
				elapsedTime += System.currentTimeMillis() - startTime;
				System.out.println("*****");
				System.out.println("opt: " + opt.get(lines-1));
				sol = (double)(opt.get(lines-1) - knapsack.getSolutionCost())/opt.get(lines-1);
				System.out.println("sol: " + knapsack.getSolutionCost());
				System.out.println(sol);
				if(0 > Double.compare(max, sol))
					max = sol;
			}
			System.out.println(max + " !!!!!!");
			elapsedTime /= 1;
			totalTime += elapsedTime;
			elapsedTime = 0;
			knapsack.clear();
		}
		System.out.println(inputFile + " " + ((totalTime/lines)));
		br.close();
	}
	
	public static void fptasDynamic(Knapsack knapsack, double eps) throws Exception{
		//int[][] results = filledArr(knapsack.getSize()+1, Knapsack.MAX_COST);
		int[][] results = new int[knapsack.getSize()+1][knapsack.getTotalItemsCost()+1];
		int total_cost = knapsack.getTotalItemsCost();
		int b = knapsack.getShift(eps);
		//int b = 1;
		System.out.println("bits shift: " + b);
		knapsack.shiftItemsCost(b);
		
		if(total_cost >= Knapsack.MAX_COST)
			throw new Exception("Array is going to be out of bound: " + total_cost + " vs " + Knapsack.MAX_COST);
		
		for(int i = 1; i < results[0].length; i++)
			results[0][i] = Integer.MAX_VALUE/2;
		
		for(int i = 1; i <= knapsack.getSize(); i++){
			for(int j = 0; j <= total_cost; j++){
				if(knapsack.getItemCost(i-1) <= j){
					results[i][j] = Math.min(knapsack.getItemWeight(i-1)+results[i-1][j-knapsack.getItemCost(i-1)], results[i-1][j]);
				} else {
					results[i][j] = results[i-1][j];
				}
			}
		}
		
		for(int i = total_cost; i > 0; i-- ){
			if(results[knapsack.getSize()][i] <= knapsack.getCapacity()){
				knapsack.setSolutionCost(i);
				knapsack.setSolutionCost(knapsack.getSolutionCost() << b);
				break;
			}
		}
		
		knapsack.shiftBackItemsCost(b);		
	}

}
