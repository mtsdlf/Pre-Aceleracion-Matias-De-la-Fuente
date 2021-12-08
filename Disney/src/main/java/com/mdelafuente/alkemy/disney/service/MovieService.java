package com.mdelafuente.alkemy.disney.service;

import java.util.List;

import com.mdelafuente.alkemy.disney.dto.MovieBasicDTO;
import com.mdelafuente.alkemy.disney.dto.MovieDTO;

public interface MovieService {
	
	List<MovieBasicDTO> getAll();
	
	MovieDTO getDetailsById(Long id);
	
	List<MovieDTO> getByFilters(String name, Long continent, String order);
	
	MovieDTO update(Long id, MovieDTO character);
	
	MovieDTO save(MovieDTO movie);

	void addCharacter(Long id, Long idCharacter);
	
	void removeCharacter(Long id, Long idCharacter);
	
	void delete(Long id);

	
}
