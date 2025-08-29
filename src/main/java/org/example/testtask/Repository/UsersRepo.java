package org.example.testtask.Repository;

import org.example.testtask.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByGmail(String gmail);

    boolean existsByGmail(String gmail);
}
