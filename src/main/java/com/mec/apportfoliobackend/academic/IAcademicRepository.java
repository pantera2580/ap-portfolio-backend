package com.mec.apportfoliobackend.academic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IAcademicRepository extends JpaRepository<Academic, UUID> {
}
