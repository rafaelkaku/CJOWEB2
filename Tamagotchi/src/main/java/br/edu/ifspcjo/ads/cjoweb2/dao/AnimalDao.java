package br.edu.ifspcjo.ads.cjoweb2.dao;

import java.sql.Connection;
import java.sql.Date; // <-- Importação adicionada para java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import br.edu.ifspcjo.ads.cjoweb2.model.Animal;
import br.edu.ifspcjo.ads.cjoweb2.model.User;

public class AnimalDao {

    private DataSource dataSource;

    public AnimalDao(DataSource dataSource) { 
        this.dataSource = dataSource; 
    }

    public Boolean save(Animal a) {
        // CORREÇÃO LÓGICA: Alterada a coluna 'age' para 'date_of_birth' no SQL
        String sql = "INSERT INTO animal (name, species, birth_date, description, status, user_id) VALUES (?,?,?,?,?,?)";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { 
            
            ps.setString(1, a.getName());
            ps.setString(2, a.getSpecies());
            ps.setDate(3, Date.valueOf(a.getDateOfBirth()));
            
            ps.setString(4, a.getDescription());
            ps.setString(5, a.getStatus());
            ps.setLong(6, a.getUserId()); 
            ps.executeUpdate();
            // Recupera o ID gerado e o define no objeto Animal
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    a.setId(rs.getLong(1));
                }
            }
            return true;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar animal", e);
        }
    }
    public Animal getAnimalById(Long id) {
        String sql = "SELECT id, name, species, birth_date, description, status, user_id FROM animal WHERE id = ?";
        Animal animal = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { 
            
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    animal = new Animal();
                    animal.setId(rs.getLong("id"));
                    animal.setName(rs.getString("name"));
                    animal.setSpecies(rs.getString("species"));
                    
                    // Converte java.sql.Date de volta para java.time.LocalDate
                    Date sqlDate = rs.getDate("birth_date");
                    if (sqlDate != null) {
                        animal.setDateOfBirth(sqlDate.toLocalDate());
                    }
                    
                    animal.setDescription(rs.getString("description"));
                    animal.setStatus(rs.getString("status"));
                    animal.setUserId(rs.getLong("user_id"));
                }
            }
            return animal;
            
        } catch (SQLException e) {
            // Segue o padrão do projeto de relançar como RuntimeException
            throw new RuntimeException("Erro ao buscar animal por ID", e);
        }
    }
 // O código abaixo deve ser adicionado à sua classe AnimalDao

    public Boolean update(Animal a) {
        // SQL para atualizar todos os campos que podem ser modificados, usando o ID como chave
        String sql = "UPDATE animal SET name=?, species=?, birth_date=?, description=?, status=?, user_id=? WHERE id=?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) { 
            
            // 1. Vinculação dos novos valores
            ps.setString(1, a.getName());
            ps.setString(2, a.getSpecies());
            ps.setDate(3, Date.valueOf(a.getDateOfBirth()));
            ps.setString(4, a.getDescription());
            ps.setString(5, a.getStatus());
            ps.setLong(6, a.getUserId()); // Mantém o user_id, mas é bom vinculá-lo
            
            // 2. Vinculação da chave (WHERE)
            ps.setLong(7, a.getId()); 
            
            int rowsAffected = ps.executeUpdate();
            
            // Retorna true se pelo menos uma linha foi afetada (atualizada)
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            // Segue o padrão do projeto de relançar como RuntimeException
            throw new RuntimeException("Erro ao atualizar animal", e);
        }
    }
    public List<Animal> getAnimalsByUser(User user) {
        String sql = "SELECT id, name, species, birth_date, description, status, user_id FROM animal WHERE user_id = ?";
        List<Animal> list = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setLong(1, user.getId());
            
            try (ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    Animal a = new Animal();
                    a.setId(rs.getLong("id"));
                    a.setName(rs.getString("name"));
                    a.setSpecies(rs.getString("species"));
                    
                    // Mapeamento de Date para LocalDate
                    Date sqlDate = rs.getDate("birth_date");
                    if (sqlDate != null) {
                        a.setDateOfBirth(sqlDate.toLocalDate());
                    }
                    
                    a.setDescription(rs.getString("description"));
                    a.setStatus(rs.getString("status"));
                    a.setUserId(rs.getLong("user_id"));
                    list.add(a);
                }
            }
            return list;
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais por usuário", e);
        }
    }
    
    
    // Método delete mantido por referência, embora não tenha sido o foco da correção
    public void delete(Long id) {
        String sql = "DELETE FROM animal WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar animal", e);
        }
    }
    
}