package org.cefire.library.user;

import spark.Request;

public class TestRequest extends Request {
    @Override
    public String queryParamOrDefault(String queryParam, String defaultValue) {
        return switch (queryParam) {
            case "fullname" -> "José";
            case "dni" -> "11111111H";
            case "birthdate" -> "1988-04-08";
            default -> throw new RuntimeException();
        };
    }
}
