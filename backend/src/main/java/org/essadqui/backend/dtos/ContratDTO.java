package org.essadqui.backend.dtos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class ContratDTO {
    private Long id;
    private String dateSouscription;
    private String statut;
    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;
    private Long clientId;
    private String clientNom;
    private String typeContrat;

    // Automobile
    private String numImmatriculation;
    private String marque;
    private String modele;

    // Habitation
    private String typeLogement;
    private String adresse;
    private Double superficie;
    // Santé
    private String niveauCouverture;
    private Integer nbPersonnesCouvertes;
}
