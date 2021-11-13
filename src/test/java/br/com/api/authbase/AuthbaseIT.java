package br.com.api.authbase;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@DisplayName("Testes de integração/componentes authbase")
@AutoConfigureMockMvc
public class AuthbaseIT {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@DisplayName("Exemplo para Testes de componentes, padrão de nomenclatura")
	public void getMethodTest_whenDo_expectedResult() throws Exception {

		Assert.assertTrue(true);

	}


}