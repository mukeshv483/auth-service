package com.myecom.myapp.domain.dto.user;

import com.myecom.myapp.domain.entity.user.Address;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userName;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String password;
    private List<Address> address;

}
