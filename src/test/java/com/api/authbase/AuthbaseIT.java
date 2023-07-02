package com.api.authbase;

import com.api.authbase.configuration.KeyCloakAdminClient;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DisplayName("Testes de integração/componentes authbase")
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AuthbaseIT {


	@Autowired
	private KeyCloakAdminClient keyCloakAdminClient;

	@Test
	@DisplayName("Exemplo para Testes de componentes, padrão de nomenclatura")
	public void getMethodTest_whenDo_expectedResult() {
//		UserDTO userDTO = UserDTO.builder().username("teste2453").email("testes41@gmail.com").emailVerified(true).enabled(true).build();
//		ResponseEntity<String> user = keyCloakAdminClient.createUser(userDTO);
//		Assert.assertEquals(user.getStatusCode().value(),201);
		Assert.assertTrue(true);

	}


}