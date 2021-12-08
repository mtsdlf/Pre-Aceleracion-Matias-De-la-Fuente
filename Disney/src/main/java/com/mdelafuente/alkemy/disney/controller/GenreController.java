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
import org.springframework.web.bind.annotation.RestController;

import com.mdelafuente.alkemy.disney.dto.GenreDTO;
import com.mdelafuente.alkemy.disney.service.GenreService;

@RestController
@RequestMapping("continents")
public class GenreController {
	
	private GenreService genreService;
	
	@Autowired
	GenreController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<GenreDTO>> getAll() {
		List<GenreDTO> continents = genreService.getAll();
		return ResponseEntity.ok().body(continents);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<GenreDTO> getDetailsById(
			@Valid @PathVariable Long id) {
		GenreDTO continent = this.genreService.getDetailsById(id);
		return ResponseEntity.ok(continent);
	}	

	@PutMapping("/{id}")
	public ResponseEntity<GenreDTO> update (
			@Valid @PathVariable Long id, 
			@RequestBody GenreDTO continent) {
		GenreDTO result = this.genreService.update(id, continent);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<GenreDTO> save(
			@RequestBody GenreDTO continent) {
		GenreDTO result = genreService.save(continent);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(
			@Valid @PathVariable Long id) {
		this.genreService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
