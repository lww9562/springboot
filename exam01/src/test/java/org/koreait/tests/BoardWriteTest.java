package org.koreait.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardWriteTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("게시글 작성 성공시 /board/list로 이동")
	public void writeSuccessTest() throws Exception {
		mockMvc.perform(post("/board/write")
							.param("subject", "제목2")
							.param("content", "내용2"))
						.andDo(print())
						.andExpect(redirectedUrl("/board/list"));
	}

	@Test
	@DisplayName("제목, 내용 필수 체크")
	public void validationTest1() throws Exception {
		String body = mockMvc.perform(post("/board/write"))
						//.andDo(print())
						.andReturn().getResponse().getContentAsString();

		//제목 유효성 검사 체크
		assertTrue(body.contains("제목을 입력하세요."));

		//내용 유효성 검사 체크
		assertTrue(body.contains("내용을 입력하세요."));
	}
}
