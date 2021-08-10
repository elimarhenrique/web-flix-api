package br.com.webflixapi.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.webflixapi.controller.dto.CategoriaDto;
import br.com.webflixapi.controller.form.AtualizaCategoriaForm;
import br.com.webflixapi.controller.form.CategoriaForm;
import br.com.webflixapi.modelo.Categoria;
import br.com.webflixapi.repository.CategoriaRepository;

@RestController
public class CategoriaController {

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping("/categorias")
	@Cacheable(value = "listaDeCategorias")
	public Page<CategoriaDto> listCategoria(@PageableDefault(size = 5) Pageable paginacao) {
		//Pageable paginacao = PageRequest.of(page, 5);

		Page<Categoria> categorias = categoriaRepository.findAll(paginacao);
		return CategoriaDto.converter(categorias);
	}

	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaDto> getCategoriaById(@PathVariable Long id) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

		if (optionalCategoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(optionalCategoria.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/categorias")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<CategoriaDto> addCategoria(@Valid @RequestBody CategoriaForm categoriaform, UriComponentsBuilder uriBuilder) {
		Categoria categoria = categoriaform.converter();
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@DeleteMapping("/categorias/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

		if (optionalCategoria.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/categorias/{id}")
	@Transactional
	@CacheEvict(value = "listaDeCategorias", allEntries = true)
	public ResponseEntity<CategoriaDto> updateCategoria(@PathVariable Long id,
			@Valid @RequestBody AtualizaCategoriaForm atualizaCategoriaForm) {
		Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

		if (optionalCategoria.isPresent()) {
			Categoria categoria = atualizaCategoriaForm.atualiza(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		return ResponseEntity.notFound().build();
	}

}
