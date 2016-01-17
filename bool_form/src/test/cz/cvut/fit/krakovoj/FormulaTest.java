package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FormulaTest {

	@Test
	public void test() {
		Formula f = new Formula();
		List<Integer> weights = new ArrayList<Integer>();
		List<Integer> clause_1 = new ArrayList<Integer>();
		List<Integer> clause_2 = new ArrayList<Integer>();
		List<Integer> clause_3 = new ArrayList<Integer>();
		weights.add(2);
		weights.add(4);
		weights.add(1);
		weights.add(6);
		clause_1.add(1);
		clause_1.add(-3);
		clause_1.add(4);
		clause_2.add(-1);
		clause_2.add(2);
		clause_2.add(-3);
		clause_3.add(3);
		clause_3.add(4);
		
		assertEquals(f.getSumOfLiterals(),0);
		assertEquals(f.getSumOfClauses(),0);
		f.addWeights(weights);
		assertEquals(f.getSumOfLiterals(),4);
		f.addClause(clause_1);
		f.addClause(clause_2);
		f.addClause(clause_3);
		assertEquals(f.getSumOfClauses(),3);
		assertEquals(f.getWeight(1),4);
		//Test hard copy
		weights.set(1, 8);
		assertEquals(f.getWeight(1),4);
		
		
		//test Big weights
		List<Integer> weights_2 = new ArrayList<Integer>();
		for(int i = 1; i < 21; i++){
			weights_2.add(i);
		}
		assertEquals(weights_2.size(),20);
		weights_2.clear();
	}
	
	@Test
	public void testParsers(){
		Formula f = new Formula();
		f.parseWeights("2 4 1 6 0");
		
		assertEquals(f.getSumOfLiterals(),4);
		assertEquals(f.getWeight(0),2);
		assertEquals(f.getWeight(1),4);
		assertEquals(f.getWeight(2),1);
		assertEquals(f.getWeight(3),6);
		
		for(int i = 0; i < 65321; i++){
			f.parseClauses("1 578 -6000 0");
		}
		assertFalse(f.getSumOfClauses() == 65320);
		assertEquals(f.getSumOfClauses(),65321);
	}
	
	@Test
	public void readFromFile() throws IOException{
		Formula f = new Formula();
		f.readFromFile("src/test/cz/cvut/fit/krakovoj/testData/example.dat");
		assertEquals(f.getSumOfLiterals(),4);
		assertEquals(f.getSumOfClauses(),6);
	}

}
