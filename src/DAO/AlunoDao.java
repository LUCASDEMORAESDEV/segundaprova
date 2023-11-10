/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.AlunoDTO;
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
public class AlunoDao {
     Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<AlunoDTO> lista = new ArrayList<>();

    public void cadastrarAluno(AlunoDTO objaluno) {
        String sql = "insert into Aluno (nome,idade) values (?,?)";
            
        conn = new ConexaoDao().conectaBD();

        try {
         
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objaluno.getNome());
            pstm.setString(2, objaluno.getIdade());
       
           

            pstm.execute();
            pstm.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ALUNODAO" + erro);
        }
    }

 public ArrayList<AlunoDTO> PesquisarAluno() {
        String sql = "select * from Aluno";
         conn = new ConexaoDao().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                AlunoDTO objaluno = new AlunoDTO();
                objaluno.setId(rs.getInt("id"));
                objaluno.setNome(rs.getString("nome"));
                objaluno.setIdade(rs.getString("idade"));
               
                    
                

                lista.add(objaluno);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "alunoDAO Pesquisar" + erro);

        }
        return lista;
    }
public void alterarAluno(AlunoDTO objaluno) {
        String sql = "update Aluno set nome = ?, idade = ? where id = ?";

        conn = new ConexaoDao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(3, objaluno.getId());
            pstm.setString(1, objaluno.getNome());
            pstm.setString(2, objaluno.getIdade());
           
            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO alterar" + erro);
        }
}
    
    public void Excluir(AlunoDTO objaluno){
        String sql = "delete from Aluno where id = ?";
       
    conn = new ConexaoDao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            
           
            pstm.setInt(1, objaluno.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "AlunoDAO excluir" + erro);
        }
    }
}
