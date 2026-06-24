package com.assesment.bajaj.service;

import com.assesment.bajaj.dto.BFHLRequest;
import com.assesment.bajaj.dto.BFHLResponse;

public interface BFHLService {
    BFHLResponse processData(BFHLRequest request);
}