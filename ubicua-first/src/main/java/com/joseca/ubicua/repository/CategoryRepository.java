package com.joseca.ubicua.repository;

import org.springframework.stereotype.Repository;
import com.joseca.ubicua.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
