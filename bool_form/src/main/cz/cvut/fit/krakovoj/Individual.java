/**
 * 
 */
package cz.cvut.fit.krakovoj;

import java.util.Random;

/**
 * @author krakovoj@fit.cvut.cz
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public class Individual {
	private Integer[] chromosome;
	
	public Individual(int length){
		if(length < 0){
			System.err.println("Warning: Individual size should be equal or more than 0." +
					"Required lenth '" + length + "' is changed to 0.");
			length = 0;
		}
		chromosome = new Integer[length];
	}
	
	public Individual(Individual inputIndividual){
		chromosome = new Integer[inputIndividual.getChromosomeLength()];
		for(int i = 0; i < chromosome.length; i++){
			try {
				chromosome[i] = inputIndividual.getChromosomeElem(i);
			} catch (Exception e) {
				System.err.println("Chromosome was fulfilled prematurely:");
				e.printStackTrace();
				return;
			}
		}
	}
	
	public int getChromosomeElem(int position) throws Exception{
		if(position >= chromosome.length){
			throw new IndexOutOfBoundsException("Position is bigger than chromosome length.");
		}
		return chromosome[position];
	}
	
	public void setRandomChromosome(){
		Random rand = new Random();
		
		if(chromosome.length < 1){
			System.err.println("Warning: Chromosome length is " + chromosome.length +" so it" +
					" can not be randomised.");
			return;
		}
		
		for(int i = 0; i < chromosome.length; i++){		
			chromosome[i] = rand.nextInt(Integer.MAX_VALUE) % 2;	
		}
	}
	
	public int getChromosomeLength(){
		return chromosome.length;
	}
	
	public Individual clone(){
		return new Individual(this);
	}
}
