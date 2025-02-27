package br.com.users.app.controllers;

import br.com.users.app.dto.AdmDto;
import br.com.users.app.dto.AuthenticationRequestDto;
import br.com.users.app.dto.SessionDto;
import br.com.users.app.services.AdmService;
import br.com.users.app.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adm")
public class AdmController {
    private final AdmService admService;
    private final AuthenticationService authenticationService;

    @PostMapping("/create")
    public ResponseEntity<AdmDto> createAdm(@RequestBody @Valid AdmDto admDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admService.createAdm(admDto));
    }

    @PostMapping("/login")
    public SessionDto login(@RequestBody AuthenticationRequestDto request) {
        String token = authenticationService.authenticate(request);
        return new SessionDto(token);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AdmDto>> getAllAdm() {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findAllAdm());
    }
}
