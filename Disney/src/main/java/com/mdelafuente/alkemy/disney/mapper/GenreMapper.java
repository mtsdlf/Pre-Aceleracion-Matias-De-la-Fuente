package com.mdelafuente.alkemy.disney.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mdelafuente.alkemy.disney.dto.GenreDTO;
import com.mdelafuente.alkemy.disney.entity.GenreEntity;

@Component
public class GenreMapper {
	
	public GenreEntity genreDTO2Entity(GenreDTO dto) {
			GenreEntity genreEntity = new GenreEntity();
			genreEntity.setName(dto.getName());
			genreEntity.setImagePath(dto.getImagePath());
			return genreEntity;
	}

	public GenreDTO genreEntity2DTO(GenreEntity entity) {
		GenreDTO dto = new GenreDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImagePath(entity.getImagePath());
		return dto;
	}

	public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {
		List<GenreDTO> dtos = new ArrayList<>();
		for (GenreEntity entity : entities) {
			dtos.add(this.genreEntity2DTO(entity));
		}
		return dtos;
	}

}
