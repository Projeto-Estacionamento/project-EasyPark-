package com.backend.EasyPark.util;

public class ValidaEmail {
    public static boolean validaCaracTerArroba(String email) {
        return email != null && email.contains("@");
    }
}
