package gr.nothingness.backofficeusermanager.facilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {

  private static final String SALT_ALGORITHM = "MD5";
  private static final String PASSWORD_ALGORITHM = "SHA1";

  public static String generateSalt() throws NoSuchAlgorithmException {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[32];

    random.nextBytes(salt);

    return getDigest(salt, SALT_ALGORITHM).substring(16, 32);
  }

  public static String encryptPassword(String password, String salt) throws NoSuchAlgorithmException {
    String saltInBinary = new BigInteger(salt, 16).toString(2);
    String saltedPassword = password + convertBinaryToAscii(saltInBinary);

    return getDigest(saltedPassword.getBytes(StandardCharsets.ISO_8859_1), PASSWORD_ALGORITHM);
  }

  private static String getDigest(byte[] value, String algorithm) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
    byte[] digestedValue = messageDigest.digest(value);

    return convertToHex(digestedValue);
  }

  private static String convertToHex(byte[] value) {
    StringBuilder stringBuilder = new StringBuilder(value.length * 2);

    for (byte b: value) {
      stringBuilder.append(String.format("%02x", b));
    }

    return stringBuilder.toString();
  }

  private static String convertBinaryToAscii(String binaryString) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < binaryString.length(); i += 8) {
      int endIndex = i + 8;
      if (binaryString.length() < i + 8) {
        endIndex = binaryString.length();
      }
      int characterCode = Integer.parseInt(binaryString.substring(i, endIndex), 2);
      String returnCharacter = Character.toString((char) characterCode);
      stringBuilder.append(returnCharacter);
    }

    return stringBuilder.toString();
  }

}
