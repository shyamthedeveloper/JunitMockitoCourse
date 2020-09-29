package com.in28minutes.mockitcourse;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.isEmptyString;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HamcrestLibraryTest {

	@Test
	public void test() {
		List<Integer> scores =  Arrays.asList(99,100,104,105);
		
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(105, 100));
		assertThat(scores, everyItem(greaterThan(90)));
		assertThat(scores, everyItem(lessThan(110)));
		assertThat("", isEmptyString());
		
		//check all methods like this from org.hamcrest.Matchers class
	}

}
