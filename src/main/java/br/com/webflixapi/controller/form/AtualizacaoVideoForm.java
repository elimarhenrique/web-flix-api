package br.com.webflixapi.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.webflixapi.modelo.Video;
import br.com.webflixapi.repository.VideoRepository;

public class AtualizacaoVideoForm {

	@NotNull
	@NotEmpty
	private String titulo;

	@NotNull
	@NotEmpty
	private String descricao;

	@NotNull
	@NotEmpty
	private String url;

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

	public Video atualiza(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.findById(id).get();

		video.setTitulo(this.titulo);
		video.setDescricao(this.descricao);
		video.setUrl(this.url);

		return video;
	}

}
