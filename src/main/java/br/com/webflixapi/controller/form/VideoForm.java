package br.com.webflixapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.webflixapi.modelo.Categoria;
import br.com.webflixapi.modelo.Video;
import br.com.webflixapi.repository.CategoriaRepository;

public class VideoForm {

	@NotNull
	private Long categoriaId;
	
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull @NotEmpty
	private String url;

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Video converter(CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.findById(this.categoriaId).get();
		return new Video(categoria, titulo, descricao, url);
	}
}
