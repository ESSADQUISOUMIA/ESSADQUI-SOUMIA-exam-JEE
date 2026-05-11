package org.essadqui.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.essadqui.backend.enums.StatutContrat;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_contrat", discriminatorType = DiscriminatorType.STRING)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class ContratAssurance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateSouscription;

    @Enumerated(EnumType.STRING)
    private StatutContrat statut;

    @Temporal(TemporalType.DATE)
    private Date dateValidation;

    private double montantCotisation;
    private int dureeContrat;
    private double tauxCouverture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Paiement> paiements = new ArrayList<>();
}