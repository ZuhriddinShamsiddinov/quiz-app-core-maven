package com.company.utils;

import com.google.gson.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Type;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * @author "Zuhriddin Shamsiddinov"
 * @created 2:48 PM on 6/20/2022 on Monday
 * @project projects16
 */
public class BaseUtils {
    public static Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }
    }).setPrettyPrinting().create();
    public static Gson gsonWithNulls = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    private final static Scanner readText = new Scanner(System.in);
    private final static Scanner readNumerics = new Scanner(System.in);

    public static String readText() {
        return readText.nextLine();
    }

    public static String readText(String data) {
        print(data, Colors.BLUE);
        return readText.nextLine();
    }

    public static String readPassword(String data) {
        return new String(System.console().readPassword(data + Colors.BLUE));
    }

    public static String readText(String data, String color) {
        print(data, color);
        return readText.nextLine();
    }

    public static void print(String data) {
        print(data, Colors.BLUE);
    }

    public static void print(String data, String color) {
        out.print(color + data + Colors.RESET);
    }

    public static void println(Object data) {
        println(data, Colors.BLUE);
    }


    public static void println(Object data, String color) {
        out.println(color + data + Colors.RESET);
    }

    public static void println(String data, Object... args) {
        out.printf((data) + "%n", args);
    }

    public static String otp(int bound){
        return String.valueOf((new Random().nextInt((int) Math.pow(10,bound-1), (int) Math.pow(10,bound))));
    }
    public static String encryptWithKey(String key, String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] KeyData = key.getBytes();
        SecretKeySpec KS = new SecretKeySpec(KeyData, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");

        cipher.init(Cipher.ENCRYPT_MODE, KS);
        byte[] bytes = cipher.doFinal(text.getBytes());
        return new String(Base64.getEncoder().encode(bytes));
    }


}
