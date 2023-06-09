package pl.Alski.zadanie.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.service.RepositoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repositories")
@AllArgsConstructor
public class UserController {

    RepositoryService repositoryService;

    @PostMapping(value = "/{username}",  produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<RepositoryDTO> getRepositories(@PathVariable String username){
            return repositoryService.getUserRepositoriesWithoutForks(username);
    }

}

