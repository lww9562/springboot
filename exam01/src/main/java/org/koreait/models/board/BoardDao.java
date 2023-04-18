package org.koreait.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardDao {
	private final JdbcTemplate jdbcTemplate;

	//게시글 추가
	public boolean insert(Board board){
		String sql = "INSERT INTO BOARD (ID, SUBJECT, CONTENT) " + " VALUES (SEQ_BOARD.nextval, ?, ?)";
		int cnt = jdbcTemplate.update(sql, board.getSubject(), board.getContent());

		return cnt > 0;
	}

	//게시글 조회
	public Board get(Long id){
		try {
			String sql = "SELECT * FROM BOARD WHERE ID = ?";
			Board board = jdbcTemplate.queryForObject(sql, this::boardMapper, id);

			return board;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	//게시글 목록 조회
	public List<Board> gets(){
		String sql = "SELECT * FROM BOARD ORDER BY REGDT DESC";
		List<Board> list = jdbcTemplate.query(sql, this::boardMapper);

		return list;
	}

	private Board boardMapper(ResultSet rs, int i) throws SQLException {
		Board board = new Board();
		board.setId(rs.getLong("ID"));
		board.setSubject(rs.getString("SUBJECT"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());
		Timestamp modDt = rs.getTimestamp("MODDT");
		if(modDt != null){
			board.setModDt(modDt.toLocalDateTime());
		}

		return board;
	}
}
