package com.mec.apportfoliobackend.experience;

import com.mec.apportfoliobackend.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IExperienceRepository extends JpaRepository<Experience, UUID> {
    Optional<List<Experience>> findByPerson(Person person);
}
