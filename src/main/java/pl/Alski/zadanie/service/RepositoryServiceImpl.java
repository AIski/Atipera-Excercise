package pl.Alski.zadanie.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.entity.Repository;
import pl.Alski.zadanie.mapper.RepositoryMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryServiceImpl implements RepositoryService {

    private final UserRepoAPIService userRepoAPIService;
     private final Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);
    private final RepositoryMapper mapper;


    @Override
    public List<RepositoryDTO> getUserRepositoriesWithoutForks(String username) {
        Repository[] repositoriesArray = userRepoAPIService.getUserRepositories(username);
        if (repositoriesArray != null) {
           var repositories = userRepoAPIService
                   .getRepoDetails(repositoriesArray)
                   .stream()
                   .filter(r -> !r.isFork())
                   .collect(Collectors.toList());
           return mapper.toRepositoryDTOs(repositories);
        } else return new ArrayList<RepositoryDTO>();
    }
}
