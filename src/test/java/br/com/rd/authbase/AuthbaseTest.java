package br.com.rd.authbase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;


@ExtendWith(MockitoExtension.class)
public class AuthbaseTest {
	@Test
	@DisplayName("Exemplo para Testes Unitários, padrão de nomenclatura")
	public void getMethodTest_whenDo_expectedResult() {
		Assertions.assertEquals(true,true);
	}
}