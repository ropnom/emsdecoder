package dates;

public class testwrite {

	public static byte[] toByteArray(String s) {
		
		byte data[] = new byte[s.length() / 2];
		
		for (int i = 0; i < (s.length()-1); i += 2) {
			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
		}
		return data;

	}

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}

	public static void main(String[] args) throws InterruptedException {

		// String hex =
		// "530C3FE0003FC7FEBFE4003FDC000000020003FF80017999F9FB9B97885DC";
		String hex = "530C3FE0003FC7FEBFE4003FDC000000020003FF80017999F9FB9B97885";
		byte[] mensajebin = new byte[hex.length()];

		System.out.println(hex);
		mensajebin = toByteArray(hex);

		for (int i = 0; i < mensajebin.length; i++) {
			System.out.println(toBinaryString(mensajebin[i]));

		}

	}
}
