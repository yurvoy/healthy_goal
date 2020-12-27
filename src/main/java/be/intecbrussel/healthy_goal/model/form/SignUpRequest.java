package be.intecbrussel.healthy_goal.model.form;

import lombok.Data;

@Data
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
}
