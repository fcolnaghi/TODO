package br.com.dao;

import static br.com.todo.service.OfyService.ofy;

import java.util.List;

import br.com.todo.model.Profile;
import br.com.todo.model.Todo;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

public class TodoDAO {
	
	public static List<Todo> getAllTodo() {

		Query<Todo> query = ofy().load().type(Todo.class).order("title");

		return query.list();
	}

	public static List<Todo> getTodoByEmail(String email) {

		Query<Todo> query = ofy().load().type(Todo.class).ancestor(Key.create(Profile.class, email));

		return query.list();
	}
	
}
