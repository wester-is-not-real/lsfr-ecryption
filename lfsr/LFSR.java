/* *****************************************************************************
 * Name: Wester J. Aldarondo Torres
 * NetID: wester.aldarondo@upr.edu
 * Precept: P00
 *
 * Description:  Produces pseudo-random bits by simulating a linear-feedback
 *               shift register, and then use it to implement a simple form of
 *               encryption for digital pictures.
 *
 **************************************************************************** */

public class LFSR {
    private final int n, tapPosition;
    //Instance variables for number of bits n and tap position in LFSR
    private final int[] reg;    //Instance variable for the array of bits or sequence of bits

    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        tapPosition = tap;
        n = seed.length();
        reg = new int[n];
        for (int i = 0; i < n; i++) {
            if (seed.charAt(i) == '0')
                reg[i] = 0;
            else
                reg[i] = 1;
            //System.out.print(reg[i] + ", ");
        }
    }

    // returns the number of bits in this LFSR
    public int length() {
        return reg.length;
    }

    //returns the ith bit of this LFSR (as 0 or 1)
    public int bitAt(int i) {
        return reg[n - i];
    }

    //returns a string representation of this LFSR
    public String toString() {
        String print = "";
        for (int i = 0; i < n; i++) {
            print = print + reg[i];
        }
        return print;
    }

    // simulates one step of this LFSR and returns the new bit (as 0 or 1)
    public int step() {
        int lastnum = bitAt(n) ^ bitAt(tapPosition);
        for (int i = 0; i < n - 1; i++) {
            reg[i] = reg[i + 1];
        }
        reg[n - 1] = lastnum;
        return lastnum;
    }

    // simulates k steps of this LFSR and returns the k bits as a k-bit integer
    public int generate(int k) {
        int a = 0;
        for (int i = 0; i < k; i++) {
            a = (a * 2) + step();
        }
        return a;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {
        // PUT YOUR TEST CODE HERE
        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2.length());
        StdOut.println(lfsr2.bitAt(1));
        StdOut.println(lfsr2.toString());
        StdOut.println(lfsr2.step());
        StdOut.println(lfsr2.generate(3));
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.generate(5);
            StdOut.println(lfsr2 + " " + r);
        }
    }
}
