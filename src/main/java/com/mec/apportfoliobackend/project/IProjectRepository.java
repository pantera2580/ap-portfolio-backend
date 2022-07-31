package com.mec.apportfoliobackend.project;

import com.mec.apportfoliobackend.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface IProjectRepository extends JpaRepository<Project, UUID> {
    Optional<List<Project>> findByPerson(Person person);
}
