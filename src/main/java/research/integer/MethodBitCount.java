package research.integer;

public class MethodBitCount {


    /**
     * Returns the number of one-bits in the two's complement binary
     * representation of the specified {@code int} value.  This function is
     * sometimes referred to as the <i>population count</i>.
     *
     * @param i the value whose bits are to be counted
     * @return the number of one-bits in the two's complement binary
     *     representation of the specified {@code int} value.
     * @since 1.5
     */
    public static int bitCount(int i) {
        System.out.println("初始的i：" + i);
        System.out.println("初始二进制：" + Integer.toBinaryString(i));
        // 01010101010101010101010101010101
        i = i - ((i >>> 1) & 0x55555555);
        System.out.println("第一步操作后的i：" + i);
        System.out.println("第一步操作后的二进制：" + Integer.toBinaryString(i));
        // 00110011001100110011001100110011
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        System.out.println("第二步操作后的i：" + i);
        System.out.println("第二步操作后的二进制：" + Integer.toBinaryString(i));
        // 00001111000011110000111100001111
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        System.out.println("第三步操作后的i：" + i);
        System.out.println("第三步操作后的二进制：" + Integer.toBinaryString(i));
        i = i + (i >>> 8);
        System.out.println("第四步操作后的i：" + i);
        System.out.println("第四步操作后的二进制：" + Integer.toBinaryString(i));
        i = i + (i >>> 16);
        System.out.println("第五步操作后的i：" + i);
        System.out.println("第五步操作后的二进制：" + Integer.toBinaryString(i));
        // 00000000000000000000000000111111
        i=i & 0x3f;
        System.out.println("第六步操作后的i：" + i);
        System.out.println("第六步操作后的二进制：" + Integer.toBinaryString(i));

        return i;
    }

    public static void main(String[] args) {
        int i = 0xfffff;
        bitCount(i);

    }
}
