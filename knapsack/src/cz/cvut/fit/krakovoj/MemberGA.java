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

}
