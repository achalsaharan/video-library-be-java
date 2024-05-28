package com.example.videolibrarybe.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int userId;
    private String firstName;
    private String lastName;
    private String emailId;
}
