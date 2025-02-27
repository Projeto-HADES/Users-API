package br.com.users.app.services;

import br.com.users.app.dto.UserDto;
import br.com.users.app.entity.AdmEntity;
import br.com.users.app.entity.UserEntity;
import br.com.users.app.exceptions.CustomException;
import br.com.users.app.mappers.UserMapper;
import br.com.users.app.repository.AdmRepository;
import br.com.users.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final AdmRepository admRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto createUser(UserDto userDto) {
        userRepository.findByEmail(userDto.email()).ifPresent(userEntity -> {
            throw new CustomException("Email ja cadastrado no Sistema", HttpStatus.BAD_REQUEST, null);
        });

        AdmEntity admEntity = admRepository.findById(userDto.admId()).orElseThrow(() -> {
           throw new CustomException("Adm: " + userDto.admId() + "não encontrado", HttpStatus.NOT_FOUND, null);
        });

        String encryptedPassword = passwordEncoder.encode(userDto.password());

        UserEntity newUser = userMapper.toModel(userDto);
        newUser.setPassword(encryptedPassword);
        newUser.setAdmId(admEntity);
        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }

    public List<UserDto> findUserAll() {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new CustomException("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND, null);
        }
        return userMapper.toDoList(users);
    }
}
