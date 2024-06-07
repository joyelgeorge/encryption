package encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAHash {

    public static String generateSHA256(String filePath) throws NoSuchAlgorithmException, IOException {
      
    	MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(filePath);
            DigestInputStream dis = new DigestInputStream(fis, digest)) {

            byte[] hashBytes = digest.digest();
            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                hexString.append(hex);
            }
            return hexString.toString();
        }
    }

    public static void main(String[] args) {
        String filePath = "src/asset/amd.jpg";
        try {
            String sha256Hash = generateSHA256(filePath);
            System.out.println("SHA-256 hash of file \"" + filePath + "\": " + sha256Hash);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
