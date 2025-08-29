package org.example.testtask.Repository;

import org.example.testtask.Model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessingLogRepo extends JpaRepository<Log, UUID> {
}
