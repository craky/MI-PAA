package cz.cvut.fit.krakovoj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	public int populationSize = 130;
	public double crossoverProbability = 0.7;
	public double mutationProbability = 0.028;
	public int maxGeneration = 600;
	public int maxGenerationWithoutImprovement = maxGeneration / 4;
	public int tournamentCapacity = 16;
	public int sharedFitnessThreshold = 1;
	
	private List<Individual> population = new ArrayList<Individual>();
	private List<Individual> newPopulation = new ArrayList<Individual>();
	
	private int bestFit = 0;
	private int worstFit = 0;
	
	private int generationCount = 0;
	private int withoutChange = 0;
	private Random rand = new Random();
	private Formula formula = new Formula();
	
	public GeneticAlgorithm(String fileName){
		population.clear();
		try {
			formula.readFromFile(fileName);
		} catch (IOException e) {
			System.err.println("Error: GeneticAlgorithm constructor.");
			e.printStackTrace();
		}
		
		for(int i = 0; i < populationSize; i++){
			population.add(new Individual(formula.getSumOfLiterals()));
		}		
		
		for(Individual individual : population){
			individual.randomChromosome();
		}
				
	}
	
	public int getPopulationSize(){
		return population.size();
	}
	
	public Individual tournament(int capacity) throws Exception{
		Individual individual;
		
		List<Individual> tournament = new ArrayList<Individual>();
		List<Individual> copyOfPopul = new ArrayList<Individual>();
		
		for(int i = 0; i < population.size();i++){
			copyOfPopul.add(population.get(i).clone());
		}
		
		if(capacity > population.size()){
			System.err.println("TournamentSelection capacity size problem detected.");
		} else {
			for(int i = 0; i < capacity; i ++){
				tournament.add(copyOfPopul.remove(rand.nextInt(copyOfPopul.size())));
			}
		}
		
		individual = removeBestOne(tournament);
		return individual;
	}
	
	public Individual removeBestOne(List<Individual> list) throws Exception{
		Individual best = list.get(0);
		
		for(Individual individual: list){
			best = best.getFitness(formula) > individual.getFitness(formula) ? best : individual;
		}
		//list.remove(best);
		
		return best.clone(); 
	}
	
	/**
	 * Takes all elements from source to target
	 * @param source list of individuals
	 * @param target list of individuals
	 */
	public void moveList(List<Individual> source,List<Individual> target){
		int original_source_size = source.size();
		for(int i = 0; i < original_source_size;i++){
			target.add(source.remove(0));
		}
		
		source.clear();
	}
	
	/**
	 * Do one-point crossover if probability allows it. 
	 * After that mutate.
	 * @throws Exception 
	 */
	public void crossoverAndMutate() throws Exception{
		Individual parent_1 = tournament(tournamentCapacity);
		Individual parent_2 = tournament(tournamentCapacity);		
		
		//if(java.lang.Double.compare(rand.nextDouble(),crossoverProbability) <= 0){
			//parent_1.doCrossover(0,formula.getSumOfLiterals() / 2,parent_2);
			//parent_2.doCrossover(formula.getSumOfLiterals() / 2, formula.getSumOfLiterals(), parent_1);
		//}
		
		if(java.lang.Double.compare(rand.nextDouble(),parent_1.crossoverProbability(population.get(rand.nextInt(population.size())).getFitness(formula),
				bestFit, worstFit, formula)) <= 0)
			parent_1.doCrossover(0,formula.getSumOfLiterals() / 2,parent_2);
		if(java.lang.Double.compare(rand.nextDouble(),parent_2.crossoverProbability(population.get(rand.nextInt(population.size())).getFitness(formula),
				bestFit, worstFit, formula)) <= 0)
			parent_2.doCrossover(formula.getSumOfLiterals() / 2, formula.getSumOfLiterals(), parent_1);
		
		parent_1.mutate(mutationProbability);
		parent_2.mutate(mutationProbability);
		newPopulation.add(parent_1.clone());
		newPopulation.add(parent_2.clone());
	}
	
	public int run(int threshold) throws Exception{
		maxGeneration = threshold;
		generationCount = 0;
		int bestFitness = population.get(0).getFitness(formula), tmpBestFit = population.get(0).getFitness(formula);
		while (withoutChange < maxGenerationWithoutImprovement && generationCount < maxGeneration){	
			bestFit = getBestFitness();
			worstFit = getWorstFitness();
			for(int i = 0; i < populationSize; i+=2){
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
			generationCount++;
			//System.out.println("Gen: " + generationCount + " fit: " + bestFitness);
		}
		//System.out.println("Best fitness " + bestFitness + " wSum = " + getBestIndividual().getWeightedSum(formula));
		withoutChange = 0;
		return getBestIndividual().getWeightedSum(formula);
	}
	
	/**
	 * Counts best fitness from all individuals
	 * @return best fitness
	 * @throws Exception 
	 */
	public int getBestFitness() throws Exception{
		int result = population.get(0).getFitness(formula);
		for(Individual individual : population){
			result = individual.getFitness(formula) > result ? individual.getFitness(formula) : result;
		}
		
		return result;
	}
	
	public int getWorstFitness() throws Exception{
		int result = population.get(0).getFitness(formula);
		for(Individual individual : population){
			result = individual.getFitness(formula) < result ? individual.getFitness(formula) : result;
		}
		
		return result;
	}
	
	public Individual getBestIndividual() throws Exception{
		Individual result = population.get(0);
		for(Individual individual : population){
			if (individual.getFitness(formula) > result.getFitness(formula)){
				result = individual;
			}
		}
		
		return result.clone();
	}
}
