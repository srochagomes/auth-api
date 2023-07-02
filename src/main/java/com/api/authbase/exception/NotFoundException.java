package com.api.authbase.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper=false)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private final HttpStatus httpStatusCode;

    @Builder.Default
    private final String code = "404";

    @Builder.Default
    private final String message = "Não encontrado o recurso";

    @Builder.Default
    private final String description = "Não foi possivel localizar o recurso.";

    public BusinessExceptionBody getOnlyBody() {
        return BusinessExceptionBody.builder()
                .code(Optional.ofNullable(this.code).orElseGet(()->this.httpStatusCode.toString()))
                .message(this.message)
                .description(this.description)
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class BusinessExceptionBody {
        private String code;

        private String message;

        private String description;

    }
}
