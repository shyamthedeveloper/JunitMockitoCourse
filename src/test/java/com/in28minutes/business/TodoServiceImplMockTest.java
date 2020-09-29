package com.in28minutes.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.in28minutes.data.api.TodoService;

public class TodoServiceImplMockTest {

	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockito() {
		
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		
		when(todoService.retrieveTodos("Dummy")).thenReturn(mockList);
		
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		assertEquals(2,retrieveTodos.size());
	}
	
	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockito2() {
		
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList();
		
		when(todoService.retrieveTodos("Dummy")).thenReturn(mockList);
		
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		assertEquals(0,retrieveTodos.size());
	}
	
	
	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingMockitWithBDD() {
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		
		//When
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		//Then
		assertThat(retrieveTodos.size(),is(2));
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingMockitWithBDD() {
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		
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
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList("Learn Spring MVC", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		
		//When
		todoServiceImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		then(todoService).should().deleteTodo("Learn dance");
		then(todoService).should(times(1)).deleteTodo("Learn dance");
		then(todoService).should(never()).deleteTodo("Learn Spring MVC");
	
	}
	
	@Test
	public void testDeleteTodosRelatedToSpring_usingMockitWithBDD_ArgumentCapture() {
		
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		
		//Given
		TodoService todoService = mock(TodoService.class);
		List<String> mockList = Arrays.asList("Learn cricket", "Learn Spring boot",  "Learn dance");
		given(todoService.retrieveTodos("Dummy")).willReturn(mockList);
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoService);
		
		//When
		todoServiceImpl.deleteTodosNotRelatedToSpring("Dummy");
		
		//Then
		then(todoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());	
		assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
	}

}
