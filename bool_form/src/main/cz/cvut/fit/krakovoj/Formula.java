/**
 * 
 */
package cz.cvut.fit.krakovoj;

import java.util.ArrayList;
import java.util.List;

/**
 * @author krakovoj@fit.cvut.cz
 *
 */
public class Formula {
	private List<Integer> weights = new ArrayList<Integer>();
	private List<Clause> clauses = new ArrayList<Clause>();
	
	/**
	 * Only way to know how many literals is in the Formula
	 * is the sum of weights, there have to be weight for
	 * every literal.
	 * @return sum of literals
	 */
	public int getSumOfLiterals(){
		return weights.size();
	}
	
	public int getSumOfClauses(){
		return clauses.size();
	}
	
	public void addWeights(List<Integer> w){
		weights.addAll(w);
	}
	
	public int getWeight(int i){
		return weights.get(i);
	}
	
	public void addClause(List<Integer> c){
		Clause cl = new Clause();
		for(int i = 0; i < c.size();i++){
			cl.addLiteral(i);
		}
		clauses.add(cl.clone());
	}
}
