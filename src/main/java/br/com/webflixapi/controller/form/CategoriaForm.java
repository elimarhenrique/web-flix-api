package br.com.webflixapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.webflixapi.modelo.Categoria;

public class CategoriaForm {

	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String cor;

	public CategoriaForm(@NotNull @NotEmpty String titulo, @NotNull @NotEmpty String cor) {
		this.titulo = titulo;
		this.cor = cor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Categoria converter() {
		return new Categoria(titulo, cor);
	}
}
