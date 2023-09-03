package src.persistance.impl;

import src.domain.entities.Account;
import src.persistance.AccountDAO;
import src.persistance.DAOException;
import src.domain.entities.Currency;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;

public class AccountDAOImpl implements AccountDAO {
    Connection connection;

    @Override
    public boolean insert(Account account) {
        String query = "INSERT INTO accounts VALUES (?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, account.getBank().getId());
            preparedStatement.setInt(3, account.getClient().getId());
            preparedStatement.setInt(4, account.getBalance().getRub());
            preparedStatement.setInt(5, account.getBalance().getKop());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to insert account", e);
        }
    }

    @Override
    public Account getById(int id) {
        String query = "SELECT * FROM accounts WHERE id = (?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Account(resultSet.getInt("id"), resultSet.getInt("bank"), resultSet.getInt("client"), new Currency(resultSet.getInt("rub"), resultSet.getInt("kop")));
            }
            return null;
        }catch(SQLException e){
            throw new DAOException("Failed to get account by id", e);
        }
    }

    @Override
    public boolean updateById(Account account) {
        String query = "UPDATE accounts SET bank = ? client = ? rub = ? kop = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, account.getBank().getId());
            preparedStatement.setInt(2, account.getClient().getId());
            preparedStatement.setInt(3, account.getBalance().getRub());
            preparedStatement.setInt(4, account.getBalance().getKop());
            preparedStatement.setInt(5, account.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to update account by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM accounts WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to delete account by id", e);
        }
    }
    
}
