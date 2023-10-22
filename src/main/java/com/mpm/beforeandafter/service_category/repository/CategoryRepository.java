package com.mpm.beforeandafter.service_category.repository;

import com.mpm.beforeandafter.service_category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
