package com.api.authbase.listener;

import com.api.authbase.domain.dto.UserAccountCreatedDTO;
import com.api.authbase.service.AdminProviderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@AllArgsConstructor
public class UserCreatedEventListener implements EventListener<UserAccountCreatedDTO>{

    private AdminProviderService service;

    @Override
    public void processEvent(UserAccountCreatedDTO userCreatedDTO) {
        System.out.println("userCreatedDTO "+userCreatedDTO.toString());
        service.registerUserAuth(userCreatedDTO);
    }
}
