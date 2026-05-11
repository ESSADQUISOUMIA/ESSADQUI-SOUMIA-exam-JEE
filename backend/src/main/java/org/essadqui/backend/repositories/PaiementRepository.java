package org.essadqui.backend.repositories;

import org.essadqui.backend.entities.Paiement;
import org.essadqui.backend.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByContratId(Long contratId);
    List<Paiement> findByType(TypePaiement type);
}
