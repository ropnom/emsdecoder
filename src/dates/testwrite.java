package dates;

import javax.xml.bind.DatatypeConverter;

public class testwrite {

	public static byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}

	public static String toBinaryString(byte n) {
		
		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}

	public static void main(String[] args) throws InterruptedException {

		byte[] mensajebin = new byte[2];
		String hex = "5F06";
		System.out.println(hex);
		mensajebin = toByteArray(hex);

		for (int i = 0; i < mensajebin.length; i++) {
			System.out.println(toBinaryString(mensajebin[i]));
			System.out.println();
	
		}

	}
}
