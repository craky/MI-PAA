/**
 * 
 */
package cz.cvut.fit.krakovoj;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * @author krakovoj@fit.cvut.cz
 *
 */
public class Clause {
	private List<Integer> literals = new ArrayList<Integer>();
	
	public void addLiteral(int literal){
		//literal can not be a zero
		if(literal == 0){
			System.err.println("Warning: Literal can not be 0 and is '" + literal + "'. Literal" +
					" not added.");
			return;
		}
		literals.add(literal);
	}
	
	public int getSize(){
		return literals.size();
	}
	
	public int getLiteral(int i){
		return literals.get(i);
	}
	
	/**
	 * Takes evaulation and returns if clause is true or false
	 * @param evaulation individual chromosome
	 * @return Clasue evaulation
	 */
	public boolean eval(List<Integer> evaulation){
		int val = 0, lit_abs;
		
		for(int i = 0; i < literals.size(); i ++){
			lit_abs = Math.abs(literals.get(i));
			
			if(((literals.get(i) > 0) && (evaulation.get(lit_abs-1) == 1)) || ((literals.get(i) < 0) && (evaulation.get(lit_abs-1) == 0))){				
				val++;			
			}
				
		}
		if(val > 0){
			return true;
		}		
		return false;
	}
	
	public Clause clone(){
		Clause c = new Clause();
		for(Integer l: literals){
			c.addLiteral(l);
		}
		
		return c;
	}
	
	public void print(){
		System.out.print("Clause is {");
		for(Integer l: literals){
			System.out.print(l+", ");
		}
		System.out.print("}");
	}
}
