package pl.Alski.zadanie.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.Alski.zadanie.dto.BranchDTO;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchMapper {

    @Mapping(source = "commit.sha", target = "lastCommitSha")
    BranchDTO toBranchDTO(Branch branch);

    List<BranchDTO> toBranchDTOs(List<Branch> branches);
}
