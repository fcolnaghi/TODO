package br.com.todo;

import static br.com.todo.service.OfyService.ofy;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.todo.model.Profile;

import com.googlecode.objectify.Key;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		
		Profile profile = getProfile((String) request.getParameter("email"));
		
		response.sendRedirect("/todo?profile=" + profile.getEmail());
				
	}
	
	/**
	 * Responsável por recuperar o profile do usuário para podermos carregar a sua lista de TODOs
	 * @param email
	 * @return Profile
	 */
	public static Profile getProfile(String email) {
		
		Key<Profile> key = Key.create(Profile.class, email);
		Profile profile = (Profile) ofy().load().key(key).now();
		
		if (profile == null) {
			profile = new Profile();			
			
			profile.setEmail(email);
			
			ofy().save().entities(profile).now();
		}
		
		return profile;
	}

}
