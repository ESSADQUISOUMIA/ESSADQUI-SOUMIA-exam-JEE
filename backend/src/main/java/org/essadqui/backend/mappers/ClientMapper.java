package org.essadqui.backend.mappers;

import org.essadqui.backend.dtos.*;
import org.essadqui.backend.entities.*;
import org.essadqui.backend.enums.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {

    public ClientDTO toDTO(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setEmail(client.getEmail());
        dto.setTelephone(client.getTelephone());
        dto.setAdresse(client.getAdresse());
        dto.setCodePostal(client.getCodePostal());
        dto.setVille(client.getVille());
        dto.setNumeroSecuriteSociale(client.getNumeroSecuriteSociale());
        dto.setDateInscription(client.getDateInscription());
        dto.setActif(client.getActif());

        if (client.getContrats() != null) {
            dto.setNombreContrats(client.getContrats().size());
            dto.setTotalCotisations(client.getContrats().stream()
                    .map(ContratAssurance::getMontantCotisation)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }

        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setAdresse(dto.getAdresse());
        client.setCodePostal(dto.getCodePostal());
        client.setVille(dto.getVille());
        client.setNumeroSecuriteSociale(dto.getNumeroSecuriteSociale());
        client.setDateInscription(dto.getDateInscription() != null ? dto.getDateInscription() : LocalDateTime.now());
        client.setActif(dto.getActif() != null ? dto.getActif() : true);

        return client;
    }
}