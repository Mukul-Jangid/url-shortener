package com.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ShortUrlCodeGeneratorTest {

  private final ShortUrlCodeGenerator generator = new ShortUrlCodeGenerator();

  @Test
  void generateCode_defaultLength_returnsSevenCharacterBase62String() {
    String code = generator.generateCode();
    assertNotNull(code);
    assertEquals(7, code.length());
    assertTrue(code.matches("^[a-zA-Z0-9]{7}$"));
  }

  @Test
  void generateCode_customLength_returnsMatchingLength() {
    String code = generator.generateCode(10);
    assertNotNull(code);
    assertEquals(10, code.length());
    assertTrue(code.matches("^[a-zA-Z0-9]{10}$"));
  }
}
