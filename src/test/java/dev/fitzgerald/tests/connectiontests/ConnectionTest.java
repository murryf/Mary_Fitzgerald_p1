package dev.fitzgerald.tests.connectiontests;

import dev.fitzgerald.tests.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectionTest {
    @Test
    void can_connect(){
        Connection conn = ConnectionUtil.createConnection();
        Assertions.assertNotNull(conn);
    }
}