package org.essadqui.backend.services;

import org.essadqui.backend.dtos.ContratDTO;
import org.essadqui.backend.entities.Client;
import org.essadqui.backend.entities.Paiement;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IAssuranceService {
    Page<Client> getAllClients(int page, int size, String keyword);
    Client getClientById(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);

    List<ContratDTO> getContratsByClient(Long clientId);
    ContratDTO getContratById(Long id);
    ContratDTO saveContrat(ContratDTO dto);
    void validerContrat(Long id);
    void resilierContrat(Long id);
    void deleteContrat(Long id);

    List<Paiement> getPaiementsByContrat(Long contratId);
    Paiement addPaiement(Long contratId, Paiement paiement);
}