package br.com.webflixapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.webflixapi.modelo.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
