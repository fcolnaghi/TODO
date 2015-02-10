package br.com.todo.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.dao.TodoDAO;
import br.com.todo.model.Todo;

@Path("/todo")
public class TodoResource {
	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Todo> list(@QueryParam("profile") String profile) {
		try{
			if(profile == null) {
				return TodoDAO.getAllTodo();
			} else {
				return TodoDAO.getTodoByEmail(profile);
			}
		}catch(Exception e){
			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build());
		}
		
	}
}
