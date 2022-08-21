package models;

import java.io.Serializable;
import java.util.Objects;

public class ModelEndereco implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long Id;
	private String cep;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;	//localidade
	private String uf;
	private String complemento;
	private ModelLogin usuario_id;
	private ModelLogin criado_por;
	private ModelLogin modificado_por;
	private boolean isEnderecoPrincipal; 
	

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


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public ModelLogin getUsuario_id() {
		return usuario_id;
	}


	public void setUsuario_id(ModelLogin usuario_id) {
		this.usuario_id = usuario_id;
	}


	public ModelLogin getCriado_por() {
		return criado_por;
	}


	public void setCriado_por(ModelLogin criado_por) {
		this.criado_por = criado_por;
	}


	public ModelLogin getModificado_por() {
		return modificado_por;
	}


	public void setModificado_por(ModelLogin modificado_por) {
		this.modificado_por = modificado_por;
	}


	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}
	
	


	public boolean isEnderecoPrincipal() {
		return isEnderecoPrincipal;
	}


	public void setEnderecoPrincipal(boolean isEnderecoPrincipal) {
		this.isEnderecoPrincipal = isEnderecoPrincipal;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelEndereco other = (ModelEndereco) obj;
		return Objects.equals(Id, other.Id);
	}
	
	
	
}
