package it.epicode.prenotazioni.model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import it.epicode.prenotazioni.StringAttributeConverter;





@Entity
@Table(name="edificio")
public class Edificio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String indirizzo;
	@ManyToOne
	private Citta citta;
	private boolean isActive; 
	
	@Convert(converter = StringAttributeConverter.class)
	@Size(max =8, min=8)
	private String codice;
	
	
	
	
	
	public Edificio() {
	}
	
	public Edificio(String nome, String indirizzo, Citta citta,String codice) {
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.codice=codice; 
		this.setActive(false);
	}
	
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public Citta getCitta() {
		return citta;
	}
	public void setCitta(Citta citta) {
		this.citta = citta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Edificio [nome=" + nome + ", indirizzo=" + indirizzo + ", citta=" + citta + ", id="
				+ id + "]";
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
