package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



/**
 * The Genetic algorithm for solution the knapsack problem.
 * 
 * @author krakovoj@fit.cvut.cz
 * @version 1.0
 * @since 2015-12-27
 *
 */
public class KnapsackGA {
	public static final int POPULATION_SIZE = 100;
	public static final double CROSSOVER_PROBABILITY = 0.6;
	public static final double MUTATION_PROBABILITY = 0.6;
	public static final int MAX_GENERATION = 5000;
	public static final int MAX_GENERATIONS_WITHOUT_IMPROVEMENT = 10000;
	
	private List<MemberGA> population = new ArrayList<MemberGA>();
	private List<MemberGA> newPopulation = new ArrayList<MemberGA>();
	private Knapsack knapsack = new Knapsack();
	private int generationCount = 0;
	private int withoutChange = 0;
	private Random rand = new Random();
	
	public KnapsackGA(){

	}
	
	public void solve(String inputFile) throws IOException{
		String line;
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		while ((line = br.readLine()) != null) {
			knapsack.fillKnapsack(line.split(" "));
			for (int i = 0; i < 1; i++) {
				//For each line do
				initialization();
				run();
			}
			knapsack.clear();
		}
		
		br.close();
	}
	
	public void solveOneLine(String inputFile) throws IOException{
		InputStream fis = new FileInputStream(inputFile);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		knapsack.fillKnapsack(br.readLine().split(" "));			
		initialization();
		run();
		knapsack.clear();		
		br.close();
	}
		
	/**
	 * Will make a population of individuals.
	 * Each individual has assigned a random 
	 * chromosome.
	 */
	public void initialization(){
		population.clear();
		for(int i = 0; i < POPULATION_SIZE; i++){
			population.add(new MemberGA(knapsack.getSize()));
		}
		
		if(population.size() != POPULATION_SIZE){
			System.err.println("Error Population size shoud be" +
					POPULATION_SIZE + " but is " + population.size() );
		}
		
		for(MemberGA individual : population){
			individual.randomChromosome();
		}
	}
	
	public void run(){
		generationCount = 0;
		int bestFitness = population.get(0).fitness(knapsack), tmpBestFit = population.get(0).fitness(knapsack);
		//System.out.println("\"P(Krizeni) = " + CROSSOVER_PROBABILITY + "\"");
		while (withoutChange < MAX_GENERATIONS_WITHOUT_IMPROVEMENT && generationCount < MAX_GENERATION){		
			for(int i = 0; i < POPULATION_SIZE; i+=2){
				crossoverAndMutate();
			}
			setPopulationBack();
			
			if(!newPopulation.isEmpty()){
				System.err.println("Warning: Set Population back not works. NewPop is " + newPopulation.size()
						+ " and should be 0");
			}
			if (bestFitness < getBestFitness()){
				bestFitness = getBestFitness();
			}
			if(bestFitness == tmpBestFit){
				withoutChange++;
			}else{
				withoutChange = 0;
			}
			tmpBestFit = bestFitness;
			//if(generationCount % 10 == 0 ){
				//System.out.println(generationCount + " " + bestFitness);
			//}
			generationCount++;
		}
		System.out.println( knapsack.getId() + " " + bestFitness);
		withoutChange = 0;
	}
	
	/**
	 * Randomly choose two individuals a select the one with better fitness.
	 * @return individual with better fitness
	 */
	public MemberGA tournamentSelection(){
		int index_1 = rand.nextInt(population.size());
		/* Only one individual left in original population*/
		if(population.size() == 1){
			MemberGA onlyIndividual = population.remove(index_1); 
			newPopulation.add(onlyIndividual);
			return onlyIndividual;
		}
		int index_2 = rand.nextInt(population.size()-1);
				
		MemberGA parent_1 = population.remove(index_1);
		MemberGA parent_2 = population.remove(index_2);
		
		if(parent_1.fitness(knapsack) >= parent_2.fitness(knapsack)){			
			population.add(parent_2);					
			newPopulation.add(parent_1);
			return parent_1;
		} else {			
			population.add(parent_1);
			newPopulation.add(parent_2);
			return parent_2;
		}
	}
	
	/**
	 * After tournament is important to set up population
	 * */
	public void setPopulationBack(){
		if(population.size() != 0){
			System.err.println("Warning population.size in setPopulationBack" +
					"is not 0. It is " + population.size());
		}
		for(int i = 0; i < POPULATION_SIZE; i ++){
			// Removing always the first element of the list
			population.add(newPopulation.remove(0));
		}
		
		newPopulation.clear();
		
		if(population.size() != POPULATION_SIZE){
			System.err.println("Warning population.size in setPopulationBack should be " + POPULATION_SIZE 
					+ " but is " + population.size());
		}
	}
	
	/**
	 * Do one-point crossover if probability allows it. 
	 * After that mutate.
	 */
	public void crossoverAndMutate(){
		MemberGA parent_1 = tournamentSelection();
		MemberGA parent_2 = tournamentSelection();
		
		if(java.lang.Double.compare(rand.nextDouble(),CROSSOVER_PROBABILITY) <= 0){
			parent_1.doCrossover(0,knapsack.getSize() / 2,parent_2);
			parent_2.doCrossover(knapsack.getSize() / 2, knapsack.getSize(), parent_1);
		}
		
		parent_1.mutate(MUTATION_PROBABILITY);
		parent_2.mutate(MUTATION_PROBABILITY);
	}
	
	/**
	 * Counts best fitness from all individuals
	 * @return best fitness
	 */
	public int getBestFitness(){
		int result = population.get(0).fitness(knapsack);
		for(MemberGA individual : population){
			result = individual.fitness(knapsack) > result ? individual.fitness(knapsack) : result;
		}
		
		return result;
	}		
}
