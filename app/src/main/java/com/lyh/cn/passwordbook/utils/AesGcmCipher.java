/*
 * Copyright 2018 nandsito
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lyh.cn.passwordbook.utils;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class that encapsulates KeyStore, AES key and AES-GCM cipher features.
 */
public class AesGcmCipher {

    /**
     * Default nonce size in bytes.
     */
    static final int NONCE_SIZE = 12;

    /**
     * Chosen AES key size in bits.
     */
    private static final int AES_KEY_SIZE = 256;

    /**
     * Default authentication tag size in bits.
     */
    private static final int AUTHENTICATION_TAG_SIZE = 128;

    /**
     * Android KeyStore type.
     */
    //不使用AndroidKeyStore来保存密钥，因为要导出密钥给其他手机使用
    //private static final String ANDROID_KEY_STORE = "AndroidKeyStore";

    /**
     * Alias for the application AES key.
     */
    //private static final String ALIAS_KEY = "my_key";

    /**
     * AES-GCM cipher.
     */
    private static final String CIPHER_AES_GCM = "AES/GCM/NoPadding";

    /**
     * Keystore from the application-specific Android provider.
     */
    private KeyStore mKeyStore;

    AesGcmCipher() {
        //setupKeystore();
        //insertKeyIntoKeystore(createAesKey());
    }

    /**
     * Load Android keystore.
     */
    private void setupKeystore() {
/*        try {
            mKeyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
            mKeyStore.load(null);
        } catch (CertificateException | NoSuchAlgorithmException | KeyStoreException |
                IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /**
     * Insert a key into the keystore if there's none yet.
     *
     * @param key key to be inserted
     */
    private void insertKeyIntoKeystore(Key key) {
/*        try {
            if (!mKeyStore.containsAlias(ALIAS_KEY)) {
                mKeyStore.setKeyEntry(ALIAS_KEY, key, null, null);
            }
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }*/
    }

    /**
     * Create an AES key for both encryption and decryption with AES-GCM cipher. The key requires
     * the device to be unlocked for decryption.
     *
     * @return a new AES key String
     */
    public static String createAesKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES);
            keyGenerator.init(AES_KEY_SIZE);
            SecretKey key = keyGenerator.generateKey();
            byte[] bytes = key.getEncoded();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt a piece of text with AES-GCM cipher.
     * <p>
     * Note that the cipher can encrypt any byte sequence.
     *
     * @param plaintext text to be encrypted
     * @return concatenated nonce and cipher output
     */
    public static String doEncrypt(String plaintext,String key) {
        try {
            byte[] IV = new byte[NONCE_SIZE];
            byte[] secretKey = Base64.getDecoder().decode(key);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, KeyProperties.KEY_ALGORITHM_AES);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AUTHENTICATION_TAG_SIZE, IV);
            Cipher cipher = Cipher.getInstance(CIPHER_AES_GCM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
            byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            ByteBuffer byteBuffer = ByteBuffer.allocate(IV.length + cipherText.length);
            byteBuffer.put(IV);
            byteBuffer.put(cipherText);
            return Base64.getEncoder().encodeToString(byteBuffer.array());
        } catch ( NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException  |
                BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypt a ciphertext with AES-GCM.
     *
     * @param cipherText concatenated nonce and encryption cipher output
     * @return plaintext
     */
    public static String doDecrypt(String cipherText,String key) {
        try {
            byte[] encrypted = Base64.getDecoder().decode(cipherText);
            byte[] IV = Arrays.copyOfRange(encrypted, 0, NONCE_SIZE);
            Cipher cipher = Cipher.getInstance(CIPHER_AES_GCM);

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(AUTHENTICATION_TAG_SIZE, IV);
            byte[] secretKey = Base64.getDecoder().decode(key);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, KeyProperties.KEY_ALGORITHM_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmParameterSpec);

            byte[] plainText = cipher.doFinal(Arrays.copyOfRange(encrypted, 12, encrypted.length));
            return new String(plainText, StandardCharsets.UTF_8);
        } catch ( NoSuchAlgorithmException | NoSuchPaddingException
                 | InvalidKeyException | InvalidAlgorithmParameterException
                 | BadPaddingException | IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        }
    }
}
