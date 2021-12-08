package com.mdelafuente.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.mdelafuente.alkemy.disney.dto.MovieBasicDTO;
import com.mdelafuente.alkemy.disney.dto.MovieDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterDTO;
import com.mdelafuente.alkemy.disney.entity.MovieEntity;

@Component
public class MovieMapper {
	
	
	private CharacterMapper characterMapper;
	
	@Autowired
	public MovieMapper(@Lazy CharacterMapper characterMapper) {
	
		this.characterMapper = characterMapper;
	}

	public void movieEntityRefreshValues(MovieEntity entity, MovieDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setPopulation(dto.getPopulation());
		entity.setArea(dto.getArea());
		entity.setGenreId(dto.getGenreId());
		entity.setImageUrl(dto.getImageUrl());
	}
	
	public MovieEntity movieDTO2Entity(MovieDTO dto) {
			MovieEntity movieEntity = new MovieEntity();
			movieEntity.setTitle(dto.getTitle());
			movieEntity.setPopulation(dto.getPopulation());
			movieEntity.setArea(dto.getArea());
			movieEntity.setImageUrl(dto.getImageUrl());
			movieEntity.setGenreId(dto.getGenreId());
			return movieEntity;
	}

	public MovieDTO movieEntity2DTO(MovieEntity entity, Boolean loadCharacters) {
		MovieDTO dto = new MovieDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setPopulation(entity.getPopulation());
		dto.setArea(entity.getArea());
		if(loadCharacters) {
			List<CharacterDTO> characterDTOS = this.characterMapper.characterEntitySet2DTOList(entity.getCharacters(), loadCharacters);
			dto.setCharacters(characterDTOS);
		}
		
		dto.setImageUrl(entity.getImageUrl());
		dto.setGenreId(entity.getGenreId());
		return dto;
	}

	public List<MovieDTO> moviesEntityList2DTOList(List<MovieEntity> movieList, Boolean loadCharacters) {
		List<MovieDTO> dtos = new ArrayList<>();
		for (MovieEntity entity : movieList) {
			dtos.add(this.movieEntity2DTO(entity, loadCharacters));
		}
		return dtos;
	}
	
	/** 
	 * @param entities (Set or List)
	 * @param loadMovies
	 */
	public List<MovieDTO> movieEntitySet2DTOList(Collection<MovieEntity> entities, boolean loadCharacters) {
		List<MovieDTO> dtos = new ArrayList<>();
		for (MovieEntity entity : entities) {
			dtos.add(this.movieEntity2DTO(entity, loadCharacters));
		}
		return dtos;
	}

	public List<MovieBasicDTO> moviesEntityList2BasicDTOList(List<MovieEntity> entities) {
		List<MovieBasicDTO> dtos = new ArrayList<>();
		MovieBasicDTO basicDTO;
		for (MovieEntity entity : entities) {
			basicDTO = new MovieBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setImageUrl(entity.getImageUrl());
			dtos.add(basicDTO);
		}
		return dtos;
	}

}
