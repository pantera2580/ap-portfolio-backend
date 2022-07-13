package com.mec.apportfoliobackend.experience;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IExperienceRepository extends JpaRepository<UUID, Experience> {
}
