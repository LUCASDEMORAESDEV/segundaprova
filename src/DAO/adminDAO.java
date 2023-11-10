
package DAO;

import DTO.AdminDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class adminDAO {
    
    Connection conn;
    
    public ResultSet autenticacaoUsuario(AdminDTO objUsuarioDTO){
       conn = new ConexaoDao().conectaBD();
        
       try {
         String sql = "select * from adminn where nome = ? and senha = ?";
           PreparedStatement pstm = conn.prepareStatement(sql);
           pstm.setString(1, objUsuarioDTO.getUsuario());
           pstm.setString(2, objUsuarioDTO.getSenha());
      
       ResultSet rs = pstm.executeQuery();
       return rs;
       } catch (SQLException erro) {
            JOptionPane.showConfirmDialog(null,"UsuarioDAO" + erro);
            return null;
        
        }
    }
}
