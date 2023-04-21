package org.koreait.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
//@IdClass(Category.class)
public class Category {
	@Id
	private String cateCd;
	@Id
	private String subCateCd;
	private String cateNm;
}
