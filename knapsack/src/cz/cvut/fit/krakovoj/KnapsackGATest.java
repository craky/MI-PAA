package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class KnapsackGATest {

	
	@Test
	public void testLists(){
		KnapsackGA problem = new KnapsackGA();
		List<MemberGA> source = new ArrayList<MemberGA>();
		List<MemberGA> target = new ArrayList<MemberGA>();
		
		assertEquals(source.size(),0);
		assertEquals(target.size(),0);
		
		for(int i = 0; i < 200; i++){
			source.add(new MemberGA(35));
		}
		assertEquals(source.size(),200);
		problem.moveList(source, target);
		assertEquals(source.size(),0);
		assertEquals(target.size(),200);
	}

}
