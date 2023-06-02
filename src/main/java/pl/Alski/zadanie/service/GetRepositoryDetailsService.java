package pl.Alski.zadanie.service;

import org.springframework.stereotype.Service;
import pl.Alski.zadanie.entity.Repository;

@Service
public interface GetRepositoryDetailsService {
    public Repository getDetails(Repository repo);
}
