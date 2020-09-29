package com.in28minutes.mockitcourse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;

public class ListMockTest {

	@Test
	public void testList_SizeMethod() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2);
		
		assertEquals(2, mockList.size());
	}
	
	@Test
	public void testList_SizeMethodMultipleValues() {
		List mockList = mock(List.class);
		when(mockList.size()).thenReturn(2).thenReturn(6);
		
		assertEquals(2, mockList.size());
		assertEquals(6, mockList.size());
	}
	
	@Test
	public void testList_getMethod() {
		List mockList = mock(List.class);
		when(mockList.get(0)).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", mockList.get(0));
	}
	
	@Test
	public void testList_getMethodWithAny() {
		List mockList = mock(List.class);
		when(mockList.get(anyInt())).thenReturn("in28Minutes");
		
		assertEquals("in28Minutes", mockList.get(0));
	}
	
	@Test
	public void testList_getMethodWithBDD() {
		
		//Given
		List mockList = mock(List.class);
		given(mockList.get(anyInt())).willReturn("in28Minutes");
		
		//When
		String actualValue = mockList.get(0).toString();
		
		//Then
		assertThat(actualValue, is("in28Minutes"));
	}
	
	@Test(expected=RuntimeException.class)
	public void testList_getMethodException() {
		List mockList = mock(List.class);
		when(mockList.get(1)).thenThrow(new RuntimeException("Exception"));
		
		mockList.get(1);
	}
	

}
