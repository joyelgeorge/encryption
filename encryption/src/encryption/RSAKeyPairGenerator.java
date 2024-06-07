package encryption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairGenerator {
        
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
    	
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void main(String[] args) {
        try {
            KeyPair keyPair = generateKeyPair();
            
            System.out.println("Public Key: " + keyPair.getPublic());
            System.out.println("Private Key: " + keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}