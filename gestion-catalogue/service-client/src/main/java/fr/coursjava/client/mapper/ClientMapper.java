package fr.coursjava.client.mapper;

import fr.coursjava.api.model.Client;
import fr.coursjava.api.model.ClientReference;
import fr.coursjava.client.bo.ClientBo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AdresseMapper.class})
public interface ClientMapper {

    ClientReference boToReference(ClientBo bo);

    Client boToDto(ClientBo bo);
}
