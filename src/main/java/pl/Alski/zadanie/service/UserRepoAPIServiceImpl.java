package pl.Alski.zadanie.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRepoAPIServiceImpl implements UserRepoAPIService {

    WebClient webClient = WebClient.create();
    private final Logger logger = LoggerFactory.getLogger(UserRepoAPIServiceImpl.class);
    private final String GITHUB_API_URL = "https://api.github.com";

    @Override
    public List<Repository> getUserRepositories(String username) {
        Flux<Repository> reposStream = webClient
                .get()
                .uri(GITHUB_API_URL + "/users/" + username + "/repos")
                .retrieve()
                .bodyToFlux(Repository.class);
        Mono<List<Repository>> collectedRepoStream = reposStream.collectList();
        List<Repository> repositoriesList = collectedRepoStream.block();
        if (repositoriesList.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return repositoriesList;
    }

    public List<Branch> getBranchesDetails(Repository r) {
        Flux<Branch> branchesStream = webClient
                .get()
                .uri(GITHUB_API_URL + "/repos/" + r.getOwner().getLogin() + "/" + r.getName() + "/branches")
                .retrieve()
                .bodyToFlux(Branch.class);
        logger.info("Getting branches details for repository: " + r.getName());
        Mono<List<Branch>> collectedBranchesStream = branchesStream.collectList();
        List<Branch> branchesList = collectedBranchesStream.block();
        return branchesList;
    }

}
