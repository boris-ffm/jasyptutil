package de.donde.jasyptutil;

import org.jasypt.util.text.AES256TextEncryptor;

public class Cryptor {

    /**
     * @param args  1 - enc/dec,  2 - value to encrypt/decrypt,  3 - key
     */
    public static void main(String[] args) {
        if (args.length != 3 || (!args[0].equals("enc") && !args[0].equals("dec"))) {
            help();
            System.exit(-1);
        }
        System.out.println("Modus:\t" + args[0]);
        System.out.println("Wert:\t" + args[1]);
        System.out.println("Key:\t" + args[2]);

        if (args[0].equals("enc")) {
            System.out.println(encrypt(args[1], args[2]));
        } else {
            System.out.println(decrypt(args[1], args[2]));
        }
    }

    private static void help() {
        System.out.println("1 - enc/dec,  2 - value to encrypt,  3 - key");
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
