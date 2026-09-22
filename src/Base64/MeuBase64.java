package Base64;

import java.util.Base64;

public class MeuBase64 {
    public static String codificar(String msg) {
        byte[] bytesMsg = msg.getBytes();
        String msgBase64 = Base64.getEncoder().encodeToString(bytesMsg);
        return msgBase64;
    }
    public static String decodificar(String msgBase64) {
        byte[] bytesMsgBase64 = Base64.getDecoder().decode(msgBase64);
        return new String(bytesMsgBase64);
    }
}