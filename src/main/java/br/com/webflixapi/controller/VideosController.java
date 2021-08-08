package br.com.webflixapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.webflixapi.controller.dto.VideoDto;
import br.com.webflixapi.controller.form.AtualizacaoVideoForm;
import br.com.webflixapi.controller.form.VideoForm;
import br.com.webflixapi.modelo.Video;
import br.com.webflixapi.repository.CategoriaRepository;
import br.com.webflixapi.repository.VideoRepository;

@RestController
public class VideosController {

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	CategoriaRepository categoriaRepository;

	@GetMapping("/videos")
	public Page<VideoDto> listVideos(@RequestParam int page) {
		Pageable paginacao = PageRequest.of(page, 5);

		Page<Video> videos = videoRepository.findAll(paginacao);
		return VideoDto.converter(videos);
	}

	@GetMapping("/videos/")
	public List<VideoDto> searchVideos(@RequestParam String search) {
		List<Video> videos = videoRepository.findByTituloContainingIgnoreCase(search);
		return VideoDto.converterList(videos);
	}

	@GetMapping("/videos/{id}")
	public ResponseEntity<VideoDto> getVideosById(@PathVariable Long id) {
		// return videoRepository.findById(id).get();
		Optional<Video> video = videoRepository.findById(id);

		if (video.isPresent()) {
			return ResponseEntity.ok(new VideoDto(video.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/videos")
	@Transactional
	public ResponseEntity<VideoDto> addVideo(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		Video video = videoForm.converter(categoriaRepository);
		videoRepository.save(video);

		URI uri = uriBuilder.path("/videos{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));
	}

	@PutMapping("/videos/{id}")
	@Transactional
	public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id,
			@Valid @RequestBody AtualizacaoVideoForm atualizaVideoForm) {
		Optional<Video> optionalVideo = videoRepository.findById(id);

		if (optionalVideo.isPresent()) {
			Video video = atualizaVideoForm.atualiza(id, videoRepository);
			return ResponseEntity.ok(new VideoDto(video));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/videos/{id}")
	@Transactional
	public ResponseEntity<?> deleteVideo(@PathVariable Long id) {
		Optional<Video> optionalVideo = videoRepository.findById(id);

		if (optionalVideo.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
