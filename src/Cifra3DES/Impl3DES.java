package Cifra3DES;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Impl3DES {

    byte[] chave;
    SecretKeySpec chaveSpec = null;
    byte[] vi;
    IvParameterSpec viSpec = null;
    byte[] bytesMsg;
    byte[] bytesEncriptados;

    Impl3DES(){
        this.gerarChave();
    }

    private void gerarChave() {
        chave = "1234567890abcdfghijlkmno".getBytes();
        chaveSpec = new SecretKeySpec(chave, "TripleDES");
        vi = "o49qd1e5".getBytes();
        viSpec = new IvParameterSpec(vi);
    }

    public String cifrar(String msg) {
        String mensagemEncriptada = null;
        Cipher cifrador;

        try {
            cifrador = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
            cifrador.init(Cipher.ENCRYPT_MODE, chaveSpec, viSpec);

            bytesMsg = msg.getBytes(StandardCharsets.UTF_8);
            bytesEncriptados = cifrador.doFinal(bytesMsg);

            System.out.println("Bytes encriptados: " + Arrays.toString(bytesEncriptados));

            mensagemEncriptada = new String(bytesEncriptados);

        /*
            System.out.println("Mensagem encriptada: " + mensagemEncriptada);
        */

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return mensagemEncriptada;
    }

    public String decifrar(String msgCifrada) {
        String msgDecriptada = null;
        Cipher decifrador;

        try {
            decifrador = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
            decifrador.init(Cipher.DECRYPT_MODE, chaveSpec, viSpec);

            byte[] bytesMsgDecriptada = decifrador.doFinal(bytesEncriptados);

            System.out.println(
                    "\nBytes decriptados: " + Arrays.toString(bytesMsgDecriptada)
            );

            msgDecriptada = new String(bytesMsgDecriptada, StandardCharsets.UTF_8);

            /*
                System.out.println("Mensagem decriptada: " + msgDecriptada);
            */

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return msgDecriptada;
    }
}