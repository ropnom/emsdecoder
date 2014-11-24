package dates;

public class test {

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		byte a = (byte) 0xff;
		
		byte b = 0xff >>> 4;
		
		System.out.println(toBinaryString(a));
		System.out.println(toBinaryString(b));
		System.out.println(toBinaryString((byte) (a&b)));
	}

}
