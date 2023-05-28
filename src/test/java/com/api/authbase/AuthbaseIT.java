package com.api.authbase;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DisplayName("Testes de integração/componentes authbase")
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AuthbaseIT {




	@Test
	@DisplayName("Exemplo para Testes de componentes, padrão de nomenclatura")
	public void getMethodTest_whenDo_expectedResult() {

		Assert.assertTrue(true);

	}


}