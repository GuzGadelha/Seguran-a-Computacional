package CifraDES;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ImplDES {

    private KeyGenerator geradorChave;
    private SecretKey chave;
    private Cipher meuCifrador;
    private String mensagem;
    private byte[] bytesMensagem;
    private byte[] bytesEncriptados;
    private byte[] bytesDecriptados;
    private String mensagemEncriptada;
    private String mensagemDecriptada;

    public ImplDES(String m) {
        mensagem = m;
        this.rodar(mensagem);
    }

    private void gerarChave() {
        String chaveBase64;
        try {
            geradorChave = KeyGenerator.getInstance("DES");
            chave = geradorChave.generateKey();
            System.out.println("Chave gerada: " + chave.getEncoded().toString());

            /*
             chaveBase64 = Base64.getEncoder().encodeToString(chave.getEncoded());
                System.out.println("Chave gerada: " + chaveBase64);
            */

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void rodar(String textoAberto) {
        try {
            // mensagem
            mensagem = textoAberto;

            // transformando a mensagem em bytes
            bytesMensagem = mensagem.getBytes();

            // Geração da chave
            gerarChave();

            // Inicializando o DES
            meuCifrador = Cipher.getInstance("DES");

            // configurando para o modo de encriptação
            meuCifrador.init(Cipher.ENCRYPT_MODE, chave);
            bytesEncriptados = meuCifrador.doFinal(bytesMensagem);

            // configurando para o modo de decriptação
            meuCifrador.init(Cipher.DECRYPT_MODE, chave);
            bytesDecriptados = meuCifrador.doFinal(bytesEncriptados);

            // imprimir mensagem em bytes
            System.out.println("Bytes criptografados: " + Arrays.toString(bytesEncriptados));
            System.out.println("Bytes descriptografados: " + Arrays.toString(bytesDecriptados));

            mensagemEncriptada = new String(bytesEncriptados);
            mensagemDecriptada = new String(bytesDecriptados);

            System.out.println("Mensagem : " + mensagem);
            System.out.println("Criptografada - " + mensagemEncriptada);
            System.out.println("Descriptografada - " + mensagemDecriptada);

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
        }
    }
}