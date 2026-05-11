package org.essadqui.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.essadqui.backend.enums.NiveauCouverture;
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("SANTE")
@Data @AllArgsConstructor @NoArgsConstructor
public class ContratSante extends ContratAssurance {
    @Enumerated(EnumType.STRING)
    private NiveauCouverture niveauCouverture;
    private int nbPersonnesCouvertes;
}
