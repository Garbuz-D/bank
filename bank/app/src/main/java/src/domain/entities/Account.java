package src.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Account{
    private int id;
    private Bank bank;
    private Client client; 
    private Currency balance;
}