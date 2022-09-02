package components;

import java.io.Serializable;

public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;

	private int Id;
	private String descricao;
	
	public Perfil(int id, String descricao) {
		super();
		Id = id;
		this.descricao = descricao;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
