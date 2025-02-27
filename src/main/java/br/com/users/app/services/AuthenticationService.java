package br.com.users.app.services;

import br.com.users.app.dto.AuthenticationRequestDto;
import br.com.users.app.entity.AdmEntity;
import br.com.users.app.exceptions.CustomException;
import br.com.users.app.repository.AdmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AdmRepository admRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public String authenticate(AuthenticationRequestDto request) {
        AdmEntity admEntity = getAdm(request.email());
        if (!passwordEncoder.matches(request.password(), admEntity.getPassword())) {
            throw new CustomException("E-mail ou Senha Inválidos!", HttpStatus.BAD_REQUEST, null);
        }
        return tokenService.generateTokens(admEntity.getId().toString());
    }

    public AdmEntity getAdm(String email) {
        return admRepository.findByEmail(email).orElseThrow(() -> {
            String errorMessage = "Adm não encontrado para o email: " + email;
            throw new CustomException("Adm não encontrado!", HttpStatus.NOT_FOUND, null);
        });
    }
}
