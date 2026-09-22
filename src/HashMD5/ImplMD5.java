package HashMD5;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class ImplMD5 {
    static final Charset UTF_8 =
            StandardCharsets.UTF_8;

    public static byte[] resumo(byte[] bytesEntrada, String alg) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(alg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] bytesResumo = md.digest(bytesEntrada);
        return bytesResumo;
    }

    public static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }

    // Método para usar a API moderna HexFormat
    public static String bytes2HexModernAPI(byte[] bytes) {
        return HexFormat.of().formatHex(bytes);
    }

}