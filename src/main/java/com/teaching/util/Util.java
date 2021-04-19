package com.teaching.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/*
* 该类实现了一些工具函数，诸如生成课程码等
* */
public final class Util {
    private static final int CodeLength = 6;
    public static String generateInvitationCode() {
        // 6 位 87DB89
        Random randomer = new Random();
        StringBuilder invitationCode = new StringBuilder();
        for(int i = 0; i < CodeLength; i++) {
            int branch = randomer.nextInt(2);
            if(branch == 0) {
                invitationCode.append(randomer.nextInt(10));
            } else {
                // uppercase letter
                // A-Z -> 65-90
                int ascode = randomer.nextInt(26) + 65;
                char letter = (char)ascode;
                invitationCode.append(letter);
            }
        }
        return invitationCode.toString();
    }
}
