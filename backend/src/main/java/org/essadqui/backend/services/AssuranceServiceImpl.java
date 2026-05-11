package org.essadqui.backend.services;

import lombok.AllArgsConstructor;
import org.essadqui.backend.dtos.ContratDTO;
import org.essadqui.backend.entities.*;
import org.essadqui.backend.enums.*;
import org.essadqui.backend.repositories.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class AssuranceServiceImpl implements IAssuranceService {

    private ClientRepository clientRepo;
    private ContratAssuranceRepository contratRepo;
    private PaiementRepository paiementRepo;

    @Override
    public Page<Client> getAllClients(int page, int size, String keyword) {
        return clientRepo.findByNomContainsIgnoreCase(keyword, PageRequest.of(page, size));
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepo.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepo.deleteById(id);
    }

    @Override
    public List<ContratDTO> getContratsByClient(Long clientId) {
        return contratRepo.findByClientId(clientId)
                .stream().map(this::toDTO).toList();
    }

    @Override
    public ContratDTO getContratById(Long id) {
        return contratRepo.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id));
    }

    @Override
    public ContratDTO saveContrat(ContratDTO dto) {
        Client client = clientRepo.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));
        ContratAssurance contrat;
        switch (dto.getTypeContrat().toUpperCase()) {
            case "AUTOMOBILE" -> {
                ContratAutomobile ca = new ContratAutomobile();
                ca.setNumImmatriculation(dto.getNumImmatriculation());
                ca.setMarque(dto.getMarque());
                ca.setModele(dto.getModele());
                contrat = ca;
            }
            case "HABITATION" -> {
                ContratHabitation ch = new ContratHabitation();
                ch.setTypeLogement(TypeLogement.valueOf(dto.getTypeLogement()));
                ch.setAdresse(dto.getAdresse());
                ch.setSuperficie(dto.getSuperficie());
                contrat = ch;
            }
            default -> {
                ContratSante cs = new ContratSante();
                cs.setNiveauCouverture(NiveauCouverture.valueOf(dto.getNiveauCouverture()));
                cs.setNbPersonnesCouvertes(dto.getNbPersonnesCouvertes());
                contrat = cs;
            }
        }
        contrat.setClient(client);
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setDateSouscription(new Date());
        contrat.setMontantCotisation(dto.getMontantCotisation());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setTauxCouverture(dto.getTauxCouverture());
        return toDTO(contratRepo.save(contrat));
    }

    @Override
    public void validerContrat(Long id) {
        ContratAssurance c = contratRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));
        c.setStatut(StatutContrat.VALIDE);
        c.setDateValidation(new Date());
        contratRepo.save(c);
    }

    @Override
    public void resilierContrat(Long id) {
        ContratAssurance c = contratRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));
        c.setStatut(StatutContrat.RESILIE);
        contratRepo.save(c);
    }

    @Override
    public void deleteContrat(Long id) {
        contratRepo.deleteById(id);
    }

    @Override
    public List<Paiement> getPaiementsByContrat(Long contratId) {
        return paiementRepo.findByContratId(contratId);
    }

    @Override
    public Paiement addPaiement(Long contratId, Paiement paiement) {
        ContratAssurance contrat = contratRepo.findById(contratId)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable"));
        paiement.setContrat(contrat);
        return paiementRepo.save(paiement);
    }

    private ContratDTO toDTO(ContratAssurance c) {
        ContratDTO dto = new ContratDTO();
        dto.setId(c.getId());
        dto.setStatut(c.getStatut() != null ? c.getStatut().name() : null);
        dto.setDateSouscription(c.getDateSouscription() != null ? c.getDateSouscription().toString() : null);
        dto.setMontantCotisation(c.getMontantCotisation());
        dto.setDureeContrat(c.getDureeContrat());
        dto.setTauxCouverture(c.getTauxCouverture());
        if (c.getClient() != null) {
            dto.setClientId(c.getClient().getId());
            dto.setClientNom(c.getClient().getNom());
        }
        if (c instanceof ContratAutomobile ca) {
            dto.setTypeContrat("AUTOMOBILE");
            dto.setNumImmatriculation(ca.getNumImmatriculation());
            dto.setMarque(ca.getMarque());
            dto.setModele(ca.getModele());
        } else if (c instanceof ContratHabitation ch) {
            dto.setTypeContrat("HABITATION");
            dto.setTypeLogement(ch.getTypeLogement() != null ? ch.getTypeLogement().name() : null);
            dto.setAdresse(ch.getAdresse());
            dto.setSuperficie(ch.getSuperficie());
        } else if (c instanceof ContratSante cs) {
            dto.setTypeContrat("SANTE");
            dto.setNiveauCouverture(cs.getNiveauCouverture() != null ? cs.getNiveauCouverture().name() : null);
            dto.setNbPersonnesCouvertes(cs.getNbPersonnesCouvertes());
        }
        return dto;
    }
}