package com.assesment.bajaj.service;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.assesment.bajaj.dto.BFHLRequest;
import com.assesment.bajaj.dto.BFHLResponse;

@SpringBootTest
class BFHLServiceTest {

@Autowired
private BFHLService bfhlService;

@Test
void testExampleA() {

    BFHLRequest request = new BFHLRequest();
    request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

    BFHLResponse response = bfhlService.processData(request);

    assertNotNull(response);
    assertTrue(response.getIsSuccess());
    assertEquals("339", response.getSum());
    assertEquals("Ra", response.getConcatString());
}

@Test
void testExampleB() {

    BFHLRequest request = new BFHLRequest();
    request.setData(Arrays.asList(
            "2", "a", "y", "4",
            "&", "-", "*",
            "5", "92", "b"
    ));

    BFHLResponse response = bfhlService.processData(request);

    assertNotNull(response);
    assertTrue(response.getIsSuccess());
    assertEquals("103", response.getSum());
    assertEquals("ByA", response.getConcatString());
}

@Test
void testOnlyNumbers() {

    BFHLRequest request = new BFHLRequest();
    request.setData(Arrays.asList("1", "2", "3", "4", "5"));

    BFHLResponse response = bfhlService.processData(request);

    assertEquals("15", response.getSum());
    assertEquals(3, response.getOddNumbers().size());
    assertEquals(2, response.getEvenNumbers().size());
}

@Test
void testOnlySpecialCharacters() {

    BFHLRequest request = new BFHLRequest();
    request.setData(Arrays.asList("@", "#", "!"));

    BFHLResponse response = bfhlService.processData(request);

    assertEquals("0", response.getSum());
    assertEquals(3, response.getSpecialCharacters().size());
}


}
