package com.mdelafuente.alkemy.disney.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDTO {
	private Long id;
	@NotEmpty
	private String title;
	private String imagePath;
	@Positive
	@Max(5)
	private Integer rating;
	@Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message="invalid releaseDate format")
	private String releaseDate;
	private List<CharacterDTO> characters;
	private Long genreId;

}
