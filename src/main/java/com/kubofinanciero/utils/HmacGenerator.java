package com.kubofinanciero.utils;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HmacGenerator {
  private static String SECRET_KEY = "86f23ace-44ad-4218-99b1-2f731ada0098";
  private static String ALGORITHM = "HmacSHA256";

  private static byte[] hashHMAC(String message) {
    byte[] hash = new byte[] {};

    try {
      Mac sha256HMAC = Mac.getInstance(ALGORITHM);
      SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
      sha256HMAC.init(secretKey);
      hash = sha256HMAC.doFinal(message.getBytes());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return hash;
  }

  public static String getHexadecimalHash(String message) {
    byte[] hash = hashHMAC(message);

    StringBuffer result = new StringBuffer();
    for (byte b : hash) {
      result.append(String.format("%02x", b));
    }
    return result.toString();
  }

  public static String getBase64Hash(String message) {
    byte[] hash = hashHMAC(message);
    return Base64.getEncoder().encodeToString(hash);
  }

  public static void main(String[] args) {
    System.out.println(HmacGenerator.getHexadecimalHash("jesus@mail.com"));
    System.out.println(HmacGenerator.getBase64Hash("jesus@mail.com"));
  }
}