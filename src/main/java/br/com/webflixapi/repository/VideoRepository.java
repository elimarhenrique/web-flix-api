package br.com.webflixapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webflixapi.modelo.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

//	@Query("SELECT v FROM Video v WHERE v.titulo LIKE :search")
//	List<Video> buscaPorTitulo(@Param("search") String search);

	List<Video> findByTituloLike(String search);

	List<Video> findByTitulo(String search);

	List<Video> findByTituloContainingIgnoreCase(String search);

	List<Video> getVideoCategoriaById(Long id);
}
