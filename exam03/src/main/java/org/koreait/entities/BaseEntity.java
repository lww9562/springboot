package org.koreait.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	@CreatedDate
	@Column(updatable = false)			//수정(UPDATE) 불가
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime regDt;

	@LastModifiedDate
	@Column(insertable = false)			//추가(INSERT) 불가
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modDt;
}
