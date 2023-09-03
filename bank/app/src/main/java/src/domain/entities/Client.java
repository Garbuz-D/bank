package src.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Client {
    private int id;
    private String name; 
    private String surname;
}