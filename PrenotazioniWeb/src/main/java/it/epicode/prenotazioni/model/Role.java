package it.epicode.prenotazioni.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="role")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;

	@Enumerated(EnumType.STRING)
	private RoleType roletype;
	
	@Override
	public String toString() {
		return ""+roletype;
	}
	public Role(RoleType roletype) {
		this.roletype = roletype;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleType getRoletype() {
		return roletype;
	}
	public Role() {
	}
	
}
