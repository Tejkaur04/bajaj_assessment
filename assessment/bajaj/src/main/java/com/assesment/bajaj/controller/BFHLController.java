package com.assesment.bajaj.controller;


import com.assesment.bajaj.dto.BFHLRequest;
import com.assesment.bajaj.dto.BFHLResponse;
import com.assesment.bajaj.service.BFHLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BFHL")
public class BFHLController {

    @Autowired
    private BFHLService BFHLService;

    /**
     * POST endpoint to process array data
     * @param request the request containing array of data
     * @return processed response with classification
     */
    @PostMapping
    public ResponseEntity<BFHLResponse> processBFHL(@RequestBody BFHLRequest request) {
        try {
            BFHLResponse response = BFHLService.processData(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}