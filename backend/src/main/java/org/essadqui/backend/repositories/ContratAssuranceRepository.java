package org.essadqui.backend.repositories;

import org.essadqui.backend.entities.ContratAssurance;
import org.essadqui.backend.enums.StatutContrat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {
    List<ContratAssurance> findByClientId(Long clientId);
    List<ContratAssurance> findByStatut(StatutContrat statut);
    Page<ContratAssurance> findByClientIdAndStatut(Long clientId, StatutContrat statut, Pageable pageable);
}