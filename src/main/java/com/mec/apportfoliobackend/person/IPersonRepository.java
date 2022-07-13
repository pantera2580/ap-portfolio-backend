package com.mec.apportfoliobackend.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IPersonRepository extends JpaRepository<Person, UUID> {
}
