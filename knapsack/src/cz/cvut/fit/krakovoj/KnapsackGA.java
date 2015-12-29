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
	public static final int POPULATION_SIZE = 500;
	public static final double CROSSOVER_PROBABILITY = 0.7;
	public static final double MUTATION_PROBABILITY = 0.1;
	public static final int MAX_GENERATION = 300;
	public static final int MAX_GENERATIONS_WITHOUT_IMPROVEMENT = 250;
	
	private List<MemberGA> population = new ArrayList<MemberGA>();
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
		
	/**
	 * Will make a population of individuals.
	 * Each individual has assigned a random 
	 * chromosome.
	 */
	public void initialization(){
		for(int i = 0; i < POPULATION_SIZE; i++){
			population.add(new MemberGA(knapsack.getSize()));
		}
		
		for(MemberGA individual : population){
			individual.randomChromosome();
		}
	}
	
	public void run(){
		generationCount = 0;
		int bestFitness = population.get(0).fitness(knapsack), tmpBestFit = population.get(0).fitness(knapsack);
		
		while (withoutChange < MAX_GENERATIONS_WITHOUT_IMPROVEMENT && generationCount < MAX_GENERATION){
			
			for(int i = 0; i < population.size(); i+=2){
				crossoverAndMutate();
			}
			bestFitness = getBestFitness();
			if(bestFitness == tmpBestFit){
				withoutChange++;
			}else{
				withoutChange = 0;
			}
			tmpBestFit = bestFitness;
			generationCount++;
		}
		System.out.println("Starting Line no. " + knapsack.getId() + ". Best Fitness is " + bestFitness);
		withoutChange = 0;
	}
	
	/**
	 * Randomly choose two individuals a select the one with better fitness.
	 * @return individual with better fitness
	 */
	public MemberGA tournamentSelection(){
		MemberGA parent_1 = population.get(rand.nextInt(population.size()));
		MemberGA parent_2 = population.get(rand.nextInt(population.size()));
		
		return parent_1.fitness(knapsack) >= parent_2.fitness(knapsack) ? parent_1:parent_2;
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
