package by.anabios13.authorizationService.mappers;

public interface DTOMapper<Model, DTO> {
    DTO mapToDTO(Model model);
    Model mapToModel(DTO dto);
}
