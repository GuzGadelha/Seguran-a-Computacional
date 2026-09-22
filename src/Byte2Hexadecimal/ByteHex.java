package Byte2Hexadecimal;

public class ByteHex {
    public static void main(String[] args) {
        byte[] bytes = {12, 27, 36, 40};

        System.out.println("Array em hexadecimal: ");
        for (byte b : bytes) {
            String st = String.format("%02X",b & 0xFF);
            System.out.print(st + "\n");
        }

        System.out.println("\nArray em binário: ");
        for (byte b : bytes) {
            System.out.print(Integer.toBinaryString(b) + "\n");
        }
    }
}
