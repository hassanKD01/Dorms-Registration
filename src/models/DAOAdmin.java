package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class DAOAdmin {
        
    public boolean verifyAdmin(String username,String password) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean result = false;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(" SELECT * FROM `dorms-registration`.`admin`");
            rs = pstmt.executeQuery();
            while (rs.next()){
                if(rs.getString(1).compareTo(username)==0 && rs.getString(2).compareTo(password)==0){result = true;}
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
        return result;
    }
}

