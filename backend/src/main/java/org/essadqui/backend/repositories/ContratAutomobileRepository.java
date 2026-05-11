package org.essadqui.backend.repositories;

import org.essadqui.backend.entities.ContratAutomobile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratAutomobileRepository extends JpaRepository<ContratAutomobile, Long> {
    List<ContratAutomobile> findByMarque(String marque);
}