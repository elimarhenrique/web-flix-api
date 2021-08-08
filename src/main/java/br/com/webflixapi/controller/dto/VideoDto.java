package br.com.webflixapi.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.webflixapi.modelo.Video;

public class VideoDto {

	private Long id;
	private Long categoriaId;
	private String titulo;
	private String descricao;
	private String url;

	public VideoDto(Video video) {
		this.id = video.getId();
		this.categoriaId = video.getCategoria().getId();
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrl() {
		return url;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public static Page<VideoDto> converter(Page<Video> videos) {
		return videos.map(VideoDto::new);
	}

	public static List<VideoDto> converterList(List<Video> videos) {
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}

}
