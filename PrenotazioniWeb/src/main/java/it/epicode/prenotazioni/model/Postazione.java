package it.epicode.prenotazioni.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name="postazione")
public class Postazione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descrizione;
	
	private String codice;
	
	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;
	
	private Integer numeroMax;
	@ManyToOne
	private Edificio edificio;
	
	
	
	public Postazione() {
	}
	public Postazione(String codice,String descrizione, TipoPostazione tipo, Integer numeroMax, Edificio edificio) {
		this.descrizione = descrizione;
		this.codice = codice;
		this.tipo = tipo;
		this.numeroMax = numeroMax;
		this.edificio = edificio;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public TipoPostazione getTipo() {
		return tipo;
	}
	public void setTipo(TipoPostazione tipo) {
		this.tipo = tipo;
	}
	public Integer getNumeroMax() {
		return numeroMax;
	}
	public void setNumeroMax(Integer numeroMax) {
		this.numeroMax = numeroMax;
	}
	public Edificio getEdificio() {
		return edificio;
	}
	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Postazione [id=" + id + ", descrizione=" + descrizione + ", tipo=" + tipo + ", numeroMax=" + numeroMax
				+ ", edificio=" + edificio + "]";
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
}
