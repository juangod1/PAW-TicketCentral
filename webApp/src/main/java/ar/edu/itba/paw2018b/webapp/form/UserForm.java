package ar.edu.itba.paw2018b.webapp.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "repeatEmail",
                message = "Email addresses do not match!"
        )
})
public class UserForm {

    @Size(min = 6, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+")
    private String username;
    @NotNull
    @Size(min = 8, max = 14)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")
    private String password;
    @NotNull
    @Size(min = 8, max = 14)
    private String repeatPassword;

    @Size(min = 8, max = 8)
    private String dni;

    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @Pattern(regexp = "[a-zA-Z]+[ ]*[a-zA-z]*")
    private String surname;

    @Size(min = 8, max = 20)
    @Pattern(regexp = "[0-9]+")
    private String phone;
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotNull
    @Email
    private String repeatEmail;

    public String getRepeatEmail() {
        return repeatEmail;
    }

    public void setRepeatEmail(String repeatEmail) {
        this.repeatEmail = repeatEmail;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
    public void setRepeatPassword(String repeatPassword)
    {
        this.repeatPassword = repeatPassword;
    }
}
