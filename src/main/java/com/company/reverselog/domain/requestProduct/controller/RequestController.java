package com.company.reverselog.domain.requestProduct.controller;

import com.company.reverselog.domain.request.dto.RequestDetailData;
import com.company.reverselog.domain.request.dto.RequestRegistrationData;

import com.company.reverselog.domain.requestProduct.dto.DataListRequestDto;
import com.company.reverselog.domain.requestProduct.service.MakeRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
public class RequestController {
    @Autowired
    private MakeRequest makeRequestService;


    @GetMapping
    @Secured("ROLE_ADMIN")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Recupera todas as solicitações de ordem de serviço do sistema")
    public ResponseEntity<Page<DataListRequestDto>> findAllRequests(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {
        Page<DataListRequestDto> dataListRequestDto = makeRequestService.findAllRequest(pageable);
        return ResponseEntity.ok(dataListRequestDto);

    }

    @PostMapping
    @Transactional
    @Operation(summary = "Cria uma solicitação de ordem de serviço no sistema")
    public ResponseEntity<RequestDetailData> registrationRequest(@Valid @RequestBody RequestRegistrationData request, UriComponentsBuilder uriBuilder) {
        RequestDetailData dto = makeRequestService.saveRequest(request);
        var uri = uriBuilder.path("/solicitacao/{id}").buildAndExpand(dto.cliente().getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

}
