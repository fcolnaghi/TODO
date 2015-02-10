package br.com.todo.rest;

import static br.com.todo.service.OfyService.ofy;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Todo todo) {
		
		System.out.println("entrou no post" + todo);
		
		ofy().save().entity(todo).now();
		
		
//		try{
//			if(todo != null) {
//				System.out.println(todo);
//			}
//		}catch(Exception e){
//			throw new WebApplicationException(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build());
//		}
		
	}
	
	
}
