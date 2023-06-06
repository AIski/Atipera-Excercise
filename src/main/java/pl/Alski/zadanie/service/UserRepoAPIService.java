package pl.Alski.zadanie.service;

import org.springframework.stereotype.Service;
import pl.Alski.zadanie.entity.Repository;

import java.util.List;

@Service
public interface UserRepoAPIService {
    public Repository[] getUserRepositories(String username);

    public List<Repository> getRepoDetails(Repository[] repositories);


}
