package models;

import flyweight.BlockFactory;
import flyweight.FloorFactory;
import flyweight.RoomFactory;
import models.data.BlockDTO;
import models.data.FloorDTO;
import models.data.RoomDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAODorms {
    public int getNbOfStudents(int roomNb, int floorNb, String blockName)throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nbOfStudents =0;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT `numberOfStudents` FROM `dorms-registration`.`rooms` WHERE `roomNb`=? and `floorNb`=? and `blockName`=?");
            pstmt.setInt(1, roomNb);pstmt.setInt(2, floorNb);pstmt.setString(3, blockName);
            rs = pstmt.executeQuery();
            if(rs.next()){
                nbOfStudents = rs.getInt("numberOfStudents");
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
        
        return nbOfStudents;
    }
    
    public void getBlocks(String gender) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BlockDTO block = null;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT `blockName`,`isFull` FROM `dorms-registration`.`blocks` WHERE gender=?");
            pstmt.setString(1, gender);
            rs = pstmt.executeQuery();
            while(rs.next()){
                block = BlockFactory.getBlock(rs.getString("blockName"));
                block.setIsFull(rs.getString("isFull"));
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    public void getFloors(String blockName) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FloorDTO floor = null;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT `floorNb`,`isFull` FROM `dorms-registration`.`floors` WHERE blockName=?");
            pstmt.setString(1, blockName);
            rs = pstmt.executeQuery();
            while(rs.next()){
                floor = FloorFactory.getFloor(rs.getInt(1));
                floor.setIsFull(rs.getString(2));
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
    }
    
    public void getRooms(String blockName, int floorNb) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        RoomDTO room = null;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT `roomNb`,`numberOfStudents` FROM (select * from `dorms-registration`.`rooms` WHERE blockName=?)rooms where floorNb =?");
            pstmt.setString(1, blockName);pstmt.setInt(2, floorNb);
            rs = pstmt.executeQuery();
            while(rs.next()){
                room = RoomFactory.getRoom(rs.getInt(1));
                room.setNbOfStudents(rs.getInt(2));
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
    }
}
