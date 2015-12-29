package cz.cvut.fit.krakovoj;

import java.util.Random;

/**
 * Member of population of the Genetic Algorithm
 * @author krakovoj@fit.cvut.cz
 * @version 1.0
 * @since 2015-12-27
 *
 */
public class MemberGA {
	private Integer[] chromosome;
	
	public MemberGA(int chrom_size){
		chromosome = new Integer[chrom_size];
	}
	
	/**
	 * This method is only for tests.
	 * @return length of chromosome
	 */
	public int getChromosomeSize(){
		return chromosome.length;
	}
	
	/**
	 * Will generate random chromosome.
	 */
	public void randomChromosome(){
		Random rand = new Random();
		
		for(int i = 0; i < chromosome.length;i++){
			chromosome[i] = rand.nextInt() % 2 ;
		}
	}
	
	public int getChromoElem(int i){
		return chromosome[i];
	}
	
	/**
	 * 
	 * @param knapsack full of items costs and weights
	 * @return total_cost or 0 if capacity is lower than total_weight
	 */
	public int fitness(Knapsack knapsack){
		int total_weight = 0, total_cost = 0;
		
		//System.out.println("    --chromosome.length = " + chromosome.length);
		for(int i = 0; i < chromosome.length; i++){
			if(chromosome[i] == 1){
				total_weight += knapsack.getItemWeight(i);
				total_cost += knapsack.getItemCost(i);
			}
		}
		
		//System.out.println("    --fitness = " + total_cost + " is it true? = " + (total_weight < knapsack.getCapacity()));
		if(total_weight < knapsack.getCapacity()){
			return total_cost;
		}
		
		return 0;
		
	}
	
	public void doCrossover(int from, int to, MemberGA source){
		for(int i = from; i < to; i++){
			chromosome[i] = source.getChromoElem(i);
		}
	}
	
	public void mutate(double mutate_probability){
		Random rand = new Random();
		
		for(int i = 0; i < chromosome.length;i++){
			if(java.lang.Double.compare(rand.nextDouble(),mutate_probability) <= 0){
				if(chromosome[i] == 1){
					chromosome[i] = 0;
				} else {
					chromosome[i] = 1;
				}
			}
		}
	}

}
