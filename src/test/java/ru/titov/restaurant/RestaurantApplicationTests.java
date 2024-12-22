package ru.titov.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.titov.restaurant.controller.HomeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HomeController.class)
class RestaurantApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testHomePageStatus() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk());
	}

	@Test
	public void testHomePageTitle() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Добро пожаловать!")));
	}

	@Test
	public void testHomePageMenuLinks() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Главная")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Меню")))
				.andExpect(content().string(org.hamcrest.Matchers.containsString("Отзывы")));
	}



}
