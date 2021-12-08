package com.mdelafuente.alkemy.disney.controller;

import java.util.List;
import java.util.Set;

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

import com.mdelafuente.alkemy.disney.dto.CharacterBasicDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterDTO;
import com.mdelafuente.alkemy.disney.service.CharacterService;

@RestController
@RequestMapping("characters")
public class CharacterController {
	
	private CharacterService characterService;
	
	@Autowired
	CharacterController(CharacterService characterService) {
		this.characterService = characterService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CharacterBasicDTO>> getAll() {
		List<CharacterBasicDTO> characters = this.characterService.getAll();	
		return ResponseEntity.ok(characters);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> getDetailsById(@Valid @PathVariable Long id) {
		CharacterDTO character = this.characterService.getDetailsById(id);
		return ResponseEntity.ok(character);
	}
	
	@GetMapping
	public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
			@Valid @RequestParam(required = false) String name, 
			@Valid @RequestParam(required = false) String date,
			@RequestParam(required = false) Set<Long> movies,
			@Valid @RequestParam(required = false, defaultValue = "ASC") String order
	) {
		List<CharacterDTO> characters = this.characterService.getByFilters(name, date, movies, order); 
		return ResponseEntity.ok(characters);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CharacterDTO> update (@Valid @PathVariable Long id, @RequestBody CharacterDTO character) {
		CharacterDTO result = this.characterService.update(id, character);;
		return ResponseEntity.ok(result);
	}
	 
	@PostMapping
	public ResponseEntity<CharacterDTO> save(@Valid @RequestBody CharacterDTO character) {
		CharacterDTO result = this.characterService.save(character);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
		this.characterService.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
