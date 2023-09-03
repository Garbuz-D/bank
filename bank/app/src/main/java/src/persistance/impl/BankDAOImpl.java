package src.persistance.impl;

import src.domain.entities.Bank;
import src.persistance.BankDAO;
import src.persistance.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;

public class BankDAOImpl implements BankDAO {
    Connection connection;

    @Override
    public boolean insert(Bank bank) {
        String query = "INSERT INTO banks VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, bank.getId());
            preparedStatement.setString(2, bank.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to insert bank", e);
        }
    }

    @Override
    public Bank getById(int id) {
        String query = "SELECT * FROM banks WHERE id = (?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Bank(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return null;
        }catch(SQLException e){
            throw new DAOException("Failed to get bank by id", e);
        }
    }

    @Override
    public boolean updateById(Bank bank) {
         String query = "UPDATE banks SET name = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, bank.getName());
            preparedStatement.setInt(2, bank.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to update bank by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM banks WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to delete bank by id", e);
        }
    }
    
}
