package com.mdelafuente.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdelafuente.alkemy.disney.dto.GenreDTO;
import com.mdelafuente.alkemy.disney.entity.GenreEntity;
import com.mdelafuente.alkemy.disney.exceptions.ParamNotFound;
import com.mdelafuente.alkemy.disney.mapper.GenreMapper;
import com.mdelafuente.alkemy.disney.repository.GenreRepository;
import com.mdelafuente.alkemy.disney.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService{
	
	private GenreRepository genreRepository;
	
	private GenreMapper genreMapper;
	
	@Autowired
	public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
		this.genreRepository = genreRepository;
		this.genreMapper = genreMapper;
	}

	@Override
	public List<GenreDTO> getAll() {
		List<GenreEntity> entities = genreRepository.findAll();
		List<GenreDTO> result = genreMapper.continentEntityList2DTOList(entities);
		return result;
	}

	@Override
	public GenreDTO getDetailsById(Long id) {
		Optional<GenreEntity> entity = Optional.of(genreRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("continent id not valid");
		}
		GenreDTO genreDTO = this.genreMapper.continentEntity2DTO(entity.get());
		return genreDTO;
	}
	
	@Override
	public GenreDTO update(Long id, GenreDTO continent) {
		Optional<GenreEntity> oldEntity = Optional.of(this.genreRepository.getById(id));
		if (!oldEntity.isPresent()) {
			throw new ParamNotFound("character id not valid");
		}
		GenreEntity newEntity = genreMapper.continentDTO2Entity(continent);
		newEntity.setId(oldEntity.get().getId());
		GenreEntity entitySaved = genreRepository.save(newEntity);
		GenreDTO result = genreMapper.continentEntity2DTO(entitySaved);
		return result;
	}
	
	@Override
	public GenreDTO save(GenreDTO dto) {
		GenreEntity entity = genreMapper.continentDTO2Entity(dto);
		GenreEntity entitySaved = genreRepository.save(entity);
		GenreDTO result = genreMapper.continentEntity2DTO(entitySaved);
		return result;
	}
	
	@Override
	public void delete(Long id) {
		Optional<GenreEntity> entity = this.genreRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("Genre ID not valid");
		}
		this.genreRepository.deleteById(id);
	}

}
