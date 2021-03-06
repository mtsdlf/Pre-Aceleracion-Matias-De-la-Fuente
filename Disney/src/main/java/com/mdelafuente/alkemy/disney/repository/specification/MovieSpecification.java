 package com.mdelafuente.alkemy.disney.repository.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.mdelafuente.alkemy.disney.dto.MovieFiltersDTO;
import com.mdelafuente.alkemy.disney.entity.MovieEntity;

@Component
public class MovieSpecification {
	
	public Specification<MovieEntity> getByFilters(MovieFiltersDTO filtersDTO) {
		return (root, query, criteriaBuilder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if (StringUtils.hasLength(filtersDTO.getName())) {
				predicates.add(
						criteriaBuilder.like(
								criteriaBuilder.lower(
										root.get("title")),
										"%" + filtersDTO.getName().toLowerCase() + "%"
						) 
				);	
			}
			if (!Objects.isNull(filtersDTO.getGenre())) {
				predicates.add(
						criteriaBuilder.equal(root.get("genre"), filtersDTO.getGenre())
				);	
			}
			
			//remove duplicates
			query.distinct(true);
				
			//order resolver
			String orderByField = "id";
			query.orderBy(
					filtersDTO.isAsc() ?
							criteriaBuilder.asc(root.get(orderByField)) :
							criteriaBuilder.desc(root.get(orderByField))
			);
			
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	};
}}
