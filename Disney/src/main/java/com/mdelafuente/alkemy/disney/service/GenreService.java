package com.mdelafuente.alkemy.disney.service;

import java.util.List;

import com.mdelafuente.alkemy.disney.dto.GenreDTO;

public interface GenreService {
	
	List<GenreDTO> getAll();
	
	GenreDTO getDetailsById(Long id);
	
	GenreDTO update(Long id, GenreDTO continent);
	
	GenreDTO save(GenreDTO continent);
	
	void delete(Long id);
	
}
