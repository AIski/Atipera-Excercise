package pl.Alski.zadanie.service;

import org.springframework.stereotype.Service;
import pl.Alski.zadanie.entity.Branch;
import pl.Alski.zadanie.entity.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public interface UserRepoAPIService {
    public List<Repository> getUserRepositories(String username);

    public List<Branch> getBranchesDetails(Repository repo);


}
