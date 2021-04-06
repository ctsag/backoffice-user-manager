package gr.nothingness.backofficeusermanager.security.facilities;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class MixedDigestPasswordEncoder implements PasswordEncoder {

  private static final String SALT_ALGORITHM = "MD5";
  private static final String PASSWORD_ALGORITHM = "SHA1";

  @Getter @Setter private String salt;

  @Override
  public String encode(CharSequence password) {
    if (salt == null) {
      generateSalt();
    }

    String saltInBinary = new BigInteger(salt, 16).toString(2);
    String saltedPassword = password.toString() + binaryToAscii(addLeadingZeroes(saltInBinary));

    try {
      return getDigest(saltedPassword.getBytes(StandardCharsets.ISO_8859_1), PASSWORD_ALGORITHM);
    } catch (NoSuchAlgorithmException exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
  }

  @Override
  public boolean matches(CharSequence password, String encodedPassword) {
    return encodedPassword.equals(encode(password));
  }

  private void generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[32];

    random.nextBytes(salt);

    try {
      this.salt = getDigest(salt, SALT_ALGORITHM);
    } catch (NoSuchAlgorithmException exception) {
      throw new IllegalArgumentException(exception.getMessage());
    }
  }

  private String getDigest(byte[] value, String algorithm) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
    byte[] digestedValue = messageDigest.digest(value);

    return bytesToHex(digestedValue);
  }

  private String bytesToHex(byte[] value) {
    StringBuilder stringBuilder = new StringBuilder(value.length * 2);

    for (byte b: value) {
      stringBuilder.append(String.format("%02x", b));
    }

    return stringBuilder.toString();
  }

  private String binaryToAscii(String binary) {
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < binary.length(); i += 8) {
      int characterCode = Integer.parseInt(binary.substring(i, i + 8), 2);
      String asciiCharacter = Character.toString((char)characterCode);
      stringBuilder.append(asciiCharacter);
    }

    return stringBuilder.toString();
  }

  private String addLeadingZeroes(String binary) {
    StringBuilder stringBuilder = new StringBuilder(binary);
    int missingZeroes = 8 - (stringBuilder.length() % 8);

    for (int i = 0; i < missingZeroes; i++) {
      stringBuilder.insert(0, "0");
    }

    return stringBuilder.toString();
  }

}
