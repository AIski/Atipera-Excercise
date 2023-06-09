package pl.Alski.zadanie.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.entity.Repository;
import pl.Alski.zadanie.mapper.RepositoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final UserRepoAPIService userRepoAPIService;
    private static final Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private final RepositoryMapper mapper;

    @Override
    public List<RepositoryDTO> getUserRepositoriesWithoutForks(String username) {
        var repositories = userRepoAPIService.getUserRepositories(username);
        logger.info("Returning " + repositories.size() + " repositories for username:  " + username);
        checkIfEmpty(repositories);
        return mapper.toRepositoryDTOs(
                repositories
                        .stream()
                        .filter(r -> !r.fork())
                        .map(r -> getRepoBranchesDetails(r))
                        .collect(Collectors.toList()));
    }

    private static void checkIfEmpty(List<Repository> repositories) {
        if (repositories.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }

    private Repository getRepoBranchesDetails(Repository repo) {
        var branches = userRepoAPIService.getBranchesDetails(repo);
        return new Repository(
                repo.name(),
                repo.fork(),
                repo.owner(),
                branches
        );
    }

}
