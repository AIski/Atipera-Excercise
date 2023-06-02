package pl.Alski.zadanie.entity;

import lombok.Data;

import java.util.List;

@Data
public class Repository {
    private String name;
    private boolean fork;
    private Owner owner;
    private List<Branch> branches;
}
