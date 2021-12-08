package com.mdelafuente.alkemy.disney.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdelafuente.alkemy.disney.dto.MovieBasicDTO;
import com.mdelafuente.alkemy.disney.dto.MovieDTO;
import com.mdelafuente.alkemy.disney.dto.MovieFiltersDTO;
import com.mdelafuente.alkemy.disney.entity.MovieEntity;
import com.mdelafuente.alkemy.disney.entity.CharacterEntity;
import com.mdelafuente.alkemy.disney.exceptions.ParamNotFound;
import com.mdelafuente.alkemy.disney.mapper.MovieMapper;
import com.mdelafuente.alkemy.disney.repository.MovieRepository;
import com.mdelafuente.alkemy.disney.repository.specification.MovieSpecification;
import com.mdelafuente.alkemy.disney.service.MovieService;
import com.mdelafuente.alkemy.disney.service.CharacterService;

@Service
public class MovieServiceImpl implements MovieService{

	private MovieRepository movieRepository;
	
	private MovieSpecification movieSpecification;
	
	private MovieMapper movieMapper;
	
	private CharacterService characterService;
	
	@Autowired
	public MovieServiceImpl(
			MovieRepository movieRepository,
			MovieSpecification movieSpecification,
			MovieMapper movieMapper,
			CharacterService characterService
	) {
		this.movieRepository = movieRepository;
		this.movieSpecification = movieSpecification;
		this.movieMapper = movieMapper;
		this.characterService = characterService;
	}

	@Override
	public List<MovieBasicDTO> getAll() {
		List<MovieEntity> entities = movieRepository.findAll();
		List<MovieBasicDTO> characterBasicDTOS = movieMapper.moviesEntityList2BasicDTOList(entities);
		return characterBasicDTOS;
	}
	
	@Override
	public MovieDTO getDetailsById(Long id) {
		Optional<MovieEntity> entity = Optional.of(movieRepository.getById(id));
		if (!entity.isPresent()) {
			throw new ParamNotFound("id movie not valid");
		}
		MovieDTO movieDTO = this.movieMapper.movieEntity2DTO(entity.get(), true);
		return movieDTO;
	}
	
	@Override public List<MovieDTO> getByFilters(String name, Long continent, String order) { 
		MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, continent, order);
		List<MovieEntity> entities = this.movieRepository.findAll(this.movieSpecification.getByFilters(filtersDTO));
		List<MovieDTO> dtos = this.movieMapper.movieEntitySet2DTOList(entities, true);
		return dtos;
	} 
	@Override
	public MovieDTO update(Long id, MovieDTO character) {
		Optional<MovieEntity> entity = this.movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("movie id not valid");
		}
		this.movieMapper.movieEntityRefreshValues(entity.get(), character);
		MovieEntity updatedEntity = this.movieRepository.save(entity.get());
		MovieDTO result = this.movieMapper.movieEntity2DTO(updatedEntity, true);
		return result;
	}
	
	@Override
	public MovieDTO save(MovieDTO dto) {
		MovieEntity entity = movieMapper.movieDTO2Entity(dto);
		MovieEntity entitySaved = movieRepository.save(entity);
		MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);
		return result;
	}

	@Override
	public void addCharacter(Long id, Long idCharacter) {
		MovieEntity entity = this.movieRepository.getById(id);
		entity.getCharacters().size();
		CharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
		entity.addCharacter(characterEntity);
		this.movieRepository.save(entity);
	}

	@Override
	public void removeCharacter(Long id, Long idCharacter) {
		MovieEntity entity = this.movieRepository.getById(id);
		entity.getCharacters().size();
		CharacterEntity characterEntity = this.characterService.getEntityById(idCharacter);
		entity.removeCharacter(characterEntity);
		this.movieRepository.save(entity);
	}
	
	@Override
	public void delete(Long id) {
		Optional<MovieEntity> entity = this.movieRepository.findById(id);
		if (!entity.isPresent()) {
			throw new ParamNotFound("Movie ID not valid");
		}
		this.movieRepository.deleteById(id);
	}

}
