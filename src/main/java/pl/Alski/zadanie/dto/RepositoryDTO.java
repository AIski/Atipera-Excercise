package pl.Alski.zadanie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public record RepositoryDTO (
     String name,
     String ownerLogin,
     List<BranchDTO> branches
){}
