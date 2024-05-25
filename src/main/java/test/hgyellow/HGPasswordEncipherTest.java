package test.hgyellow;

import common.util.PasswordEncipherUtil;

public class HGPasswordEncipherTest {

    public static void main(String[] args) {

//        encipherTest();
        matchPasswordTest();
    }

    private static void encipherTest() {
        String password = "1234";
        String encipherPassword = PasswordEncipherUtil.createPassword(password);

        System.out.println(password);
        System.out.println(encipherPassword);
    }

    private static void matchPasswordTest() {

        String password = "12345";
        String encipherPassword = PasswordEncipherUtil.createPassword(password);

        System.out.println(PasswordEncipherUtil.matchPassword(password, encipherPassword));
    }
}
