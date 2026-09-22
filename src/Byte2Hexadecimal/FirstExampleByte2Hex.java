package Byte2Hexadecimal;

public class FirstExampleByte2Hex {

    public static String byte2hex(byte[] bytes) {
        StringBuilder strHex = new StringBuilder();

        for (byte b : bytes) {
            strHex.append(String.format("%02x", b));
            // Maiúsculas
            // strHex.append(String.format("%02X", byte));
        }
        return strHex.toString();
    }

    public static void main(String[] args) {

        String entrada = "bytes para hexadecimal";

        System.out.println(byte2hex(entrada.getBytes()));

    }
}
