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
	public static final int POPULATION_SIZE = 350;
	public static final double CROSSOVER_PROBABILITY = 0.7;
	public static final double MUTATION_PROBABILITY = 1.0;
	public static final int MAX_GENERATION = 5000;
	public static final int MAX_GENERATIONS_WITHOUT_IMPROVEMENT = 5000;
	public static final int TOURNAMENT_CAPACITY = 70;
	
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
			//For each line do
			initialization();
			run();
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
		System.out.println("\"Generace\"  \"P(Mutace) " + MUTATION_PROBABILITY + "\"");
		while (withoutChange < MAX_GENERATIONS_WITHOUT_IMPROVEMENT && generationCount < MAX_GENERATION){		
			for(int i = 0; i < POPULATION_SIZE; i+=2){
				crossoverAndMutate();
			}
			population.clear();
			moveList(newPopulation,population);
			
			
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
			if(generationCount % 10 == 0 ){
				System.out.println(generationCount + " " + bestFitness);
			}
			generationCount++;
		}
		//System.out.println( knapsack.getId() + " " + bestFitness);
		withoutChange = 0;
	}
	
	/**
	 * Randomly choose 'selectCapacity' individuals and select the one with better fitness.
	 * @param selectCapacity
	 * @return individual from chosen list with best fitness
	 */
	public MemberGA tournamentSelection(int selectCapacity){
		MemberGA individual;
		List<MemberGA> tournament = new ArrayList<MemberGA>();
		List<MemberGA> copyOfPopul = new ArrayList<MemberGA>();
		
		for(int i = 0; i < population.size();i++){
			copyOfPopul.add(population.get(i).clone());
		}
		
		if(selectCapacity > population.size()){
			System.err.println("TournamentSelection capacity size problem detected.");
		} else {
			for(int i = 0; i < selectCapacity; i ++){
				//tournament.add(population.remove(rand.nextInt(population.size())));
				tournament.add(copyOfPopul.remove(rand.nextInt(copyOfPopul.size())));
			}
		}
		
		individual = removeBestOne(tournament);
		//newPopulation.add(individual);
		//moveList(tournament,population);
		//tournament.clear();
		
		return individual;
	}
	
	public MemberGA removeBestOne(List<MemberGA> list){
		MemberGA best = list.get(0);
		
		for(MemberGA individual: list){
			best = best.fitness(knapsack) > individual.fitness(knapsack) ? best : individual;
		}
		//list.remove(best);
		
		return best.clone(); 
	}
	
	/**
	 * Do one-point crossover if probability allows it. 
	 * After that mutate.
	 */
	public void crossoverAndMutate(){
		MemberGA parent_1 = tournamentSelection(TOURNAMENT_CAPACITY);
		MemberGA parent_2 = tournamentSelection(TOURNAMENT_CAPACITY);		
		
		if(java.lang.Double.compare(rand.nextDouble(),CROSSOVER_PROBABILITY) <= 0){
			parent_1.doCrossover(0,knapsack.getSize() / 2,parent_2);
			parent_2.doCrossover(knapsack.getSize() / 2, knapsack.getSize(), parent_1);
		}
		
		parent_1.mutate(MUTATION_PROBABILITY);
		parent_2.mutate(MUTATION_PROBABILITY);
		newPopulation.add(parent_1.clone());
		newPopulation.add(parent_2.clone());
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
	
	/**
	 * Takes all elements from source to target
	 * @param source list of individuals
	 * @param target list of individuals
	 */
	public void moveList(List<MemberGA> source,List<MemberGA> target){
		int original_source_size = source.size();
		for(int i = 0; i < original_source_size;i++){
			target.add(source.remove(0));
		}
		
		source.clear();
	}
}
