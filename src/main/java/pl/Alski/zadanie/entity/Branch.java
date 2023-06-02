package pl.Alski.zadanie.entity;

import lombok.Data;

@Data
public class Branch {
    private String name;
    private Commit commit;
}
