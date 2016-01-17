package cz.cvut.fit.krakovoj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	public static int populationSize = 20;
	public static double crossoverProbability = 0.7;
	public static double mutationProbability = 0.05;
	public static int maxGeneration = 5000;
	public static int maxGenerationWithoutImprovement = maxGeneration / 4;
	public static int tournamentCapacity = 5;
	
	private List<Individual> population = new ArrayList<Individual>();
	private List<Individual> newPopulation = new ArrayList<Individual>();
	
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
}
