package com.api.authbase;

import com.api.authbase.configuration.KeyCloakAdminClient;
import com.api.authbase.repository.UserAuthRepository;
import com.api.authbase.repository.entity.UserAuth;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@DisplayName("Testes de integração/componentes authbase")
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class AuthbaseIT {


	@Autowired
	private KeyCloakAdminClient keyCloakAdminClient;

	@Autowired
	private UserAuthRepository userAuthRepository;


	@Test
	@DisplayName("Exemplo para Testes de componentes, padrão de nomenclatura")
	public void getMethodTest_whenDo_expectedResult() {
		Optional<UserAuth> userFound = userAuthRepository.findById(UUID.fromString("8c6e6a56-8ef4-447d-b48b-c5ac6522e6f9"));

		ResponseEntity<String> user = keyCloakAdminClient.sendEmailVerifyToUser(userFound.get().extractKeyFromUserProviderUrl().toString(),
				"eselwer-admin", "http://localhost:3000/teste");
		Assert.assertEquals(user.getStatusCode().value(),204);
		Assert.assertTrue(true);
	}


}