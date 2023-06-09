package pl.Alski.zadanie.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRepoAPIServiceImpl implements UserRepoAPIService {

    private final WebClient webClient= WebClient.builder().build();
    private static final Logger logger = LoggerFactory.getLogger(UserRepoAPIServiceImpl.class);
    private final String GITHUB_API_URL = "https://api.github.com";

    @Override
    public List<Repository> getUserRepositories(String username) {
        return webClient
                .get()
                .uri(GITHUB_API_URL + "/users/" + username + "/repos")
                .retrieve()
                .bodyToFlux(Repository.class)
                .collectList()
                .block();
    }

    public List<Branch> getBranchesDetails(Repository repository) {
        logger.info("Getting branches details for repository: " + repository.name());
        return webClient
                .get()
                .uri(GITHUB_API_URL + "/repos/" + repository.owner().login() + "/" + repository.name() + "/branches")
                .retrieve()
                .bodyToFlux(Branch.class)
                .collectList()
                .block();
    }

}
