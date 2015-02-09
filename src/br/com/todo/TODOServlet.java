package br.com.todo;

import static br.com.todo.service.OfyService.factory;
import static br.com.todo.service.OfyService.ofy;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.todo.model.Profile;
import br.com.todo.model.Todo;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@SuppressWarnings("serial")
public class TODOServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String profile = req.getParameter("profile");
		
		req.setAttribute("listaTodo", getTodoByEmail(profile));
	
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/todo.jsp").forward(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
		String email = request.getParameter("profile");
		
		// Allocate Id first, in order to make the transaction idempotent.
        Key<Profile> profileKey = Key.create(Profile.class, email);
        final Key<Todo> todoKey = factory().allocateId(profileKey, Todo.class);
        final long todoId = todoKey.getId();
            
        //Popula os dados do TODOs
        Todo todo = new Todo();
        
        todo.setId(todoId);
        todo.setProfileKey(profileKey);
        todo.setTitle(request.getParameter("title"));
        todo.setDescription(request.getParameter("description"));
        
        ofy().save().entity(todo).now();
		
        response.sendRedirect("/todo?profile=" + email);
        
	}
	
	private List<Todo> getAllTodo() {
		
		Query<Todo> query = ofy().load().type(Todo.class).order("title");
		
		return query.list();
	}
	
	private List<Todo> getTodoByEmail(String email) {
		
		Query<Todo> query = ofy().load().type(Todo.class).ancestor(Key.create(Profile.class, email));
		
		return query.list();
	}
	
	
}
