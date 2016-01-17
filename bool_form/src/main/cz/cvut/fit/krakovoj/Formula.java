/**
 * 
 */
package cz.cvut.fit.krakovoj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
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
			cl.addLiteral(c.get(i));
		}
		clauses.add(cl.clone());
	}
	
	public void readFromFile(String fileName) throws IOException{
		String line;
		InputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis,
				Charset.forName("UTF-8"));
		BufferedReader br = new BufferedReader(isr);
		
		//First line are sums of literals & formulas
		line = br.readLine();
		//read line with weights
		line = br.readLine();
		parseWeights(line);
		while ((line = br.readLine()) != null) {
			parseClauses(line);
		}
		br.close();
	}
	
	public void parseWeights(String line){
		int weight, i = 0;
		
		while((weight = Integer.parseInt(line.split(" ")[i++])) != 0){
			weights.add(weight);
		}			
	}
	
	public void parseClauses(String line){
		int literal,i = 0;
		Clause c = new Clause();
		
		while((literal = Integer.parseInt(line.split(" ")[i++])) != 0){
			c.addLiteral(literal);
		}
		
		clauses.add(c.clone());
	}
}
