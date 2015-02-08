package br.com.todo;

import static br.com.todo.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.todo.model.Todo;

@SuppressWarnings("serial")
public class TODOServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Todo> todo = ofy().load().type(Todo.class).list();
		
		System.out.println(todo);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		/*
		 * verificar se existe um email no banco já se existir mostra uma lista com as tarefas
		 * se não existir deve ser criado uma nova entrada para ser adicionada as listas
		 */
		
		//request.setAttribute("", arg1);.
	}
	
	
}
