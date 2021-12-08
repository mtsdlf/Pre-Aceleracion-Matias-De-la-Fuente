package com.mdelafuente.alkemy.disney.service;

import java.util.List;
import java.util.Set;

import com.mdelafuente.alkemy.disney.dto.CharacterBasicDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterDTO;
import com.mdelafuente.alkemy.disney.entity.CharacterEntity;

public interface CharacterService {
	
	List<CharacterBasicDTO> getAll();
	
	CharacterEntity getEntityById(Long id);
	
	CharacterDTO getDetailsById(Long id);
	
	List<CharacterDTO> getByFilters(String name, String date, Set<Long> movies, String order);
	
	CharacterDTO update(Long id, CharacterDTO character);
	
	CharacterDTO save(CharacterDTO character);
	
	void delete(Long id);
	
}
