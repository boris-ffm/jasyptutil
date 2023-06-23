package de.donde.jasyptutil;

import org.jasypt.util.text.AES256TextEncryptor;

public class Cryptor {

    /**
     * @param args  1 - enc/dec,  2 - Secret  3 - value to encrypt/decrypt,cd ..cd
     */
    public static void main(String[] args) {
        if (args.length != 3 || (!args[0].equals("enc") && !args[0].equals("dec"))) {
            help();
            System.exit(-1);
        }
        System.out.println("Modus:\t" + args[0]);
        System.out.println("Wert:\t" + args[2]);
        System.out.println("Key:\t" + args[1]);

        if (args[0].equals("enc")) {
            System.out.println(encrypt(args[2], args[1]));
        } else {
            System.out.println(decrypt(args[2], args[1]));
        }
    }

    private static void help() {
        System.out.println("1 - enc/dec, 2 - key, 3 - value to encrypt");
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
}
