package br.com.users.app.controllers;

import br.com.users.app.dto.AdmDto;
import br.com.users.app.services.AdmService;
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

    @PostMapping("/create")
    public ResponseEntity<AdmDto> createAdm(@RequestBody @Valid AdmDto admDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(admService.createAdm(admDto));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<AdmDto>> getAllAdm() {
        return ResponseEntity.status(HttpStatus.OK).body(admService.findAllAdm());
    }
}
