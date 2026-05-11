package org.essadqui.backend.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaiementDTO {
    private Long id;
    private Long contratId;
    private BigDecimal montant;
    private String modePaiement;
    private String statut;
    private String reference;
    private LocalDateTime datePaiement;
    private LocalDateTime dateEcheance;
    private String numeroTransaction;
    private String payeurNom;
    private String commentaire;
}