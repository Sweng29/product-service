package com.microsevices.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsevices.io.dto.ProductCreateRequest;
import com.microsevices.io.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static com.mongodb.assertions.Assertions.assertTrue;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ProductService productRepository;

	private static final MongoDBContainer mongoDbContainer =
			new MongoDBContainer("mongo:4.4.2");

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		mongoDbContainer.start();
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
	}

	@Test
	void givenProductCreateRequest_shouldSaveProductDetails() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.post(
						"/v1/api/products"
				).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(getProductCreateRequest()))
		).andExpect(MockMvcResultMatchers.status().isOk());
		assertTrue(productRepository.findAllProducts().size() == 1);
	}

	private ProductCreateRequest getProductCreateRequest() {
		return ProductCreateRequest.builder()
				.active(Boolean.TRUE)
				.description("Macbook Pro")
				.name("Macbook Pro")
				.unitPrice(BigDecimal.TEN)
				.build();
	}

}
