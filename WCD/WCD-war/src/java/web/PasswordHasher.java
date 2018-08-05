/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author garto
 */
public class PasswordHasher {

    public String hashPassword(String plainText) {
        String result = null;
        try {
            //hash password
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainText.getBytes("UTF-8"));
            byte[] digest = md.digest();
            result = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace(System.err);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace(System.err);
        }
        return result;
    }
}
