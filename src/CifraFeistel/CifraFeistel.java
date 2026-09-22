package CifraFeistel;

public class CifraFeistel {
    // Método para cifrar usando cifra de Feistel
    public static String cifrar(String blocoMensagem, String[] keys) {

        String blocoEsquerdo =
                blocoMensagem.substring(0, blocoMensagem.length() / 2);
        String blocoDireito =
                blocoMensagem.substring(blocoMensagem.length() / 2);
        // Aplicar rodadas de Feistel
        for (String chaveDaRodada : keys) {
            String novoBlocoDireito =
                    xor(blocoEsquerdo,
                            funcaoFeistel(blocoDireito, chaveDaRodada));
            blocoEsquerdo = blocoDireito;
            blocoDireito = novoBlocoDireito;
        }
        // Trocar esquerdo e direito para a última rodada
        String temp = blocoEsquerdo;
        blocoEsquerdo = blocoDireito;
        blocoDireito = temp;
        // Concatenar esquerdo e direito para obter o bloco cifrado
        return blocoEsquerdo + blocoDireito;
    }
    // Método para decifrar usando cifra de Feistel
    public static String decifrar(String blocoCifrado, String[] keys) {

        String blocoEsquerdo =
                blocoCifrado.substring(0, blocoCifrado.length() / 2);
        String blocoDireito =
                blocoCifrado.substring(blocoCifrado.length() / 2);
        // Inverter a ordem das subchaves para decifração
        for (int i = keys.length - 1; i >= 0; i--) {
            String novoBlocoDireito =
                    xor(blocoEsquerdo, funcaoFeistel(blocoDireito, keys[i]));
            blocoEsquerdo = blocoDireito;
            blocoDireito = novoBlocoDireito;
        }
        // Trocar esquerdo e direito para a última rodada
        String temp = blocoEsquerdo;
        blocoEsquerdo = blocoDireito;
        blocoDireito = temp;
        // Concatenar esquerdo e direito para obter o bloco decifrado
        return blocoEsquerdo + blocoDireito;
    }
    // Função de operação XOR
    private static String xor(String a, String b) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            // Operação XOR bit a bit
            resultado.append(a.charAt(i) ^ b.charAt(i));
        }
        return resultado.toString();
    }
    // Função de Feistel simples (pode ser substituída por funções mais complexas)
    private static String funcaoFeistel(String direito, String chaveRodada) {
        // Usar uma função simples, por exemplo, XOR, para a função de Feistel
        return xor(direito, chaveRodada);
    }
}
