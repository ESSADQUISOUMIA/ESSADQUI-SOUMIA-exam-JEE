package org.essadqui.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.essadqui.backend.enums.TypeLogement;
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("HABITATION")
@Data @AllArgsConstructor @NoArgsConstructor
public class ContratHabitation extends ContratAssurance {
    @Enumerated(EnumType.STRING)
    private TypeLogement typeLogement;
    private String adresse;
    private double superficie;
}
