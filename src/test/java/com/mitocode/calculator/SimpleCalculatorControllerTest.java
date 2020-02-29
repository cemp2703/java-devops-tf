package com.mitocode.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@WebMvcTest(SimpleCalculatorController.class)
class SimpleCalculatorControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	void addTest() throws Exception {
		int num1 = 6;
		int num2 = 4;
		int res = 10;
		String expectedString = num1 + " + " + num2 + " = " + res;
		MvcResult mvcResult = mvc
				.perform(get("/api/add/{num1}/{num2}", num1, num2).contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		assertEquals(expectedString, responseString);
	}

	@Test
	void subTest() throws Exception {
		int num1 = 3;
		int num2 = 1;
		int res = 2;
		String expectedString = num1 + " - " + num2 + " = " + res;
		MvcResult mvcResult = mvc
				.perform(get("/api/sub/{num1}/{num2}", num1, num2).contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		assertEquals(expectedString, responseString);
	}

	@Test
	void mulTest() throws Exception {
		int num1 = 4;
		int num2 = 4;
		int res = 16;
		String expectedString = num1 + " x " + num2 + " = " + res;
		MvcResult mvcResult = mvc
				.perform(get("/api/mul/{num1}/{num2}", num1, num2).contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		assertEquals(expectedString, responseString);
	}

	@Test
	void divTest() throws Exception {
		int num1 = 9;
		int num2 = 3;
		int res = 3;
		String expectedString = num1 + " / " + num2 + " = " + res;
		MvcResult mvcResult = mvc.perform(get("/api/div/{num1}/{num2}",num1,num2).contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isOk()).andReturn();
		String responseString = mvcResult.getResponse().getContentAsString();
		assertEquals(expectedString, responseString);
	}
	
}
