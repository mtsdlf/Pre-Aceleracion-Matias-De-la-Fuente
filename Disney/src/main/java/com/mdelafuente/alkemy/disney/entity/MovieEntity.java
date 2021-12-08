package com.mdelafuente.alkemy.disney.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "movies")
@SQLDelete(sql= "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause= "deleted=false")
public class MovieEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	private String title;
	
	@Column(name = "release_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate releaseDate;
	
	@Positive
	@Max(5)
	private Integer rating;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinColumn(name = "genre_id", insertable = false, updatable = false)
	private GenreEntity genre;
	
	@Column(name = "genre_id", nullable = false)
	private Long genreId;
	
	@Column(name = "image_path")
	private String imagePath;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToMany(
		cascade = {
				CascadeType.PERSIST,
				CascadeType.MERGE 
		}
	)
	@JoinTable(
			name = "characters_movies",
			joinColumns = @JoinColumn(name = "characters_id", referencedColumnName="id"),
			inverseJoinColumns = @JoinColumn(name = "movies_id", referencedColumnName="id")
	)
	private Set<CharacterEntity> characters = new HashSet<CharacterEntity>();

	public void addCharacter(CharacterEntity character) {
		this.characters.add(character);
	}
	
	public void removeCharacter(CharacterEntity character) {
		this.characters.remove(character);
	}
	
}
