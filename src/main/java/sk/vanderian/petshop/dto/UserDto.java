package sk.vanderian.petshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

    @NotNull
    @NotEmpty
    @Schema(example = "foo")
    @Length(max = 255)
    private String username;

    @NotNull
    @NotEmpty
    @Schema(example = "foo@email.com")
    @Email
    @Length(max = 255)
    private String email;

    @NotNull
    @NotEmpty
    @Schema(example = "barbar")
    @Length(min = 6)
    private String password;
}
