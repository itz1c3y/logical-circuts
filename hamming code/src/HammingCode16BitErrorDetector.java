import java.util.Scanner;

public class HammingCode16BitErrorDetector {

    public static void detectParityBits(int[] hammingCode) {
        System.out.println("Detected parity bits and their positions:");
        int[] parityPositions = {1, 2, 4, 8, 16};
        for (int pos : parityPositions) {
            if (pos <= hammingCode.length) {
                int index = pos - 1;
                System.out.println("Parity bit at position " + pos + ": " + hammingCode[index]);
            }
        }
    }

    public static void detectAndCorrectError(int[] hammingCode) {
        int p1 = hammingCode[0] ^ hammingCode[2] ^ hammingCode[4] ^ hammingCode[6] ^ hammingCode[8] ^ hammingCode[10] ^ hammingCode[12] ^ hammingCode[14];
        int p2 = hammingCode[1] ^ hammingCode[2] ^ hammingCode[5] ^ hammingCode[6] ^ hammingCode[9] ^ hammingCode[10] ^ hammingCode[13] ^ hammingCode[14];
        int p4 = hammingCode[3] ^ hammingCode[4] ^ hammingCode[5] ^ hammingCode[6] ^ hammingCode[11] ^ hammingCode[12] ^ hammingCode[13] ^ hammingCode[14];
        int p8 = hammingCode[7] ^ hammingCode[8] ^ hammingCode[9] ^ hammingCode[10] ^ hammingCode[11] ^ hammingCode[12] ^ hammingCode[13] ^ hammingCode[14];
        int p16 = hammingCode[15];

        int errorPosition = (p16 * 16) + (p8 * 8) + (p4 * 4) + (p2 * 2) + p1;

        if (errorPosition == 0) {
            System.out.println("No error detected in the Hamming code.");
        } else {
            System.out.println("Error detected at position: " + errorPosition);
            hammingCode[errorPosition - 1] ^= 1;
            System.out.println("Hamming code after correction:");
            for (int bit : hammingCode) System.out.print(bit);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the 16-bit Hamming code as a sequence of 0s and 1s (e.g., 1011001101011101):");
        String hammingCodeStr = scanner.nextLine();

        if (hammingCodeStr.length() != 16) {
            System.out.println("Error: Please enter exactly 16 bits.");
            return;
        }

        int[] hammingCode = new int[16];
        for (int i = 0; i < hammingCodeStr.length(); i++) {
            hammingCode[i] = Character.getNumericValue(hammingCodeStr.charAt(i));
        }

        detectParityBits(hammingCode);

        detectAndCorrectError(hammingCode);
    }
}
