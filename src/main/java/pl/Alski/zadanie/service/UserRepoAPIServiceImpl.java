package pl.Alski.zadanie.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRepoAPIServiceImpl implements UserRepoAPIService {

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(UserRepoAPIServiceImpl.class);
    private final String GITHUB_API_URL = "https://api.github.com";

    @Override
    public Repository[] getUserRepositories(String username) {
        String userApiUrl = GITHUB_API_URL + "/users/" + username + "/repos";
        logger.info("Hitting api url: " + userApiUrl);
            Repository[] repositories = restTemplate.getForObject(userApiUrl, Repository[].class);
            for (Repository repository : repositories) {
                logger.info("Repository: " + repository.getName() + " " + repository.getOwner().getLogin() + " " + repository.getBranches());
            }
            return repositories;
    }

    @Override
    public List<Repository> getRepoDetails(Repository[] repositories) {
        List<Repository> results = new ArrayList<>();
        for (Repository repo : repositories) {
            String repoApiUrl = GITHUB_API_URL + "/repos/" + repo.getOwner().getLogin() + "/" + repo.getName() + "/branches";
            logger.info("Hitting repoApiUrl: " + repoApiUrl);
            List<Branch> repoBranches = Arrays.stream(
                            restTemplate.getForObject(repoApiUrl, Branch[].class)).toList();
            repo.setBranches(repoBranches);
            results.add(repo
            );
        }
        return results;
    }

}
