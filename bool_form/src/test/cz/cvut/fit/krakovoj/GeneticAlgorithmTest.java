package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticAlgorithmTest {

	@Test
	public void test() throws Exception {		
		int sum = 0, result = 0;
		int mutation = 1;
		//assertEquals(g.getPopulationSize(),20);
		for(int j = 1; j < 101; j++){
			for(int i = 0; i < 10; i++){
				GeneticAlgorithm g = new GeneticAlgorithm("/home/craa/Dropbox/ČVUT/Magistr/3_semestr/mi-paa/5_ukol/120_150_20.dat");
				sum += g.run(mutation);				
			}
			result = sum/10;
			System.out.println( mutation + " " + result + " " + (double) (((39.0-(double)result)/39.0)*100.0) );
			mutation += 30;
			sum = 0;
		}
		
		
	}

}
