package src.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Withdrawal extends Transaction{
    private Currency amount;
    private Account from;
}
