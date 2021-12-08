package com.mdelafuente.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdelafuente.alkemy.disney.dto.CharacterBasicDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterFiltersDTO;
import com.mdelafuente.alkemy.disney.entity.CharacterEntity;
import com.mdelafuente.alkemy.disney.exceptions.ParamNotFound;
import com.mdelafuente.alkemy.disney.mapper.CharacterMapper;
import com.mdelafuente.alkemy.disney.repository.CharacterRepository;
import com.mdelafuente.alkemy.disney.repository.specification.CharacterSpecification;
import com.mdelafuente.alkemy.disney.service.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService{
	
	private CharacterRepository characterRepository;
	
	private CharacterSpecification characterSpecification;
	
	private CharacterMapper characterMapper;

	@Autowired
	public CharacterServiceImpl(
			CharacterRepository characterRepository, 
			CharacterSpecification characterSpecification, 
			CharacterMapper characterMapper
	) {
		this.characterRepository = characterRepository;
		this.characterSpecification = characterSpecification;
		this.characterMapper = characterMapper;
	}
		
	@Override
	public List<CharacterBasicDTO> getAll() {
		List<CharacterEntity> entities = this.characterRepository.findAll();
		List<CharacterBasicDTO> characterBasicDTOS = this.characterMapper.characterEntitySet2BasicDTOList(entities);
		return characterBasicDTOS;
	}
	
	@Override
	public CharacterEntity getEntityById(Long id) {
		Optional<CharacterEntity> entity = characterRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("Character ID not valid");
		}
		CharacterEntity result = entity.get();
		return result;
	}

	@Override
	public CharacterDTO getDetailsById(Long id) {
		Optional<CharacterEntity> entity = this.characterRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("character id not valid");
		}
		CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get(), true);
		return characterDTO;
	}
	
	@Override public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> movies) { 
		CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, movies);
		List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
		List<CharacterDTO> dtos = this.characterMapper.characterEntitySet2DTOList(entities, true);
		return dtos;
	}
	
	@Override
	public CharacterDTO update(Long id, CharacterDTO character) {
		Optional<CharacterEntity> entity = this.characterRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("character id not valid");
		}
		
		this.characterMapper.characterEntityRefreshValues(entity.get(), character);
		CharacterEntity updatedEntity= this.characterRepository.save(entity.get());
		CharacterDTO result = characterMapper.characterEntity2DTO(updatedEntity, true);
		return result;
	} 
	
	@Override
	public CharacterDTO save(CharacterDTO characterDTO) {
		CharacterEntity entity = characterMapper.characterDTO2Entity(characterDTO);
		CharacterEntity entitySaved = characterRepository.save(entity);
		CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public void delete(Long id) {
		Optional<CharacterEntity> entity = this.characterRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("Character id not valid");
		}
		this.characterRepository.deleteById(id);
	}

}
