package com.bootcamp_2024_2.emazon;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmazonApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllCategoriesSortedInAscendingOrder() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/categories/v1/categories")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"sortBy\": \"name\", \"order\": \"asc\"}"))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		JsonPath jsonPath = JsonPath.compile("$.content[*].name");
		List<String> categoryNames = jsonPath.read(responseBody);

		List<String> sortedCategoryNames = new ArrayList<>(categoryNames);
		Collections.sort(sortedCategoryNames);
		assertEquals(sortedCategoryNames, categoryNames);
	}

	@Test
	void getAllCategoriesSortedInDescendingOrder() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/categories/v1/categories")
						.param("sortBy", "name")
						.param("order", "desc")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		String responseBody = result.getResponse().getContentAsString();

		JsonPath jsonPath = JsonPath.compile("$.content[*].name");
		List<String> categoryNames = jsonPath.read(responseBody);

		List<String> sortedCategoryNames = new ArrayList<>(categoryNames);
		sortedCategoryNames.sort(Collections.reverseOrder());

		assertEquals(sortedCategoryNames, categoryNames);
	}

	@Test
	void createCategory_success() throws Exception {
		String uniqueName = "Toys & Games " + UUID.randomUUID();

		String categoryJson = String.format("""
            {
                "name": "%s",
                "description": "Fun for all ages with our toys and games."
            }
            """, uniqueName);

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value(uniqueName))
				.andExpect(jsonPath("$.description").value("Fun for all ages with our toys and games."));
	}

	@Test
	void createCategory_withDuplicateName_shouldReturnInternalServerError() throws Exception {
		String uniqueName = "Unique" + UUID.randomUUID();

		String categoryJson = String.format("""
            {
                "name": "%s",
                "description": "Fun for all ages with our toys and games."
            }
            """, uniqueName);

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isCreated());

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isInternalServerError())
				.andExpect(jsonPath("$.message").value("An unexpected error occurred."));
	}

	@Test
	void createCategory_withoutDescription_shouldReturnBadRequest() throws Exception {
		String categoryJson = """
            {
                "name": "CategoryWithoutDescription"
            }
            """;

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details").value("Field description cannot be empty or null"));
	}

	@Test
	void createCategory_withNameExceedingMaxLength_shouldReturnBadRequest() throws Exception {
		String longName = "n".repeat(51);
		String categoryJson = String.format("""
            {
                "name": "%s",
                "description": "Valid description for a category."
            }
            """, longName);

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details").value("Name must be less than or equal to 50 characters"));
	}

	@Test
	void createCategory_withDescriptionExceedingMaxLength_shouldReturnBadRequest() throws Exception {
		String longDescription = "d".repeat(91);
		String categoryJson = String.format("""
            {
                "name": "ValidCategoryName",
                "description": "%s"
            }
            """, longDescription);

		mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
						.contentType(MediaType.APPLICATION_JSON)
						.content(categoryJson))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.details").value("Description must be less than or equal to 90 characters"));
	}

	@Test
	void getCategories_shouldReturnPaginatedResults() throws Exception {
		MvcResult initialResult = mockMvc.perform(MockMvcRequestBuilders.get("/categories/v1/categories")
						.param("page", "0")
						.param("size", "1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		String initialResponseBody = initialResult.getResponse().getContentAsString();
		int initialTotalElements = JsonPath.read(initialResponseBody, "$.totalElements");
		int initialTotalPages = JsonPath.read(initialResponseBody, "$.totalPages");

		String uniqueName = "Category" + UUID.randomUUID();
		for (int i = 0; i < 10; i++) {
			String categoryJson = String.format("""
            {
                "name": "%s %d",
                "description": "Description for category %d."
            }
            """, uniqueName, i, i);

			mockMvc.perform(MockMvcRequestBuilders.post("/categories/v1/createCategory")
							.contentType(MediaType.APPLICATION_JSON)
							.content(categoryJson))
					.andExpect(status().isCreated());
		}

		int expectedTotalElements = initialTotalElements + 10;
		int expectedTotalPages = initialTotalPages + 1;

		mockMvc.perform(MockMvcRequestBuilders.get("/categories/v1/categories")
						.param("page", String.valueOf(initialTotalPages))
						.param("size", "10")
						.param("sortBy", "name")
						.param("order", "asc")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.numberOfElements").value(10))
				.andExpect(jsonPath("$.totalElements").value(expectedTotalElements))
				.andExpect(jsonPath("$.totalPages").value(expectedTotalPages))
				.andExpect(jsonPath("$.content.length()").value(10));
	}

}
