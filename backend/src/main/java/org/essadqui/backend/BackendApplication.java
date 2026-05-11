package org.essadqui.backend;

import org.essadqui.backend.entities.*;
import org.essadqui.backend.enums.*;
import org.essadqui.backend.repositories.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            ClientRepository clientRepo,
            ContratAssuranceRepository contratRepo,
            PaiementRepository paiementRepo
    ) {
        return args -> {

            // =========================
            // Création des clients
            // =========================
            List<String> noms = List.of(
                    "Ahmed Benali",
                    "Sara Idrissi",
                    "Omar Chakir"
            );

            for (String nom : noms) {
                Client c = new Client();

                c.setNom(nom);

                String email = nom.replace(" ", ".")
                        .toLowerCase()
                        + "@gmail.com";

                c.setEmail(email);

                clientRepo.save(c);
            }

            // =========================
            // Création d’un contrat
            // =========================
            Client client = clientRepo.findAll().get(0);

            ContratAutomobile ca = new ContratAutomobile();

            ca.setClient(client);
            ca.setDateSouscription(new Date());
            ca.setStatut(StatutContrat.EN_COURS);
            ca.setMontantCotisation(350.0);
            ca.setDureeContrat(12);
            ca.setTauxCouverture(80.0);
            ca.setNumImmatriculation("1234-A-6");
            ca.setMarque("Toyota");
            ca.setModele("Corolla");

            contratRepo.save(ca);

            // =========================
            // Création d’un paiement
            // =========================
            Paiement p = new Paiement();

            p.setContrat(ca);
            p.setDate(new Date());
            p.setMontant(350.0);
            p.setType(TypePaiement.MENSUALITE);

            paiementRepo.save(p);

            // =========================
            // Affichage des données
            // =========================
            System.out.println("=== Liste des clients ===");
            clientRepo.findAll().forEach(System.out::println);

            System.out.println("=== Liste des contrats ===");
            contratRepo.findAll().forEach(System.out::println);

            System.out.println("=== Liste des paiements ===");
            paiementRepo.findAll().forEach(System.out::println);
        };
    }
}