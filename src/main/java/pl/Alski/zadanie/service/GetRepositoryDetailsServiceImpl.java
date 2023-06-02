package pl.Alski.zadanie.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class GetRepositoryDetailsServiceImpl implements GetRepositoryDetailsService {

    private final String GITHUB_API_URL = "https://api.github.com";
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(RepositoryServiceFacadeImpl.class);


    @Override
    public Repository getDetails(Repository repo) {
        String repoApiUrl = GITHUB_API_URL + "/repos/" + repo.getOwner().getLogin() + "/" + repo.getName() + "/branches";
        logger.info("Hitting repoApiUrl: " + repoApiUrl);
        Branch[] repoBranches = restTemplate.getForObject(repoApiUrl, Branch[].class);
        repo.setBranches(Arrays.stream(repoBranches).toList());
        return repo;
    }
}
