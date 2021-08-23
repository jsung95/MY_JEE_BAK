package org.zerock.myweb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myweb.domain.EmpDTO;
import org.zerock.myweb.domain.EmpVO;

public class EmpDAO {
	
	private static DataSource ds;
	
	static {
		Context ctx = null;
		
		try {
			
			ctx = new InitialContext();
			
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloud");
			ds = (DataSource) obj;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}//static initializer
	
	
	
	public void insertBoard(EmpDTO dto) throws SQLException {
		
		String sql = "INSERT INTO board(num, author, title, content) VALUES(board_seq.NEXTVAL, ?, ?, ?)";
		
		
		
		try {

			Connection conn = EmpDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAuthor());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			
			pstmt.executeUpdate();
			
			try(conn; pstmt;) {
				
			}//try
			
			
		} catch (Exception e) {
			throw e;
		}//try-catch
		
		
	}//insertBoard
	
	public List<EmpVO> selectSQL(EmpDTO dto) throws SQLException{
		
		String sql = "SELECT num, title, content, author, writeday FROM board WHERE author = ?";
		List<EmpVO> board = null;
		
		try {
			
			
			
			Connection conn = EmpDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getAuthor());
			
			ResultSet rs = pstmt.executeQuery();
			
			
			
			try(conn; pstmt; rs;){
				
				board = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String author = rs.getString("author");
					String title = rs.getString("title");
					String content = rs.getString("content");
					Timestamp writeday = rs.getTimestamp("writeday");
					
					EmpVO vo = new EmpVO(num, author, title, content, writeday);
					board.add(vo);
				}
				
				
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return board;
		
	}
	
}
