package org.essadqui.backend.web;

// ClientRestController.java
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.essadqui.backend.entities.Client;
import org.essadqui.backend.services.IAssuranceService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
@CrossOrigin("*")
public class ClientRestController {

    private IAssuranceService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public Page<Client> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "") String keyword) {
        return service.getAllClients(page, size, keyword);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_EMPLOYE','ROLE_ADMIN')")
    public Client getClientById(@PathVariable Long id) {
        return service.getClientById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client saveClient(@RequestBody @Valid Client client) {
        return service.saveClient(client);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        client.setId(id);
        return service.saveClient(client);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteClient(@PathVariable Long id) {
        service.deleteClient(id);
    }
}