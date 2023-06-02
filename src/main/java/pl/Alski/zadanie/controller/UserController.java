package pl.Alski.zadanie.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.Alski.zadanie.dto.RepositoryDTO;
import pl.Alski.zadanie.entity.ErrorResponse;
import pl.Alski.zadanie.service.RepositoryServiceFacade;


import java.util.List;

@RestController
@RequestMapping("/api/v1/repositories")
@AllArgsConstructor
public class UserController {

    RepositoryServiceFacade service;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/{username}", method = RequestMethod.POST)
    public ResponseEntity<Object> getRepositories(@PathVariable String username,
                                                  @RequestHeader("Accept") String acceptHeader) {
        if (acceptHeader.equals("application/json")) {
            List<RepositoryDTO> repositories = service.getUserRepositoriesWithoutForksWithDetails(username);
            return repositories.isEmpty() ?
                    new ResponseEntity<>(
                            new ErrorResponse(404, "User not found. Please give an existing github user."),
                            HttpStatus.NOT_FOUND)
                    : ResponseEntity.ok(repositories);
        }
        else return new ResponseEntity<>(
                new ErrorResponse(406, "Not acceptable header. Please use 'Accept= application/json' instead."),
                HttpStatus.NOT_ACCEPTABLE);
    }
}

