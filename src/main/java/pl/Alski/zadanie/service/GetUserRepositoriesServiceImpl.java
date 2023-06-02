package pl.Alski.zadanie.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.Alski.zadanie.controller.UserController;
import pl.Alski.zadanie.entity.Repository;


@Service
@RequiredArgsConstructor
public class GetUserRepositoriesServiceImpl implements GetUserRepositoriesService {

    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(GetUserRepositoriesServiceImpl.class);

    private final String GITHUB_API_URL = "https://api.github.com";


    @Override
    public Repository[] getUserRepositories(String username) {
        logger.info("username: " + username.toString());
        String userApiUrl = GITHUB_API_URL + "/users/" + username + "/repos";
        logger.info("Hitting api url: " + userApiUrl);
        try {
            Repository[] repositories = restTemplate.getForObject(userApiUrl, Repository[].class);
            for (Repository repository : repositories) {
                logger.info("Repository: " + repository.getName() + " " + repository.getOwner().getLogin() + " " + repository.getBranches());
            }
           return repositories;

        } catch (HttpClientErrorException exception) {
            logger.info(exception.getMessage());
            return new Repository[]{};
        }
    }
}
