package org.zerock.myapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.zerock.myapp.domain.BoardDTO;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.domain.PageTO;
import org.zerock.myapp.domain.SearchDTO;

import lombok.extern.log4j.Log4j2;
@Log4j2
public class BoardDAO {
	private static DataSource ds;
	static {
		Context ctx = null;
		try {
			ctx = new InitialContext();
			Object obj = ctx.lookup("java:comp/env/jdbc/OracleCloud");
			BoardDAO.ds = (DataSource) obj;
			
		} catch (Exception e) {
			
		} finally {
			if(ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}//try-catch
				
			}//if
			
		} //try-catch-finally
		
	}//static initializer
	
	public List<BoardVO> list() throws SQLException {
		
		String sql = "SELECT num, author, title, to_char(writeday, 'YYYY/MM/DD') AS writeday, readcnt, repRoot, repStep, repIndent FROM board ORDER BY repRoot DESC, repStep ASC";
		List<BoardVO> list = null;
		try {
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;){
				
				list = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String author = rs.getString("author");
					String title = rs.getString("title");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					int repRoot = rs.getInt("repRoot");
					int repStep = rs.getInt("repStep");
					int repIndent = rs.getInt("repIndent");
					
					BoardVO vo = new BoardVO(num, author, title, null, readcnt, writeday, repRoot, repStep, repIndent);
					list.add(vo);
				}
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return list;
	}
	
	
	public void write(BoardDTO dto) throws SQLException {
		String sql = "INSERT INTO board(num, title, author, content, repRoot, repStep, repIndent) VALUES(board_seq.NEXTVAL, ?, ?, ?, board_seq.CURRVAL, 0, 0)";
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getContent());
			
			int n = pstmt.executeUpdate();
			
			try(conn; pstmt;) {
				
				if(n > 0) { 
					log.info("insert success");
				} else {
					log.info("insert failed");
				}
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		
	}//write
	
	
	
	public List<BoardVO> readRecent() throws SQLException {
		String sql = "SELECT * FROM board WHERE ROWNUM = 1 ORDER BY num DESC";
		List<BoardVO> list = null;
		
		try {
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			
			try(conn; pstmt;) {
				
				list = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String author = rs.getString("author");
					String title = rs.getString("title");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					
					BoardVO vo = new BoardVO(num, author, title, null, readcnt, writeday, null, null, null);
					list.add(vo);
				}
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
	
	
	
	
	public void readCount(BoardDTO dto) throws SQLException {
		
//		String sql = "UPDATE board SET readcnt = readcnt + 1 WHERE num =" + Integer.toString(dto.getNum());
		String sql = "UPDATE board SET readcnt = readcnt + 1 WHERE num = ?";
		
		try {
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			pstmt.executeUpdate();
			
			
			try(conn; pstmt;) {
				
			}//try-with-resources
			
		} catch (SQLException e) {
			throw e;
		}//try-catch
		
	}//조회수 readCount()
	
	
	
	public List<BoardVO> retrieve(BoardDTO dto) throws SQLException {
		
		String sql = "SELECT * FROM board WHERE num = ?";
		
		List<BoardVO> list = null;
		
		readCount(dto);
		
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;) {
				
				list = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String content = rs.getString("content");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					
					BoardVO vo = new BoardVO(num, author, title, content, readcnt, writeday, null, null, null);
					list.add(vo);
				}//while
				
				
			}//try-with-resources
			
			
			
		} catch (SQLException e) {
			throw e;
		}//try-catch
		
		
		return list;
		
	}//retrieve 게시글 내용 조회
	
	
	
	public void update(BoardDTO dto) throws SQLException{
		
		String sql = "UPDATE board SET title = ?, author = ?, content = ? WHERE num = ?";
		
		
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNum());
			
			pstmt.executeUpdate();
			
			try(conn; pstmt;) {
				
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
	} //update 게시글 수정 
	
	public void delete(BoardDTO dto) throws SQLException {
		String sql = "DELETE FROM board WHERE num = ?";
		
		try {

			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			
			pstmt.executeUpdate();
			
			try(conn; pstmt;) {
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
	}//delete 게시글 삭제 
	
	public List<BoardVO> search(SearchDTO s_dto) throws SQLException {
		
		List<BoardVO> list = null;
		
		String sql = "SELECT * FROM board";
		int temp = 0;
		
		if(s_dto.getSearchName().equals("title")) {
			sql = sql + " WHERE title LIKE ?";
		} 
		else if(s_dto.getSearchName().equals("author")) {
			sql = sql + " WHERE author LIKE ?";
		}
		else if(s_dto.getSearchName().equals("author_title")) {
			sql = sql + " WHERE title LIKE ? OR author LIKE ?";
			temp = 1;
		}
		
		try {
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			if(temp == 0) {
				pstmt.setString(1, "%"+s_dto.getSearchValue()+"%");
			} else {
				pstmt.setString(1, "%"+s_dto.getSearchValue()+"%");
				pstmt.setString(2, "%"+s_dto.getSearchValue()+"%");
			}
			
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;) {
				
				list = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String author = rs.getString("author");
					String title = rs.getString("title");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					
					BoardVO vo = new BoardVO(num, author, title, null, readcnt, writeday, null, null, null);
					list.add(vo);
				}
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		
		return list;
	}//search 게시글검색 
	
	public List<BoardVO> replyui(BoardDTO dto) throws SQLException {
		
		String sql = "SELECT * FROM board WHERE num = ?";
		
		List<BoardVO> list = null;
		
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNum());
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt; rs;) {
				
				list = new ArrayList<>();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String title = rs.getString("title");
					String author = rs.getString("author");
					String content = rs.getString("content");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					int repRoot = rs.getInt("repRoot");
					int repStep = rs.getInt("repStep");
					int repIndent = rs.getInt("repIndent");
					
					BoardVO vo = new BoardVO(num, author, title, content, readcnt, writeday, repRoot, repStep, repIndent);
					list.add(vo);
				}
				
			}
			
			
		} catch (SQLException e) {
			throw e;
		}
		
		
		return list;
	}//replyui 
	
	
	public void makeReply(BoardDTO dto) throws SQLException {
		
		String sql = "UPDATE board SET repStep = repStep + 1 WHERE repRoot = ? AND repStep > ?";
		
		
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getRepRoot());
			pstmt.setInt(2, dto.getRepStep());
			
			pstmt.executeUpdate();
			System.out.println("\t+@@>> repRoot : " + dto.getRepRoot());
			System.out.println("\t+@@>> repStep : " + dto.getRepStep());
			try(conn; pstmt;) {
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
	}//makeReply 답변글을위한 repStep + 1 증가 
	
	public void reply(BoardDTO dto) throws SQLException {
		
		String sql = "INSERT INTO board(num, title, author, content, repRoot, repStep, repIndent) VALUES(board_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
		makeReply(dto);
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getAuthor());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getRepRoot());
			pstmt.setInt(5, dto.getRepStep() + 1);
			pstmt.setInt(6, dto.getRepIndent() + 1);
			
			pstmt.executeUpdate();
			
			try(conn; pstmt;) {
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
	}//reply 답변글달기 
	
	
	public int totalCount() throws SQLException {
		
		String sql = "SELECT count(*) FROM board";
		int count = 0;
		
		try {
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			try(conn; pstmt;) {
				if(rs.next()) {
					count = rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return count;
	} //페이징 전체 레코드 수 구하기 
	
	
	public PageTO page(int curPage) throws SQLException {
		
		String sql = "SELECT num, author, title, content, to_char(writeday, 'YYYY/MM/DD') AS writeday, readcnt, repRoot, repStep, repIndent "
				+ "FROM board "
				+ "ORDER BY repRoot DESC, repStep ASC";
		
		PageTO to = new PageTO();
		int totalCount = totalCount();
		log.info("\t+ 리스트 전체 크기 : " + totalCount);
		List<BoardVO> list = null;
		
		try {
			
			Connection conn = BoardDAO.ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = pstmt.executeQuery();
			
			
			try(conn; pstmt; rs;) {
				
				list = new ArrayList<>();
				
				int perPage = to.getPerPage(); //5
				
				int skip = (curPage - 1) * perPage;
				if(skip > 0) {
					rs.absolute(skip);
				}
				
				for(int i=0; i<perPage && rs.next(); i++) {
					int num = rs.getInt("num");
					String author = rs.getString("author"); 
					String title = rs.getString("title");
					String content = rs.getString("content"); 
					String writeday = rs.getString("writeday"); 
					int readcnt = rs.getInt("readcnt");
					int repRoot = rs.getInt("repRoot");
					int repStep = rs.getInt("repStep");
					int repIndent = rs.getInt("repIndent");
					
					BoardVO vo = new BoardVO(num, author, title, content, readcnt, writeday, repRoot, repStep, repIndent);
					list.add(vo);
				}
				
				to.setList(list); // List 저장 
				to.setTotalCount(totalCount); // 전체 레코드 개수 
				to.setCurPage(curPage); // 현재 페이지 
				
				
			}
			
		} catch (SQLException e) {
			throw e;
		}
		
		return to;
	}
	
	
}//end class
