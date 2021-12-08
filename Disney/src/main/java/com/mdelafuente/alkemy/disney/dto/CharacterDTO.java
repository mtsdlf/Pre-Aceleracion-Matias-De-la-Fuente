package com.mdelafuente.alkemy.disney.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {
	private Long id;
	@NotEmpty
	private String name;
	private String imagePath;
	private String description;
	@Positive
	private Long weight;
	@Positive
	private Integer age;
	private List<MovieDTO> movies;

}
