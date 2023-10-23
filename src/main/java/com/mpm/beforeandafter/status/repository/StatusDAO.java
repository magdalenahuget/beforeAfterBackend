package com.mpm.beforeandafter.status.repository;

import com.mpm.beforeandafter.status.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends JpaRepository<Status, Long> {
}
