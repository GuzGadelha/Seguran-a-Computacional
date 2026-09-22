package Byte2Hexadecimal;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class SecondExampleByte2Hex {
    public static void main(String[] args) {
        // Convertendo um array de bytes direto
        byte[] bytesPuros = { 1, 2, 3, 40, 125, -1 };
        String hexDosBytes = HexFormat.of().formatHex(bytesPuros);
        System.out.println("1. Array de bytes em Hexadecimal (minúsculo):");
        System.out.println(hexDosBytes); // Saída: 01020328ffff

        // Convertendo o hexadecimal para maiúsculas
        String hexMaiusculo = HexFormat.of().withUpperCase().formatHex(bytesPuros);
        System.out.println("\n2. Array de bytes em Hexadecimal (maiúsculo):");
        System.out.println(hexMaiusculo); // Saída: 01020328FFFF

        // Convertendo uma String comum em bytes e depois em Hexadecimal
        String texto = "bytes para hexadecimal";
        byte[] bytesTexto = texto.getBytes(StandardCharsets.UTF_8);
        String hexDoTexto = HexFormat.of().formatHex(bytesTexto);
        System.out.println("\n3. Texto em Hexadecimal:");
        System.out.println(hexDoTexto);
    }
}