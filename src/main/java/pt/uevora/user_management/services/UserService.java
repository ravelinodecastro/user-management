package pt.uevora.user_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.uevora.user_management.dto.UserRequestDTO;
import pt.uevora.user_management.dto.UserResponseDTO;
import pt.uevora.user_management.entities.User;
import pt.uevora.user_management.exeptions.UserNotFoundException;
import pt.uevora.user_management.mappers.UserMapper;
import pt.uevora.user_management.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseDTO create(UserRequestDTO dto) {
        User user = UserMapper.toEntity(dto);
        return UserMapper.toDTO(repository.save(user));
    }

    public List<UserResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserResponseDTO getOne(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserMapper.toDTO(user);
    }

    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setEmail(dto.getEmail());
        user.setName(dto.getName());

        return UserMapper.toDTO(repository.save(user));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
