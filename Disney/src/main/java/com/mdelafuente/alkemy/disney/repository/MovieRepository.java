package com.mdelafuente.alkemy.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdelafuente.alkemy.disney.entity.MovieEntity;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

	List<MovieEntity> findAll(Specification<MovieEntity> spec);
	
}
