package com.example.farmBackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/crops")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CropController {

}
