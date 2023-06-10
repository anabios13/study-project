package by.anabios13.authorizationService.dto;

public class AuthResponseDTO {
    private  String message;
    private  String role;

    AuthResponseDTO(){}

    public AuthResponseDTO(String message, String role) {
        this.message = message;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
