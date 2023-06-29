package by.anabios13.authorizationService.mappers;

import org.modelmapper.ModelMapper;

public interface DTOMapper<Model, DTO> {
    DTO mapToDTO(Model model);
    Model mapToModel(DTO dto);
}
