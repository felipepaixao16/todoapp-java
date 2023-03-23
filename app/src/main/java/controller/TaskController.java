package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Task;
import java.util.List;
import util.ConnectionFactory;

public class TaskController {
    
    // salvar as tarefas no banco de dados
    public void save(Task task) {
        
        String sql = "INSERT INTO tasks (idProject, name, description," +
                "completed,"
                + "notes,"
                + "deadline,"
                + "createAt,"
                + "updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getCreateAt().getTime()));
            statement.setDate(7, new Date(task.getCreateAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.execute();    
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE tasks SET" 
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?,"
                + "notes = ?,"
                + "completed = ?,"
                + "deadline = ?,"
                + "createAt = ?,"
                + "updateAt = ?"
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateAt().getTime()));
            statement.setDate(8, new Date(task.getUpdateAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();  
        } catch (Exception ex) { 
            throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);
        }
    }
    
    public void removeById(int taskID) throws SQLException {
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();
            //Preparando a query
            statement = connection.prepareStatement(sql);
            //Setando os valores
            statement.setInt(1, taskID);
            //Executando a query
            statement.execute();
            
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        //Lista de tarefas que será devolvida quando a chamada do método acontecer
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            //Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            //Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
        
            
            /*Enquanto houverem valores a serem percorridos no meu resultSet*/
            while(resultSet.next()){
                
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreateAt(resultSet.getDate("createAt"));
                task.setUpdateAt(resultSet.getDate("updateAt"));
                
                tasks.add(task);
            }
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }
}
