package pl.Alski.zadanie.entity;

import java.util.List;

public record Repository(
        String name,
        boolean fork,
        Owner owner,
        List<Branch> branches
) {

}
