package src.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Replenishment extends Transaction {
    private Currency amount;
    private Account to;
}
