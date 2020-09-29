package com.in28minutes.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.api.TodoServiceStub;

public class TodoServiceImplTest {

	@Test
	public void testRetrieveAllTodosRelatedToSpring_usingStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoServiceImpl todoServiceImpl = new TodoServiceImpl(todoServiceStub);
		List<String> retrieveTodos = todoServiceImpl.retrieveAllTodosRelatedToSpring("Dummy");
		
		assertEquals(2,retrieveTodos.size());
	}

}
