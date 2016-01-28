/**
 * 
 */
package cz.cvut.fit.krakovoj;

import java.io.IOException;

/**
 * @author craa
 *
 */
public class BruteForce {
	private Individual bestSolution;
	private int sumOfResolving = 0;
	private Formula formula = new Formula();
	
	BruteForce(String fileName){
		try {
			formula.readFromFile(fileName);
		} catch (IOException e) {
			System.err.println("Error: GeneticAlgorithm constructor.");
			e.printStackTrace();
		}
		
		bestSolution = new Individual(formula.getSumOfLiterals());
	}
	
	public void solveBruteForce(Individual state, int size){
		if(size == 0){
			return;
		}
		
		size--;
		
		state.setChromosomeElem(size, 1);
		if(formula.eval(state.getChromo()))
			sumOfResolving++;
		if((bestSolution.getWeightedSum(formula) < state.getWeightedSum(formula)) && formula.eval(state.getChromo()) ){
			bestSolution = state.clone();
		}
		solveBruteForce(state,size);
		
		state.setChromosomeElem(size, 0);
		if(formula.eval(state.getChromo()))
			sumOfResolving++;
		if((bestSolution.getWeightedSum(formula) < state.getWeightedSum(formula)) && formula.eval(state.getChromo())){
			bestSolution = state.clone();
		}
		solveBruteForce(state,size);
	}
	
	public int bestSolution(){
		System.out.println("Best solution is " + bestSolution.getWeightedSum(formula) + "Sum of resolving " + sumOfResolving);
		return bestSolution.getWeightedSum(formula);
	}
	
	public int chromoSize(){
		return formula.getSumOfLiterals();
	}
}
