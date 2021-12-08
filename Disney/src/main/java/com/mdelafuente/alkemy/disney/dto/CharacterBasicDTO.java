package com.mdelafuente.alkemy.disney.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CharacterBasicDTO {
	private Long id;
	private String title;
	private String imageUrl;
	
}
