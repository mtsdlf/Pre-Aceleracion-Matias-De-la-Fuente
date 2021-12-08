package com.mdelafuente.alkemy.disney.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdelafuente.alkemy.disney.entity.CharacterEntity;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
	
	List<CharacterEntity> findAll(Specification<CharacterEntity> spec);
	
}
