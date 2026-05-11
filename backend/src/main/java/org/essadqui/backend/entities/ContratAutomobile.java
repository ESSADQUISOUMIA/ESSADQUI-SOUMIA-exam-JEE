package org.essadqui.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("AUTOMOBILE")
@Data @AllArgsConstructor @NoArgsConstructor
public class ContratAutomobile extends ContratAssurance {
    private String numImmatriculation;
    private String marque;
    private String modele;
}
