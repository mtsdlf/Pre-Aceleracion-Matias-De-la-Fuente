package com.mdelafuente.alkemy.disney.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenreDTO {
	private Long id;
	@NotEmpty
	private String name;
	private String imagePath;

}
