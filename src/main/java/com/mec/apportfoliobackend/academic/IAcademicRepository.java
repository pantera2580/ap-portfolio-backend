package com.mec.apportfoliobackend.academic;

import com.mec.apportfoliobackend.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IAcademicRepository extends JpaRepository<Academic, UUID> {
    Optional<List<Academic>> findByPerson(Person person);
}