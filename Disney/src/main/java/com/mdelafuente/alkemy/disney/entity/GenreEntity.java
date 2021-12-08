package com.mdelafuente.alkemy.disney.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@Table(name = "genres")
@SQLDelete(sql= "UPDATE genres SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class GenreEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String name;
	
	@Column(name = "image_path")
	private String imagePath;
	
	private boolean deleted = Boolean.FALSE;
	
}
