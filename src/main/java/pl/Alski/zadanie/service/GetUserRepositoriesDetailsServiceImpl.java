package pl.Alski.zadanie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.Alski.zadanie.entity.Repository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GetUserRepositoriesDetailsServiceImpl implements GetUserRepositoriesDetailsService{

    private GetRepositoryDetailsService getRepoDetailsService;

    @Override
    public List<Repository> getDetails(Repository[] repositories) {
        List<Repository> results = new ArrayList<>();
        for (Repository repo : repositories){
            results.add(
                    getRepoDetailsService.getDetails(repo)
            );
        }
        return results;
    }
}
