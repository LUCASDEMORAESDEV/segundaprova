/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AlunoDTO;
import DTO.ProfessorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ltrindade
 */
public class ProfessorDao {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProfessorDTO> lista = new ArrayList<>();

    public void cadastrarProfessor(ProfessorDTO objprof) {
        String sql = "insert into Professor (nome,disciplina) values (?,?)";
            
        conn = new ConexaoDao().conectaBD();

        try {
         
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objprof.getNome());
            pstm.setString(2, objprof.getDisciplina());
       
           

            pstm.execute();
            pstm.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "professorDAO" + erro);
        }
    }

 public ArrayList<ProfessorDTO> PesquisarProfessor() {
        String sql = "select * from Professor";
         conn = new ConexaoDao().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                ProfessorDTO objprof = new ProfessorDTO();
                objprof.setId(rs.getInt("id"));
                objprof.setNome(rs.getString("nome"));
                objprof.setDisciplina(rs.getString("disciplina"));
               
                    
                

                lista.add(objprof);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "alunoDAO Pesquisar" + erro);

        }
        return lista;
    }
public void alterarProfessor(ProfessorDTO objprof) {
        String sql = "update Professor set nome = ?, disciplina = ? where id = ?";

        conn = new ConexaoDao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(3, objprof.getId());
            pstm.setString(1, objprof.getNome());
            pstm.setString(2, objprof.getDisciplina());
           
            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO alterar" + erro);
        }
}
    
    public void Excluir(ProfessorDTO objprof){
        String sql = "delete from Professor where id = ?";
       
    conn = new ConexaoDao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            
           
            pstm.setInt(1, objprof.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "AlunoDAO excluir" + erro);
        }
    }
}
