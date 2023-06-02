package pl.Alski.zadanie.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.Alski.zadanie.entity.Repository;
import pl.Alski.zadanie.dto.RepositoryDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BranchMapper.class})
public interface RepositoryMapper {

    @Mapping(source = "owner.login", target = "ownerLogin")
    RepositoryDTO toRepositoryDTO(Repository repo);

    List<RepositoryDTO> toRepositoryDTOs(List<Repository> repos);
}
