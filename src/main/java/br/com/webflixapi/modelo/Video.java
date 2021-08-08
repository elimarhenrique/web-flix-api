package br.com.webflixapi.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "videos")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Categoria categoria;
	
	@NotNull(message = "campo titulo deve ser preenchido")
	@NotEmpty @Size(min = 2, max = 255)
	private String titulo;
	
	@NotNull(message = "campo descricao deve ser preenchido")
	@NotEmpty @Size(min = 5, max = 255)
	private String descricao;
	
	@NotNull(message = "campo url deve ser preenchido")
	@URL
	private String url;	
	
	public Video()  {
	}
	
	public Video(Categoria categoria,
			@NotNull(message = "campo titulo deve ser preenchido") @NotEmpty @Size(min = 2, max = 255) String titulo,
			@NotNull(message = "campo descricao deve ser preenchido") @NotEmpty @Size(min = 5, max = 255) String descricao,
			@NotNull(message = "campo url deve ser preenchido") @URL String url) {
		this.categoria = categoria;
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
