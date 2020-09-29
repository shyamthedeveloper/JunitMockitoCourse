package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.in28minutes.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplMockitoInjectMockTest {
	
	@Rule
	public MockitoRule mockitorule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoService;
	
	@InjectMocks
	TodoServiceImpl todoServiceImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;

	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockito() {
		
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		
		when(todoService.retrieveTodos("Dummy")).thenReturn(mockList);
		
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		assertEquals(2,retrieveTodos.size());
	}
	
	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockito2() {
		
		List<String> mockList = Arrays.asList();
		
		when(todoService.retrieveTodos("Dummy")).thenReturn(mockList);
		
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		assertEquals(0,retrieveTodos.size());
	}
	
	
	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockitWithBDD() {
		
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		
		//When
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		//Then
		assertThat(retrieveTodos.size(),is(2));
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingMockitWithBDD() {
		
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		
		//When
		todoServiceImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		verify(todoService,times(1)).deleteTodo("Learn dance");
		verify(todoService,atLeastOnce()).deleteTodo("Learn dance");
		//verify(todoService,atLeast(5)).deleteTodo("Learn dance");
		verify(todoService,never()).deleteTodo("Learn Spring boot");
		
	}
	@Test
	public void testDeleteTodosRelatedToSpring_usingMockitWithBDD_withThen() {
		
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		
		//When
		todoServiceImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		then(todoService).should().deleteTodo("Learn dance");
		then(todoService).should(times(1)).deleteTodo("Learn dance");
		then(todoService).should(never()).deleteTodo("Learn Spring MVC");
	
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingMockitWithBDD_ArgumentCapture() {

		List<String> mockList = Arrays.asList("Learn cricket", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		
		//When
		todoServiceImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());	
		assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
	}

}
