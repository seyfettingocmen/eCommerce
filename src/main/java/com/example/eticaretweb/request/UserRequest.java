package com.example.eticaretweb.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String name;
    private String surname;
    private String userName;
    private int password;
}
