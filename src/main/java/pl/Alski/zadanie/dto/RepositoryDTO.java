package pl.Alski.zadanie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryDTO {
    private String name;
    private String ownerLogin;
    private List<BranchDTO> branches;
}
