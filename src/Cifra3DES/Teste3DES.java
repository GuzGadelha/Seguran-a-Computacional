package Cifra3DES;

public class Teste3DES {
    public static void main(String[] args) {
        Impl3DES impl3DES = new Impl3DES();
        String cifrada = impl3DES.cifrar("mensagem confidencial");
        System.out.println("Mensagem cifrada: " + cifrada);
        String decifrada = impl3DES.decifrar(cifrada);
        System.out.println("Mensagem decifrada: " + decifrada);
    }
}