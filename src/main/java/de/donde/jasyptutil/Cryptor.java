package de.donde.jasyptutil;

import org.jasypt.util.text.AES256TextEncryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cryptor {

    private static final String PAR_FILE = "--file";
    private static final String PAR_MODE = "--mode";
    private static final String PAR_TEXT = "--text";
    private static final String PAR_SECRET = "--secret";


    /*
        Parameters:
            --file <path to file>
            --mode <enc|dec>
            --secret <secret>
            --text <text to encrypt/decrypt>
     */
    public static void main(String[] args) {
        Map<String, String> parameters =  parseParameters(args);
        if (parameters.size() < 3) {
            help();
            return;
        }

        String text;
        if (!parameters.containsKey(PAR_FILE)) {
            text = parameters.get(PAR_TEXT);
        }  else {
            text = fileToString(parameters.get(PAR_FILE));
        }

        if ("enc".equals(parameters.get(PAR_MODE))) {
                System.out.println(encrypt(text, parameters.get(PAR_SECRET)));
            } else {
                System.out.println(decrypt(text, parameters.get(PAR_SECRET)));
            }
    }


    private static String fileToString(String path) {
        StringBuilder sb = new StringBuilder();
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString().replace("\n", "");
    }

    private static void help() {
        System.out.println("Parameters:");
        System.out.println("--file <file to encrypt/decrypt> or --text <text to encrypt/decrypt>");
        System.out.println("--secret <secret to encrypt/decrypt>");
        System.out.println("--mode <enc|dec>");
    }

    private static AES256TextEncryptor getAes256TextEncryptor(String key) {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(key);
        return textEncryptor;
    }

    private static String encrypt(final String value, final String key) {
        AES256TextEncryptor textEncryptor = getAes256TextEncryptor(key);
        return textEncryptor.encrypt(value);
    }

    private static String decrypt(final String value, final String key) {
        AES256TextEncryptor textEncryptor = getAes256TextEncryptor(key);
        return textEncryptor.decrypt(value);
    }

    private static Map<String, String> parseParameters(String[] args) {

        Map<String, String> parameters = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case PAR_FILE -> {
                    if (i + 1 < args.length) {
                        parameters.put(PAR_FILE, args[i + 1]);
                    }
                }
                case PAR_MODE -> {
                    if (i + 1 < args.length) {
                        parameters.put(PAR_MODE, args[i + 1]);
                    }
                }
                case PAR_SECRET -> {
                    if (i + 1 < args.length) {
                        parameters.put(PAR_SECRET, args[i + 1]);
                    }
                }
                case PAR_TEXT -> {
                    if (i + 1 < args.length) {
                        parameters.put(PAR_TEXT, args[i + 1]);
                    }
                }
            }
        }
        return parameters;
    }
}
