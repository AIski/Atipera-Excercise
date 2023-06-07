package pl.Alski.zadanie.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;
import pl.Alski.zadanie.mapper.RepositoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final UserRepoAPIService userRepoAPIService;
    private final Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private final RepositoryMapper mapper;

    @Override
    public List<RepositoryDTO> getUserRepositoriesWithoutForks(String username) {
        var repositories = userRepoAPIService.getUserRepositories(username)
                .stream()
                .filter(r -> !r.isFork())
                .map(r -> getRepoBranchesDetails(r))
                .collect(Collectors.toList());
        logger.info("Returning " + repositories.size() + " repositories for username:  " + username);
        return mapper.toRepositoryDTOs(repositories);
    }

    private Repository getRepoBranchesDetails(Repository repo) {
        List<Branch> branches = userRepoAPIService.getBranchesDetails(repo);
        repo.setBranches(branches);
        return repo;
    }

}
