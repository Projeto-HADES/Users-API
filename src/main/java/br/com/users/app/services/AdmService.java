package br.com.users.app.services;

import br.com.users.app.dto.AdmDto;
import br.com.users.app.entity.AdmEntity;
import br.com.users.app.exceptions.CustomException;
import br.com.users.app.mappers.AdmMapper;
import br.com.users.app.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmService {
    private final AdmMapper admMapper;
    private final AdmRepository admRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AdmDto createAdm(AdmDto admDto) {
        admRepository.findByEmail(admDto.email()).ifPresent(admEntity -> {
            throw new CustomException("Email ja cadastrado no Sistema", HttpStatus.BAD_REQUEST, null);
        });
        String encryptedPassword = passwordEncoder.encode(admDto.password());
        AdmEntity newAdm = admMapper.toModel(admDto);
        newAdm.setPassword(encryptedPassword);
        admRepository.save(newAdm);
        return admMapper.toDto(newAdm);
    }

    public List<AdmDto> findAllAdm() {
        List<AdmEntity> adms = admRepository.findAll();
        if (adms.isEmpty()) {
            throw new CustomException("Nenhum Adm encontrado", HttpStatus.NOT_FOUND, null);
        }
        return admMapper.toDoList(adms);
    }
}
