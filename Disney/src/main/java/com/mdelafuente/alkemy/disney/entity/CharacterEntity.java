package com.mdelafuente.alkemy.disney.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@Table(name = "characters")
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class CharacterEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String description;
	
	@Positive
	private Long weight;
	
	@Positive
	private Integer age;
	
	@ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL, fetch= FetchType.LAZY)
	private List<MovieEntity> movies = new ArrayList<>();
	
	@Column(name = "image_path")
	private String imagePath;
	
	private boolean deleted = Boolean.FALSE;

}
