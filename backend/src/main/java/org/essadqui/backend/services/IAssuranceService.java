package org.essadqui.backend.services;

import ma.enset.assurance.dtos.ContratDTO;
import ma.enset.assurance.entities.Client;
import ma.enset.assurance.entities.Paiement;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IAssuranceService {
    // Clients
    Page<Client> getAllClients(int page, int size, String keyword);
    Client getClientById(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);

    // Contrats
    List<ContratDTO> getContratsByClient(Long clientId);
    ContratDTO getContratById(Long id);
    ContratDTO saveContrat(ContratDTO dto);
    void validerContrat(Long id);
    void resilierContrat(Long id);
    void deleteContrat(Long id);

    // Paiements
    List<Paiement> getPaiementsByContrat(Long contratId);
    Paiement addPaiement(Long contratId, Paiement paiement);
}
