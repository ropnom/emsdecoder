package dates;

import java.io.ObjectInputStream.GetField;

public class test {

	private static int currentbit = 0;
	private static byte[] binarymessage;
	private static String stringbinarymessage;

	public static byte[] toByteArray(String s) {
		byte data[] = new byte[(int) Math.ceil(s.length() / 2.0)];
		int i;

		for (i = 0; i < (s.length() - 1); i += 2) {
			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
		}
		if (s.length() % 2 == 1)
			data[data.length - 1] = (Integer.decode("0x" + s.charAt(i) + 0)).byteValue();

		return data;
	}

	public static String toBinaryString(byte[] n) {

		String retorno = "";
		for (int i = 0; i < n.length; i++) {
			retorno += String.format("%8s", Integer.toBinaryString((n[i] + 256) % 256)).replace(' ', '0');
		}

		return retorno;
	}

//	public static int Getbits(int numbits) {
//
//		// calculate initbit and number of bytes that we need
//		int initbits = currentbit % 8;
//		int numbytes = 1 + (numbits) / 8;
//		byte required[] = new byte[numbytes];
//
//		String bits = (String) stringbinarymessage.subSequence(currentbit, currentbit+numbits);
//		
//		return Integer.parseUnsignedInt('02',bits);
//		
//	}

	public static final int byteToInt(byte[] b) {

		int l = 0;
		for (int i = 0; i < b.length; i++) {
			l |= b[i] & 0xFF;
			if (i < b.length - 1)
				l <<= 8;
		}
		return l;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String mensaje = "F";
		binarymessage = toByteArray(mensaje);
		stringbinarymessage = toBinaryString(binarymessage);
		
		System.out.println(stringbinarymessage);
		System.out.println(Getbits(4));
	}

}
