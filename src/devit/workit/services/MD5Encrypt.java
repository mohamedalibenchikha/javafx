/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package devit.workit.services;

import java.security.MessageDigest;

/**
 *
 * @author Wael Belhadj
 */
public class MD5Encrypt {
    
    public String encrypt(String password) {

        try {
       byte[] bytesOfMessage = password.getBytes("UTF-8");
       MessageDigest md = MessageDigest.getInstance("MD5");
       byte[] md5 = md.digest(bytesOfMessage);
       StringBuffer stringBuffer = new StringBuffer();
       for (int i = 0; i < md5.length; i++) {
          stringBuffer.append(Integer.toString((md5[i] & 0xff) + 0x100, 16).substring(1));
       }
       return stringBuffer.toString();
   } catch (Exception e) {
   }
   return null;
    }

}
