package br.com.model;

public class Copiar {
	
	
	private String origem;
	private String destino;
	private String ID_supervisor;
	
	
	public Copiar(String origem, String Id_supervisor) {
		
		setID_supervisor(Id_supervisor);
		setOrigem(origem);
		setDestino();
		
		
	}
	
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {		
		
		this.origem = "\\\\"+origem+"\\C$\\Users\\"+ID_supervisor+"\\Desktop";
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino() {
		this.destino = "C:\\Users\\"+ID_supervisor+"\\Desktop\\";
	}
	public String getID_supervisor() {
		return ID_supervisor;
	}
	public void setID_supervisor(String iD_supervisor) {
		ID_supervisor = iD_supervisor;
	}

}
