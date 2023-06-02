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
public class RepositoryServiceFacadeImpl implements RepositoryServiceFacade{

    private final GetUserRepositoriesService getUserRepositoriesService;
    private final GetUserRepositoriesDetailsService getUserRepositoriesDetailsService;
    private final Logger logger = LoggerFactory.getLogger(RepositoryServiceFacadeImpl.class);
    private final RepositoryMapper mapper;


    @Override
    public List<RepositoryDTO> getUserRepositoriesWithoutForksWithDetails(String username) {
        Repository[] repositoriesArray = getUserRepositoriesService.getUserRepositories(username);
        if (repositoriesArray == null){
            return new ArrayList<RepositoryDTO>();
        }
        return mapper.toRepositoryDTOs(
                getUserRepositoriesDetailsService
                .getDetails(repositoriesArray)
                .stream()
                .filter(r -> !r.isFork())
                .collect(Collectors.toList())
        );
    }
}
