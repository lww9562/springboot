package org.koreait.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.entities.BoardData;
import org.koreait.entities.QBoardData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardDataRepository extends JpaRepository<BoardData, Long>, QuerydslPredicateExecutor<BoardData> {
	@Query("SELECT b FROM BoardData b LEFT JOIN FETCH b.member WHERE b.subject LIKE %:key%")
	List<BoardData> getBoardData(@Param("key") String keyword);

	default List<BoardData> getBoardData2(String keyword) {
		BooleanBuilder builder = new BooleanBuilder();	// and, or, not 연산자
														// 다른 조건식들을 연결해 사용
		QBoardData boardData = QBoardData.boardData;
		Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("id")));
		//0번째 페이지에서 3개만 조회, 정렬

		builder.and(boardData.subject.contains(keyword))
				.and(boardData.member.userNo.eq(1L));
		Page<BoardData> page = findAll(builder, pageable);

		List<BoardData> datas = page.getContent();
		return datas;
	}
}
