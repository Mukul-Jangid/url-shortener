package com.urlshortener.service;

import java.security.SecureRandom;
import org.springframework.stereotype.Component;

/** Generates random Base62 short codes of fixed 7-character length. */
@Component
public class ShortUrlCodeGenerator {

  private static final String BASE62_ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  private static final int DEFAULT_CODE_LENGTH = 7;

  private final SecureRandom random = new SecureRandom();

  public String generateCode() {
    return generateCode(DEFAULT_CODE_LENGTH);
  }

  public String generateCode(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(BASE62_ALPHABET.length());
      sb.append(BASE62_ALPHABET.charAt(index));
    }
    return sb.toString();
  }
}
