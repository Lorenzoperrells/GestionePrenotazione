package it.epicode.prenotazioni.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;


@Entity
@Table(name="prenotazione")
public class Prenotazione {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NonNull
	Long id;
	@ManyToOne
	@NonNull
	User user;
	@ManyToOne
	@NonNull
	Postazione postazione;
	@NonNull
	LocalDate dataPrenotata;
	LocalDate dataPrenotazione;
	
	
	
	
	
	public Prenotazione(User user, Postazione postazione, LocalDate dataPrenotata) {
		this.user = user;
		this.postazione = postazione;
		this.dataPrenotata = dataPrenotata;
		this.dataPrenotazione =LocalDate.now();
	}
	public Long getId() {
		return id;
	}
	public Prenotazione() {
		super();
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Postazione getPostazione() {
		return postazione;
	}
	public void setPostazione(Postazione postazione) {
		this.postazione = postazione;
	}
	public LocalDate getDataPrenotata() {
		return dataPrenotata;
	}
	public void setDataPrenotata(LocalDate dataPrenotata) {
		this.dataPrenotata = dataPrenotata;
	}
	public LocalDate getDataPrenotazione() {
		return dataPrenotazione;
	}
	public void setDataPrenotazione(LocalDate dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}
	@Override
	public String toString() {
		return "Prenotazione= " + id +" "+  user.getUsername() + ", postazione=" + postazione.getDescrizione()+postazione.getEdificio().getNome() + ", Prenotato per "
				+ dataPrenotata + " il " + dataPrenotazione;
	}

}
