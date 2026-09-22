package HashMD5;

public class Programa {
    public static void main(String[] args) {
        String algoritmo = "MD5";
        String texto = "Funções de hash";
        System.out.println("Entrada (string): " + texto);
        System.out.println("Entrada (tamanho): " + texto.length());

        //
        byte[] bytesTextoMD5 = ImplMD5.resumo(texto.getBytes(ImplMD5.UTF_8), algoritmo);
        System.out.println("Hexa: " + ImplMD5.bytes2Hex(bytesTextoMD5));
        System.out.println("Tamanho: " + bytesTextoMD5.length);

        // API MODERNA
        byte[] bytesTextoMD5ModernAPI = ImplMD5.resumo(texto.getBytes(ImplMD5.UTF_8), algoritmo);
        System.out.println("Hexa: " + ImplMD5.bytes2HexModernAPI(bytesTextoMD5ModernAPI));
        System.out.println("Tamanho: " + bytesTextoMD5ModernAPI.length);
    }
}