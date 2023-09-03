package src.persistance;

import src.domain.entities.Transaction;
import src.domain.entities.Transfer;
import src.domain.entities.Withdrawal;
import src.domain.entities.Replenishment;

public interface TransactionDAO {
    boolean insert(Transfer transaction);
    boolean insert(Withdrawal transaction);
    boolean insert(Replenishment transaction);
    Transaction getById(int id);
    boolean updateById(Transfer transaction);
    boolean updateById(Withdrawal transaction);
    boolean updateById(Replenishment transaction);
    boolean deleteById(int id);
}
