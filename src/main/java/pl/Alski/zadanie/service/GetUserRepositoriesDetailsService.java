package pl.Alski.zadanie.service;

import org.springframework.stereotype.Service;
import pl.Alski.zadanie.entity.Repository;

import java.util.List;

@Service
public interface GetUserRepositoriesDetailsService {
    public List<Repository> getDetails(Repository[] repositories);
}
