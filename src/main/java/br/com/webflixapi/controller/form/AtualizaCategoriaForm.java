package br.com.webflixapi.controller.form;

import br.com.webflixapi.modelo.Categoria;
import br.com.webflixapi.repository.CategoriaRepository;

public class AtualizaCategoriaForm {

	private String titulo;
	private String cor;

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

	public Categoria atualiza(Long id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.findById(id).get();
		
		categoria.setTitulo(this.titulo);
		categoria.setCor(this.cor);
		
		return categoria;
	}

}
