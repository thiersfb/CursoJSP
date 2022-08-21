package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ModelLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long Id;
	private String login;
	private String senha;
	private String nome;
	private String sobrenome;
	private String email;
	private String cpf;
	private String rg;
	private Date dtNascimento;
	private String statusId;
	private boolean isAdmin; 
	private String perfil; 
	private String genero;
	
	private String fotoUser;
	private String fotoUserExtensao;
	
	private double rendamensal;
	
	private List<ModelTelefone> telefones = new ArrayList<ModelTelefone>();
	
	public boolean isNovo() {
		
		if (this.Id == null) {
			return true; /*iInserir novo*/
		} else if (this.Id != null && this.getId() > 0) {
			return false; /*Atualizar*/
		}
		
		return Id == null;
	}
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Date getDtNascimento() {
		return dtNascimento;
	}


	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}


	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public String getFotoUser() {
		return fotoUser;
	}


	public void setFotoUser(String fotoUser) {
		this.fotoUser = fotoUser;
	}


	public String getFotoUserExtensao() {
		return fotoUserExtensao;
	}


	public void setFotoUserExtensao(String fotoUserExtensao) {
		this.fotoUserExtensao = fotoUserExtensao;
	}

	
	public double getRendamensal() {
		return rendamensal;
	}

	public void setRendamensal(double rendamensal) {
		this.rendamensal = rendamensal;
	}	
	
	public void setTelefones(List<ModelTelefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<ModelTelefone> getTelefones() {
		return telefones;
	}

}
