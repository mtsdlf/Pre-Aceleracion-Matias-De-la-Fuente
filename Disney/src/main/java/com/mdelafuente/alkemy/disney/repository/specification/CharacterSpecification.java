 package com.mdelafuente.alkemy.disney.repository.specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.mdelafuente.alkemy.disney.dto.CharacterFiltersDTO;
import com.mdelafuente.alkemy.disney.entity.CharacterEntity;
import com.mdelafuente.alkemy.disney.entity.MovieEntity;

@Component
public class CharacterSpecification {
	
	public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
		return (root, query, criteriaBuilder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(root.get("title")),
										"%" + filtersDTO.getName().toLowerCase() + "%"
						) 
				);	
			}
			
			if (StringUtils.hasLength(filtersDTO.getDate())) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = LocalDate.parse(filtersDTO.getDate(), formatter);
				predicates.add(
						criteriaBuilder.equal(
								root.<LocalDate>get("buildingDate"), 
								date
						)
				);
			}
			
			if (!CollectionUtils.isEmpty(filtersDTO.getMovies())) {
				Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
				Expression<String> moviesId = join.get("id");
				predicates.add(moviesId.in(filtersDTO.getMovies()));
			}
			
			//remove duplicates
			query.distinct(true);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	};
}}
