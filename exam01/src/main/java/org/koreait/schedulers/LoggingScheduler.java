package org.koreait.schedulers;

import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log
public class LoggingScheduler {
	//@Scheduled(cron="0 0 1 * * *")// 새벽 1시 정각에 실행
	@Scheduled(cron="*/5 * * * * *")
	public void logging(){
		log.info("5초마다 실행...");
	}
}
