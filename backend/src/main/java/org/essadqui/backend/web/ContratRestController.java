package org.essadqui.backend.web;

import lombok.AllArgsConstructor;
import org.essadqui.backend.dtos.ContratDTO;
import org.essadqui.backend.entities.Paiement;
import org.essadqui.backend.services.IAssuranceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@AllArgsConstructor
@CrossOrigin("*")
public class ContratRestController {

    private IAssuranceService service;

    @GetMapping("/client/{clientId}")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_EMPLOYE','ROLE_ADMIN')")
    public List<ContratDTO> getContratsByClient(@PathVariable Long clientId) {
        return service.getContratsByClient(clientId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public ContratDTO getContratById(@PathVariable Long id) {
        return service.getContratById(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public ContratDTO createContrat(@RequestBody ContratDTO dto) {
        return service.saveContrat(dto);
    }

    @PutMapping("/{id}/valider")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public void validerContrat(@PathVariable Long id) {
        service.validerContrat(id);
    }

    @PutMapping("/{id}/resilier")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void resilierContrat(@PathVariable Long id) {
        service.resilierContrat(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteContrat(@PathVariable Long id) {
        service.deleteContrat(id);
    }

    @GetMapping("/{id}/paiements")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT','ROLE_EMPLOYE','ROLE_ADMIN')")
    public List<Paiement> getPaiements(@PathVariable Long id) {
        return service.getPaiementsByContrat(id);
    }

    @PostMapping("/{id}/paiements")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public Paiement addPaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        return service.addPaiement(id, paiement);
    }
}