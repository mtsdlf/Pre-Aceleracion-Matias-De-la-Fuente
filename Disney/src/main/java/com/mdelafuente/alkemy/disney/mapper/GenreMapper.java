package com.mdelafuente.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mdelafuente.alkemy.disney.dto.GenreDTO;
import com.mdelafuente.alkemy.disney.entity.GenreEntity;

@Component
public class GenreMapper {
	
	public GenreEntity continentDTO2Entity(GenreDTO dto) {
			GenreEntity genreEntity = new GenreEntity();
			genreEntity.setTitle(dto.getTitle());
			genreEntity.setImageUrl(dto.getImageUrl());
			return genreEntity;
	}

	public GenreDTO continentEntity2DTO(GenreEntity entity) {
		GenreDTO dto = new GenreDTO();
		dto.setId(entity.getId());
		dto.setTitle(entity.getTitle());
		dto.setImageUrl(entity.getImageUrl());
		return dto;
	}

	public List<GenreDTO> continentEntityList2DTOList(List<GenreEntity> entities) {
		List<GenreDTO> dtos = new ArrayList<>();
		for (GenreEntity entity : entities) {
			dtos.add(this.continentEntity2DTO(entity));
		}
		return dtos;
	}

}
