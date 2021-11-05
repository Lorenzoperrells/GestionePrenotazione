package it.epicode.prenotazioni.model;

public enum TipoPostazione {
	PRIVATO ("privato"),OPENSPACE("openspace"),SALA_RIUNIONI("sala_riunioni");
	
	private String tipoPostazione;
	
	private TipoPostazione(String tipoPostazione) {
		this.tipoPostazione = tipoPostazione;
		
	}
	public String getTipoPostazione() {
		return tipoPostazione;
	}
}
