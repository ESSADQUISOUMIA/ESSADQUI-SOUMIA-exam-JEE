package org.essadqui.backend.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String codePostal;
    private String ville;
    private String numeroSecuriteSociale;
    private LocalDateTime dateInscription;
    private Boolean actif;
    private Integer nombreContrats;
    private BigDecimal totalCotisations;
}