package CifraFeistelComPadding;

public class CifraFeistel {
    // tamanho fixo de cada bloco
    private static final int TAMANHO_BLOCO = 16;

    // Cifrar uma mensagem de qualquer tamanho
    public static String cifrarMensagem(String mensagem, String[] keys) {
        StringBuilder resultado = new StringBuilder();

        // Padding da mensagem para múltiplo de TAMANHO_BLOCO
        while (mensagem.length() % TAMANHO_BLOCO != 0) {
            mensagem += "\u0000";
            // padding com caractere nulo
        }

         /*
         Exemplo rápido:
         Mensagem original: "ABCDEF" (6 caracteres)
         Se TAMANHO_BLOCO = 4
         6 % 4 = 2: não é múltiplo
         Adiciona \u0000 → "ABCDEF\u0000" (7)
         7 % 4 = 3: ainda não é múltiplo
         Adiciona \u0000 → "ABCDEF\u0000\u0000" (8)
         8 % 4 = 0: agora é múltiplo, sai do loop
         */

        // Processar cada bloco
        for (int i = 0; i < mensagem.length(); i += TAMANHO_BLOCO) {
            String bloco = mensagem.substring(i, i + TAMANHO_BLOCO);
            resultado.append(cifrarBloco(bloco, keys));
        }
        return resultado.toString();
    }

    // Decifrar uma mensagem de qualquer tamanho
    public static String decifrarMensagem(String mensagemCifrada, String[] keys) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < mensagemCifrada.length(); i += TAMANHO_BLOCO) {
            String bloco = mensagemCifrada.substring(i, i + TAMANHO_BLOCO);
            resultado.append(decifrarBloco(bloco, keys));
        }

        // Remover padding no final
        return resultado.toString().replaceAll("\u0000+$", "");

         /*
         \u0000+ : “um ou mais caracteres nulos consecutivos”
         $: indica o final da string
         Então, a expressão \u0000+$ corresponde a todos os
         caracteres nulos no final da string.
         replaceAll("\u0000+$", ""): substitui esses caracteres por nada,
         removendo o padding adicionado na cifragem.
         */

    }

    private static String cifrarBloco(String blocoMensagem, String[] keys) {
        String blocoEsquerdo = blocoMensagem.substring(0, blocoMensagem.length() / 2);
        String blocoDireito = blocoMensagem.substring(blocoMensagem.length() / 2);

        for (String chaveDaRodada : keys) {
            String novoBlocoDireito =
                    xor(blocoEsquerdo, funcaoFeistel(blocoDireito, chaveDaRodada));
            blocoEsquerdo = blocoDireito;
            blocoDireito = novoBlocoDireito;
        }

        // Trocar esquerdo e direito para a última rodada
        String temp = blocoEsquerdo;
        blocoEsquerdo = blocoDireito;
        blocoDireito = temp;
        return blocoEsquerdo + blocoDireito;
    }

    private static String decifrarBloco(String blocoCifrado, String[] keys) {
        String blocoEsquerdo = blocoCifrado.substring(0, blocoCifrado.length() / 2);
        String blocoDireito = blocoCifrado.substring(blocoCifrado.length() / 2);

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
        return blocoEsquerdo + blocoDireito;
    }

    // XOR caractere a caractere
    private static String xor(String a, String b) {
        StringBuilder resultado = new StringBuilder();
        int tamanho = Math.min(a.length(), b.length());
        for (int i = 0; i < tamanho; i++) {
            resultado.append((char) (a.charAt(i) ^ b.charAt(i)));
        }
        return resultado.toString();
    }

    private static String funcaoFeistel(String direito, String chaveRodada) {
        return xor(direito, chaveRodada);
    }

    public static void main(String[] args) {
        String mensagem = "Mensagem secreta que pode ter qualquer tamanho!";
        String[] chaves = {"chave1chave1chav",
                "chave2chave2chav", "chave3chave3chav"};

        // 3 rodadas
        String cifrado = cifrarMensagem(mensagem, chaves);
        String decifrado = decifrarMensagem(cifrado, chaves);
        System.out.println("Mensagem original : " + mensagem);
        System.out.println("Mensagem cifrada : " + cifrado);
        System.out.println("Mensagem decifrada: " + decifrado);
    }
}