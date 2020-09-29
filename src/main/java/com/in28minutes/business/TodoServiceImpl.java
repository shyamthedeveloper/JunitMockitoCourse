package com.in28minutes.business;

import java.util.ArrayList;
import java.util.List;

import com.in28minutes.data.api.TodoService;

public class TodoServiceImpl{
	
	private TodoService todoService;
	
	public TodoServiceImpl(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	public List<String> retrieveAllTodosRelatedToSpring(String user) {
		List<String> filteredTodos  = new ArrayList<>();
		List<String> todos = todoService.retrieveTodos(user);
		
		for(String todo : todos) {
			if(todo.contains("Spring")) {
				filteredTodos.add(todo);
			}
		}
		
		return filteredTodos;
	}
	
	public void deleteTodosNotRelatedToSpring(String user) {
		List<String> todos = todoService.retrieveTodos(user);
		
		for(String todo : todos) {
			if(!todo.contains("Spring")) {
				todoService.deleteTodo(todo);
			}
		}
	}

}


