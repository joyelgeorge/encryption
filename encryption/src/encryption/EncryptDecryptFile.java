package encryption;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;

public class EncryptDecryptFile {

    public static void main(String[] args) {
        try {
            // Generate RSA key pair
        	KeyPair keyPair = RSAKeyPairGenerator.generateKeyPair();
       
            // Encrypt file
            encryptFile("src/asset/amd.jpg", "src/asset/encrypted.jpg", keyPair.getPublic());

            // Decrypt file
            decryptFile("src/asset/encrypted.jpg", "src/asset/decrypted.jpg", keyPair.getPrivate());

            System.out.println("Encryption and decryption completed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encryptFile(String inputFile, String outputFile, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] inputBuffer = new byte[245];
            int bytesRead;
            while ((bytesRead = fis.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.doFinal(inputBuffer, 0, bytesRead);
                fos.write(outputBuffer);
            }
        }
    }

    private static void decryptFile(String inputFile, String outputFile, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] inputBuffer = new byte[256]; // RSA 2048 bits key size, so 256 bytes max block size
            int bytesRead;
            while ((bytesRead = fis.read(inputBuffer)) != -1) {
                byte[] outputBuffer = cipher.doFinal(inputBuffer, 0, bytesRead);
                fos.write(outputBuffer);
            }
        }
    }
}
