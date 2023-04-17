package org.koreait.boardtest.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.boardtest.models.board.Board;
import org.koreait.boardtest.models.board.BoardDao;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Log
@RequiredArgsConstructor
public class LoggingScheduler {
	private final BoardDao boardDao;
	private int[] time_stat = new int[24];

	@Scheduled(cron = "0 0 1 * * *")
	public void getTimeStatistics(){
		Arrays.fill(time_stat, 0);

		List<Board> boards = boardDao.getList();
		for(Board board : boards){
			int idx = board.getRegDt().getHour();
			time_stat[idx]++;
		}
		for(int i = 0; i < 24; i++){
			System.out.printf("%d시 ", i);
		}
		System.out.println();
		System.out.println(Arrays.toString(time_stat));
	}
}
