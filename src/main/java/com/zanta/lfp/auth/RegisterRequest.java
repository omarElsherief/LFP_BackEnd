package com.zanta.lfp.auth;

import com.zanta.lfp.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private  String firstName;
    private  String lastName;
    private  String username;
    private  String email;
    private  String password;
    private Gender gender;
}
