package com.anhntv.ecom.dto;

import com.anhntv.ecom.constants.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
