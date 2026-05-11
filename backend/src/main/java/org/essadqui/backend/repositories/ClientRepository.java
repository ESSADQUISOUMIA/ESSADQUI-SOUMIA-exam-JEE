package org.essadqui.backend.repositories;

import org.essadqui.backend.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findByNomContainsIgnoreCase(String keyword, Pageable pageable);
    Optional<Client> findByEmail(String email);
}
