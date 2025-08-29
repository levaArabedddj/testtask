package org.example.testtask.Config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SigninRequest {
    private String gmail;
    private String password;
}
