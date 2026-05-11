package org.essadqui.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssuranceApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(ClientRepository clientRepo,
                               ContratAssuranceRepository contratRepo,
                               PaiementRepository paiementRepo) {
        return args -> {
            // Créer des clients
            List.of("Ahmed Benali", "Sara Idrissi", "Omar Chakir").forEach(nom -> {
                Client c = new Client();
                c.setNom(nom);
                c.setEmail(nom.replace(" ", ".").toLowerCase() + "@gmail.com");
                clientRepo.save(c);
            });

            // Contrat automobile
            Client c1 = clientRepo.findAll().get(0);
            ContratAutomobile ca = new ContratAutomobile();
            ca.setClient(c1);
            ca.setDateSouscription(new Date());
            ca.setStatut(StatutContrat.EN_COURS);
            ca.setMontantCotisation(350.0);
            ca.setDureeContrat(12);
            ca.setTauxCouverture(80.0);
            ca.setNumImmatriculation("12345-A-6");
            ca.setMarque("Toyota");
            ca.setModele("Corolla");
            contratRepo.save(ca);

            // Contrat habitation
            Client c2 = clientRepo.findAll().get(1);
            ContratHabitation ch = new ContratHabitation();
            ch.setClient(c2);
            ch.setDateSouscription(new Date());
            ch.setStatut(StatutContrat.EN_COURS);
            ch.setMontantCotisation(200.0);
            ch.setDureeContrat(24);
            ch.setTauxCouverture(90.0);
            ch.setTypeLogement(TypeLogement.APPARTEMENT);
            ch.setAdresse("12 Rue Mohammed V, Casablanca");
            ch.setSuperficie(85.0);
            contratRepo.save(ch);

            // Contrat santé
            Client c3 = clientRepo.findAll().get(2);
            ContratSante cs = new ContratSante();
            cs.setClient(c3);
            cs.setDateSouscription(new Date());
            cs.setStatut(StatutContrat.EN_COURS);
            cs.setMontantCotisation(500.0);
            cs.setDureeContrat(12);
            cs.setTauxCouverture(95.0);
            cs.setNiveauCouverture(NiveauCouverture.PREMIUM);
            cs.setNbPersonnesCouvertes(4);
            contratRepo.save(cs);

            // Paiement
            Paiement p = new Paiement();
            p.setContrat(ca);
            p.setDate(new Date());
            p.setMontant(350.0);
            p.setType(TypePaiement.MENSUALITE);
            paiementRepo.save(p);

            System.out.println("=== Clients ===");
            clientRepo.findAll().forEach(System.out::println);
            System.out.println("=== Contrats ===");
            contratRepo.findAll().forEach(System.out::println);
        };
    }
}
