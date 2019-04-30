package fr.coursjava.client.mapper;

import fr.coursjava.api.model.Adresse;
import fr.coursjava.client.bo.AdresseBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdresseMapper {

    @Mapping(target = "ville", source = "ville.nom")
    @Mapping(target = "codePostal", source = "ville.codePostal")
    Adresse boToDto(AdresseBo bo);
}
