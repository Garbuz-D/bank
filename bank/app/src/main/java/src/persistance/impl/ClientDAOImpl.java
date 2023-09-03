package src.persistance.impl;

import src.domain.entities.Client;
import src.persistance.ClientDAO;
import src.persistance.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import lombok.Cleanup;

public class ClientDAOImpl implements ClientDAO {
    Connection connection;

    @Override
    public boolean insert(Client client) {
        String query = "INSERT INTO clients VALUES (?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.setString(3, client.getSurname());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to insert client", e);
        }
    }

    @Override
    public Client getById(int id) {
        String query = "SELECT * FROM clients WHERE id = (?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return new Client(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("surname"));
            }
            return null;
        }catch(SQLException e){
            throw new DAOException("Failed to get client by id", e);
        }
    }

    @Override
    public boolean updateById(Client client) {
         String query = "UPDATE clients SET name = ? surname = ? WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setInt(3, client.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to update client by id", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                return false;
            }
            return true;
        }catch(SQLException e){
            throw new DAOException("Failed to delete client by id", e);
        }
    }
    
}
