package it.epicode.prenotazioni.security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {

	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	private String username;	
	private String password;
}