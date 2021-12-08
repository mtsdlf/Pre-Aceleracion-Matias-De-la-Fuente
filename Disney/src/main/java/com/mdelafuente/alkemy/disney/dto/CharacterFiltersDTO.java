package com.mdelafuente.alkemy.disney.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CharacterFiltersDTO {
	private String name;
	private Integer age;
	private Set<Long> movies;
	
}
