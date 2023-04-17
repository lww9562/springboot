package org.koreait.boardtest.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean insert(BoardForm boardForm){
		String sql = "INSERT INTO BOARD (ID, SUBJECT, CONTENT) "
				+ "VALUES (SEQ_BOARDTEST.nextval, ?, ?)";
		int cnt = jdbcTemplate.update(sql, boardForm.getSubject(), boardForm.getContent());
		return cnt > 0;
	}

	public Board get(Long id){
		String sql = "SELECT * FROM BOARD WHERE ID = ?";
		Board board = jdbcTemplate.queryForObject(sql, this::BoardMapper, id);

		return board;
	}

	public List<Board> getList(){
		String sql = "SELECT * FROM BOARD ORDER BY ID DESC";
		List<Board> boards = new ArrayList<>();
		boards = (jdbcTemplate.query(sql, this::BoardMapper));

		return boards;
	}

	public boolean delete(Long id){
		String sql = "DELETE FROM BOARD WHERE ID = ?";

		int cnt = jdbcTemplate.update(sql, id);

		return cnt > 0;
	}


	private Board BoardMapper(ResultSet rs, int i) throws SQLException {
		Board board = new Board();
		board.setId(rs.getLong("ID"));
		board.setSubject(rs.getString("SUBJECT"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDt(rs.getTimestamp("REGDT").toLocalDateTime());

		return board;
	}
}
