package common.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.security.MessageDigest;

public class PasswordEncipherUtil {
    private static String salt;
    private static MessageDigest md;
    private static final int MIN = 100;
    private static final int MAX = 1000;

    static {
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

        String resource = "application.properties";

        try {
            InputStream inputStream = PasswordEncipherUtil.class.getClassLoader().getResource(resource).openStream();

            Properties prop = new Properties();
            prop.load(inputStream);

            salt = prop.getProperty("password.encipher.salt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean matchPassword(String password, String encipherPassword) {
        String[] info = encipherPassword.split("/");

        String passwordSalt = info[1];
        int keyStretching = Integer.parseInt(info[0]);

        return encipherPassword.equals(encipher(keyStretching, passwordSalt, password));
    }

    public static String createPassword(String password) {
        String passwordSalt = getSalt();
        int keyStretching = getNumber();

        return encipher(keyStretching, passwordSalt, password);
    }

    private static String encipher(int keyStretching, String passwordSalt, String password) {
        password = password + passwordSalt;
        md.update(password.getBytes(StandardCharsets.UTF_8));

        for (int i = 0; i < keyStretching; i++) {
            String temp = ByteToHex(md.digest())+passwordSalt;
            md.update(temp.getBytes(StandardCharsets.UTF_8));
        }

        return createPassword(keyStretching, passwordSalt, ByteToHex(md.digest()));
    }

    private static String ByteToHex(byte[] temp) {
        StringBuilder sb = new StringBuilder();

        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }

        return sb.toString();
    }

    private static int getNumber() {
        return new Random().nextInt(MAX-MIN) + MIN;
    }

    private static String getSalt() {
        List<String> saltCharacterList = new ArrayList<>(List.of(salt.split("")));
        Collections.shuffle(saltCharacterList);

        StringBuilder sb = new StringBuilder();
        saltCharacterList.forEach(sb::append);
        return sb.toString();
    }

    private static String createPassword(int keyStretching, String passwordSalt, String password) {
        StringJoiner sj = new StringJoiner("/");
        sj.add(String.valueOf(keyStretching));
        sj.add(passwordSalt);
        sj.add(password);
        return sj.toString();
    }
}
