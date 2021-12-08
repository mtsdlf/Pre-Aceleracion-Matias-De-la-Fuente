package com.mdelafuente.alkemy.disney.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mdelafuente.alkemy.disney.dto.MovieDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterBasicDTO;
import com.mdelafuente.alkemy.disney.dto.CharacterDTO;
import com.mdelafuente.alkemy.disney.entity.CharacterEntity;

@Component
public class CharacterMapper {
	
	@Autowired
	private MovieMapper movieMapper;
	
	public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO dto) {
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setHeight(dto.getHeight());
		entity.setBuildingDate(
				this.stringToLocalDate(dto.getBuildingDate())
		);
	}
	
	public CharacterEntity characterDTO2Entity(CharacterDTO dto) {
			CharacterEntity entity = new CharacterEntity();
			entity.setTitle(dto.getTitle());
			entity.setDescription(dto.getDescription());
			entity.setHeight(dto.getHeight());
			entity.setBuildingDate(
					this.stringToLocalDate(dto.getBuildingDate())
					);
			entity.setImageUrl(dto.getImageUrl());
			return entity;
	}
	
	public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
		CharacterDTO dto = new CharacterDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setDescription(entity.getDescription());
		dto.setHeight(entity.getHeight());
		dto.setBuildingDate(entity.getBuildingDate().toString());
		if (loadMovies) {
			List<MovieDTO> moviesDTOS = this.movieMapper.moviesEntityList2DTOList(entity.getMovies(), false);
			dto.setMovies(moviesDTOS);
		} 
		dto.setImageUrl(entity.getImageUrl());
		return dto;
	}
	
	public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO> dtos) {
		Set<CharacterEntity> entities = new HashSet<>();
		for (CharacterDTO dto : dtos) {
			entities.add(this.characterDTO2Entity(dto));
		}
		
		return entities;
	}

	/** 
	 * @param entities (Set or List)
	 * @param loadMovies
	 */
	public List<CharacterDTO> characterEntitySet2DTOList(Collection<CharacterEntity> entities, boolean loadMovies) {
		List<CharacterDTO> dtos = new ArrayList<>();
		for (CharacterEntity entity : entities) {
			dtos.add(this.characterEntity2DTO(entity, false));
		}
		return dtos;
	}
	
	public List<CharacterBasicDTO> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities) {
		List<CharacterBasicDTO> dtos = new ArrayList<>();
		CharacterBasicDTO basicDTO;
		for (CharacterEntity entity : entities) {
			basicDTO = new CharacterBasicDTO();
			basicDTO.setId(entity.getId());
			basicDTO.setTitle(entity.getTitle());
			basicDTO.setImageUrl(entity.getImageUrl());
			dtos.add(basicDTO);
		}
		return dtos;
	}
	
	private LocalDate stringToLocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(stringDate, formatter);
		return date;
	}

}
