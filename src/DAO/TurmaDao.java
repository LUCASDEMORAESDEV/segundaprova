/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.TurmaDTO;
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
public class TurmaDao {
     Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<TurmaDTO> lista = new ArrayList<>();

    public void cadastrarTurma(TurmaDTO objodto) {
        String sql = "insert into turma (nomeprof,numerosala) values (?,?)";
            
        conn = new ConexaoDao().conectaBD();

        try {
         
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objodto.getProf());
            pstm.setString(2, objodto.getNumero());
           
            
           

            pstm.execute();
            pstm.close();
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO" + erro);
        }
    }

    public ArrayList<TurmaDTO> PesquisarTurma() {
        String sql = "select * from turma";
         conn = new ConexaoDao().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                TurmaDTO objDTO = new TurmaDTO();
                objDTO.setId(rs.getInt("id"));
                objDTO.setProf(rs.getString("nomeprof"));
                objDTO.setNumero(rs.getString("numerosala"));
                
                    
                

                lista.add(objDTO);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionsrioDAO Pesquisar" + erro);

        }
        return lista;
    }

    public void alterarTurma(TurmaDTO objdto) {
        String sql = "update turma set nomeprof = ?, numerosala = ? where id = ?";

        conn = new ConexaoDao().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(3, objdto.getId());
            pstm.setString(1, objdto.getProf());
            pstm.setString(2, objdto.getNumero());
            
            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO alterar" + erro);
        }
    
    
    }
     
    public void Excluir(TurmaDTO objdto){
        String sql = "delete from turma where id = ?";
       
    conn = new ConexaoDao().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            
           
            pstm.setInt(1, objdto.getId());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO excluir" + erro);
        }
    
    }
   
     
   }

