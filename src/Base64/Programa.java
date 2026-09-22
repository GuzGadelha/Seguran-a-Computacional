package Base64;

public class Programa {
    public static void main(String[] args) {
        String mensagem = "g u";

        String codificada = MeuBase64.codificar(mensagem);
        System.out.println("Mensagem codificada em Base64: " + codificada);

        mensagem = MeuBase64.decodificar(codificada);
        System.out.println("Mensagem original: " + mensagem);
    }
}