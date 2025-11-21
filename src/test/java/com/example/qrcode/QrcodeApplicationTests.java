package com.example.qrcode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QrcodeApplicationTests {


    @Test
    void testMainMethodRuns() {
        QrcodeApplication.main(new String[] {});
    }

}
