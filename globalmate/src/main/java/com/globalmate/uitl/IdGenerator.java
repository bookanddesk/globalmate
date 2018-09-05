package com.globalmate.uitl;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    public static String generateId(){
        return UUID.randomUUID().toString()
                .replaceAll("-", "");
    }

    public static String generateMemberId(String lastMemberId) {
        final String member_id_format = "000000";
        int anInt = new AtomicInteger(Integer.parseInt(lastMemberId)).incrementAndGet();
        return new DecimalFormat(member_id_format).format(anInt);
    }

}
