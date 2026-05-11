package org.essadqui.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.essadqui.backend.enums.TypePaiement;
import java.util.Date;

@Entity
@Table(name = "paiements")
@Data @AllArgsConstructor @NoArgsConstructor
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private double montant;

    @Enumerated(EnumType.STRING)
    private TypePaiement type;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private ContratAssurance contrat;
}