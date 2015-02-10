package br.com.todo.model;

import static br.com.todo.service.OfyService.ofy;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
@Entity
public class Todo {
	
	@Id
	private Long id;
	
	@Parent
	private Key<Profile> profileKey;
	
	@Index
	private String title;
	private String description;
	private Boolean complete;
	private Date created;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public Profile getProfile() {
		return ofy().load().key(profileKey).now();
	}
	
	public void setProfileKey(Key<Profile> profileKey) {
		this.profileKey = profileKey;
	}
	
	public void setEmail(String email) {
		profileKey = Key.create(Profile.class, email);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getComplete() {
		return complete;
	}
	public void setComplete(Boolean complete) {
		this.complete = complete;
	}
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
