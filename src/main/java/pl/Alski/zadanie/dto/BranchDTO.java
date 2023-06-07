package pl.Alski.zadanie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record BranchDTO(
        String name,
        String lastCommitSha) {
}
