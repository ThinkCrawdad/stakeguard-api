package com.stakeguard.api.repositories;

import com.stakeguard.api.models.Selection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectionRepository extends JpaRepository<Selection, Long> {
}
