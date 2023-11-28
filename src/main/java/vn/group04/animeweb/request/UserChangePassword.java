package vn.group04.animeweb.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePassword {
    private String userName;
    private String currentPassword;
    private String newPassword;
}
