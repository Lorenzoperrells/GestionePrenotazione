package it.epicode.prenotazioni.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import it.epicode.prenotazioni.StringAttributeConverter;
import lombok.Data;


@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	String username;

	String nome;
	@Convert (converter=StringAttributeConverter.class)
	@Email
	String email;
	
	String password;
	
	String cognome;

	Boolean active = true;
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles = new HashSet<>();
	
	
	
	
	public User( String username, String nome, String email, String password, Boolean active) {
		this.username = username;
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.active = active;
	}
	
	public User() {
	}

	 public User(Long id, String username, String email, String password, String nome, String cognome) {
	        super();
	        this.id = id;
	        this.username = username;
	        this.email = email;
	        this.password = password;
	        this.nome = nome;
	        this.cognome = cognome;
	    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "id=" + id + ", username=" + username + ", nome=" + nome + ", email=" + email + ", password="
				+ password + ", active=" + active;
	}
	
	

}
