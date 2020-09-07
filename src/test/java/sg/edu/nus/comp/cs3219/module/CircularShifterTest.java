package sg.edu.nus.comp.cs3219.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs3219.model.LineStorage;

public class CircularShifterTest {
	LineStorage inputLineStorage;
	LineStorage afterShiftLineStorage;
	CircularShifter shifter;

	@Before
	public void setUp() {
		inputLineStorage = new LineStorage();
		afterShiftLineStorage = new LineStorage();
		shifter = new CircularShifter(afterShiftLineStorage);
		Set<String> words = new HashSet<>();
		words.add("the");
		words.add("after");
		shifter.setIgnoreWords(words);
		inputLineStorage.addObserver(shifter);
	}

	@Test
	public void test() {
		inputLineStorage.addLine("The Day after Tomorrow");
		assertEquals(2, afterShiftLineStorage.size());

		assertEquals("Day after Tomorrow the", afterShiftLineStorage.get(0).toString());
		assertEquals("Tomorrow the Day after", afterShiftLineStorage.get(1).toString());
	}

	public void test1() {
		inputLineStorage.addLine("Day Tomorrow Happy Grief");
		assertEquals(2, afterShiftLineStorage.size());

		for(int i=0; i<afterShiftLineStorage.size(); i++) {
			String sent = afterShiftLineStorage.get(1).toString();
			String words[] = sent.split(" ");
			for(String s : shifter.getIgnoreWords()) {
				assertFalse(Arrays.asList(words).contains(s));
			}
		}
	}

//	public void test2() {
//		inputLineStorage.addLine("The After After The");
//		assertEquals(0, afterShiftLineStorage.size());
//
//		for(int i=0; i<afterShiftLineStorage.size(); i++) {
//			String sent = afterShiftLineStorage.get(1).toString();
//			String words[] = sent.split(" ");
//			for(String s : shifter.getIgnoreWords()) {
//				assertFalse(Arrays.asList(words).contains(s));
//			}
//		}
//
//	}

}
