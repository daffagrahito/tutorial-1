package id.ac.ui.cs.advprog.eshop;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class EshopApplicationTests {

	@MockBean
	private SpringApplication springApplication;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void main() {
		EshopApplication.main(new String[] {});
		assertTrue(true);
	}
}