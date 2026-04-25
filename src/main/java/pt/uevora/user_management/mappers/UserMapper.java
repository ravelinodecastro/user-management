package pt.uevora.user_management.mappers;

import pt.uevora.user_management.dto.UserRequestDTO;
import pt.uevora.user_management.dto.UserResponseDTO;
import pt.uevora.user_management.entities.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto) {
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }
}
