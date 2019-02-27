package com.stefanini.pokemon.entities;

public class Usuario extends EntityBase {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;

	private String senha;

	private Integer tipoAdmin;

	private Icone icone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTipoAdmin() {
		return tipoAdmin;
	}

	public void setTipoAdmin(int admin) {
		this.tipoAdmin = admin;
	}

	public Icone getIcone() {
		return icone;
	}

	public void setIcone(Icone icone) {
		this.icone = icone;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", tipoAdmin=" + tipoAdmin + ", icone="
				+ icone + "]";
	}
	
	

}
