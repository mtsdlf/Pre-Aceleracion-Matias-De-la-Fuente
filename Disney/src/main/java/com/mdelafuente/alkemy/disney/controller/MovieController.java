package com.mdelafuente.alkemy.disney.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdelafuente.alkemy.disney.dto.MovieBasicDTO;
import com.mdelafuente.alkemy.disney.dto.MovieDTO;
import com.mdelafuente.alkemy.disney.service.MovieService;

@RestController
@RequestMapping("movies")
public class MovieController {
	
	private MovieService movieService;
	
	@Autowired
	MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieBasicDTO>> getAll() {
		List<MovieBasicDTO> movies = movieService.getAll();
		return ResponseEntity.ok(movies);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MovieDTO> getDetailsById(
			@Valid @PathVariable Long id
	) {
		MovieDTO movie = this.movieService.getDetailsById(id);
		return ResponseEntity.ok(movie);
	}	
	
	@GetMapping
	public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
			@Valid @RequestParam(required = false) String name, 
			@Valid @RequestParam(required = false) Long genre,
			@Valid @RequestParam(required = false, defaultValue = "ASC") String order
	) {
		List<MovieDTO> movies = this.movieService.getByFilters(name, genre, order); 
		return ResponseEntity.ok(movies);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovieDTO> update (
			@Valid @PathVariable Long id,
			@Valid @RequestBody MovieDTO movie
	) {
		MovieDTO result = this.movieService.update(id, movie);
		return ResponseEntity.ok(result);
	}	
	
	@PostMapping
	public ResponseEntity<MovieDTO> save(
			@Valid @RequestBody MovieDTO movie
	) {
		MovieDTO result = movieService.save(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@PostMapping("/{id}/characters/{idCharacter}")
	public ResponseEntity<Void> addCharacter(
			@Valid @PathVariable Long id, 
			@PathVariable Long idCharacter
	){
		this.movieService.addCharacter(id, idCharacter);
		return ResponseEntity.status(HttpStatus.CREATED).build();	
	}

	@DeleteMapping("/{id}/characters/{idCharacter}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id, 
			@Valid @PathVariable Long idCharacter
	) {
		this.movieService.removeCharacter(id, idCharacter);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id
	) {
		this.movieService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
