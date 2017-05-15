package main.java.cgt.backingbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import main.java.cdp.classesAnemicas.DadosAcesso;
import main.java.cgd.crudjdbc.*;

@ManagedBean(name = "dadosAcessoBean")
@SessionScoped
public class DadosAcessoBean {

	private List<DadosAcesso> lista;
	private DadosAcesso dadosAcesso = new DadosAcesso();
	DadosAcessoCrudJDBC objDadosAcessoCrudJDBC = new DadosAcessoCrudJDBC();

	public List<DadosAcesso> getLista() {
		return lista;
	}

	public DadosAcesso getDadosAcesso() {
		return dadosAcesso;
	}

	
	public void setDadosAcesso(DadosAcesso dadosAcesso) {
		this.dadosAcesso = dadosAcesso;
	}

	public void setLista(List<DadosAcesso> lista) {
		this.lista = lista;
	}

	public String novo() {
		this.dadosAcesso.setId(-1);
		this.dadosAcesso.setEmail(null);
		this.dadosAcesso.setLembrar(null);
		this.dadosAcesso.setLogin(null);
		this.dadosAcesso.setSenha(null);
		this.dadosAcesso.setEdita(false);
		return "dadosAcesso";
	}

	public String excluirRegistro(DadosAcesso u) {
		DadosAcessoCrudJDBC.excluir(u);
		// salva o usu�rio
		return u.getId()+"";
	}

	public String alterarRegistro(DadosAcesso u) {
		u.setEdita(true);
		return null;
	}

	public String salvarRegistro() {
		for (DadosAcesso usu : lista) {
			if (usu.isEdita()) {
				DadosAcessoCrudJDBC.alterar(usu);
			}
			usu.setEdita(false);
		}
		DadosAcessoCrudJDBC.listar();
		return null;
	}

	public String inserir() {
		/*
		 * FacesContext context = FacesContext.getCurrentInstance(); if
		 * (!this.dadosAcesso.getSenha().equalsIgnoreCase(this.dadosAcesso.
		 * getConfirmaSenha())) { context.addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR,
		 * "Senha confirmada incorretamente", "")); return "usuario"; }
		 */
	
		DadosAcessoCrudJDBC.salvar(this.dadosAcesso);
		// salva o usu�rio
		return "sucesso";
	}

	public String verLista() {
		DadosAcessoCrudJDBC.listar();
		return "listagem";
	}

}