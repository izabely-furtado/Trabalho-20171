package cdp.classesAnemicas;

public class InfoMedicas {
	private int id;
	private boolean medicacao;
	private String qmedicacao;
	private String tipoSangue;
	private boolean alergia;
	private String qalergia;
	private String contatoSOS;
	private boolean edita;
	public InfoMedicas(){
		this.setId(-1);
		this.setAlergia(false);
		this.setContatoSOS("");
		this.setMedicacao(false);
		this.setQalergia("");
		this.setQmedicacao("");
		this.setTipoSangue("");
		this.setEdita(false);
	}
	public boolean isMedicacao() {
		return medicacao;
	}
	public void setMedicacao(boolean medicacao) {
		this.medicacao = medicacao;
	}
	public String getQmedicacao() {
		return qmedicacao;
	}
	public void setQmedicacao(String qmedicacao) {
		this.qmedicacao = qmedicacao;
	}
	public String getTipoSangue() {
		return tipoSangue;
	}
	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}
	public boolean isAlergia() {
		return alergia;
	}
	public void setAlergia(boolean alergia) {
		this.alergia = alergia;
	}
	public String getQalergia() {
		return qalergia;
	}
	public void setQalergia(String qalergia) {
		this.qalergia = qalergia;
	}
	public String getContatoSOS() {
		return contatoSOS;
	}
	public void setContatoSOS(String contatoSOS) {
		this.contatoSOS = contatoSOS;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isEdita() {
		return edita;
	}
	public void setEdita(boolean edita) {
		this.edita = edita;
	}
	
}
