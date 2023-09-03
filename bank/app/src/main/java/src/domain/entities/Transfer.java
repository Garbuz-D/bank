package src.domain.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Transfer extends Transaction {
    private Currency amount;
    private Account from;
    private Account to;
}
