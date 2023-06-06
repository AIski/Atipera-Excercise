package pl.Alski.zadanie.service;

import org.springframework.stereotype.Service;
import pl.Alski.zadanie.dto.RepositoryDTO;

import java.util.List;

@Service
public interface RepositoryService {
   List<RepositoryDTO> getUserRepositoriesWithoutForks(String username);

}
