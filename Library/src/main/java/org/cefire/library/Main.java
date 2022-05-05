package org.cefire.library;

import org.cefire.library.user.RegistrationController;

import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Spark.post("/register", RegistrationController::register);
    }
}