package com.mec.apportfoliobackend.skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ISkillsRepository extends JpaRepository<Skills, UUID> {
}
