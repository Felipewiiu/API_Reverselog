package com.company.reverselog.domain.requestProduct.controller;

import com.company.reverselog.domain.solicitacao.dto.RequestRegistrationData;

import com.company.reverselog.domain.requestProduct.dto.DataListRequestDto;
import com.company.reverselog.domain.requestProduct.service.MakeRequest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/solicitacao")
@SecurityRequirement(name = "bearer-key")
public class SolicitacaoController {
    @Autowired
    private MakeRequest makeRequestService;


    @GetMapping
    @Secured("ROULE_ADMIN")
    public ResponseEntity<Page<DataListRequestDto>> findAllRequests(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DataListRequestDto> dataListRequestDto = makeRequestService.findAllRequest(pageable);

        return ResponseEntity.ok(dataListRequestDto);

    }

    @PostMapping
    @Transactional
    public ResponseEntity registrationRequest(@RequestBody RequestRegistrationData request, UriComponentsBuilder uriBuilder) {
        var dto = makeRequestService.saveRequest(request);

        var uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(dto.cliente().getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
