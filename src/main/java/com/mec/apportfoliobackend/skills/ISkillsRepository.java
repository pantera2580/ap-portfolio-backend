package com.mec.apportfoliobackend.skills;

import com.mec.apportfoliobackend.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ISkillsRepository extends JpaRepository<Skills, UUID> {
    Optional<List<Skills>> findByPerson(Person person);
}
