package com.globalmate.uitl;

import java.util.UUID;

public class IdGenerator {

    public static String generateId(){
        return UUID.randomUUID().toString()
                .replaceAll("-", "");
    }

}
