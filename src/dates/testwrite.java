package dates;

import Model.Message;

public class testwrite {

	private static int currentbit = 0;
	private static byte[] binarymessage;

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

	public static String toBinaryString(byte n) {

		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
	}

	public byte[] Getbits(int numbits) {

		// calculate initbit and number of bytes that we need
		int initbits = currentbit % 8;
		int numbytes = 1 + (numbits) / 8;

		int rambytes = (int) Math.ceil((float) (initbits + numbits) / 8.0);

		byte required[] = new byte[numbytes];
		byte ram[] = new byte[rambytes];

		int movidos = ((8 - (numbits % 8)) % 8);

		for (int i = 0; i < rambytes; i++) {
			// get byte from message where are the bits
			ram[i] = binarymessage[currentbit / 8 + i];
			// fist iteration
			// if (i == 0) {
			// move bit to left to make all compact.
			// if (numbits <= 8 & initbits > 0 & numbytes == 1) {
			// required[i] = (byte) (ram[i] << initbits);
			// }
			// }
		}

		// to compact byte response
		if (initbits == 0 & numbytes == 1) {
			required[0] = (byte) (ram[0] & (0xff >>> (8 - numbits)));
		} else if (initbits == 0 & numbytes > 1) {
			// igual solo que hay que mover en todos el desfase hacia la derecha
			for (int i = ram.length - 1; i > 0; i--) {
				// si es el ultimo solo tenemos en cuenta ese byte
				if (i == 0) {
					required[i] = (byte) ((ram[i] & 0xFF) >>> movidos);
				} else {
					required[i] = (byte) ((byte) ((ram[i] & 0xFF) >>> movidos) + (byte) (ram[i - 1] << movidos));
				}
			}

		} else if (initbits > 0 && numbytes == 1) {
			// primero ajustamos depsues hacemos lo mismo que antes
			ram[0] = (byte) (ram[0] << initbits);
			required[0] = (byte) ((ram[0] & 0xFF) >>> movidos);
		} else if (initbits > 0 && numbytes > 1) {

			// Percal...
			for (int i = ram.length - 1; i > 0; i--) {
				// si es el ultimo solo tenemos en cuenta ese byte
				if (i == 0) {
					ram[i] = (byte) (ram[i] << initbits);
					required[i] = (byte) ((ram[i] & 0xFF) >>> movidos);
				} else {
					required[i] = (byte) ((byte) ((ram[i] & 0xFF) >>> movidos) + (byte) (ram[i - 1] << movidos));
				}
			}

		}

		currentbit += numbits;
		for (int i = 0; i < required.length; i++) {
			System.out.println(toBinaryString(required[i]));
		}

		return required;
	}

	public static final int byteToInt(byte[] b) {

		int l = 0;
		for (int i = 0; i < b.length; i++) {
			l |= b[i] & 0xFF;
			if (i < b.length - 1)
				l <<= 8;
		}
		return l;
	}

	public static void main(String[] args) throws InterruptedException {

		byte a = (byte) 0x0F;
		System.out.println(toBinaryString(a));
		System.out.println(a);

		byte b = (byte) (a >> 2);
		byte c = (byte) ((a & 0xff) >>> 2);

		System.out.println(b);
		System.out.println(toBinaryString(b));
		System.out.println(c);
		System.out.println(toBinaryString(c));

		String hex = "C66A43FDFFEFFF7FFBC21A10C08583A81B20D806C836815206902C8038C26880";
		// String hex = "F0F210F0";
		// String hex =
		// "530C3FE0003FC7FEBFE4003FDC000000020003FF80017999F9FB9B97885";
		binarymessage = new byte[hex.length()];

		byte[] compac;

		System.out.println("Test program decode he to binary");
		System.out.println(hex);
		System.out.println(" Longitud: " + hex.length());

		binarymessage = toByteArray(hex);
		System.out.println(" Bytes length: " + binarymessage.length);

		for (int i = 0; i < binarymessage.length; i++) {
			System.out.println("Byte number " + i + " " + toBinaryString(binarymessage[i]));
		}

		// System.out.println("Give me bit 2-8");
		// currentbit = 2;
		// compac = new byte[1];
		// compac = Getbits(6);
		//
		// for (int i = 0; i < compac.length; i++) {
		// System.out.println("Byte number " + i + " " +
		// toBinaryString(compac[i]));
		// }
		//
		// System.out.println("Give me bit 8-18");
		// compac = Getbits(10);
		//
		// for (int i = 0; i < compac.length; i++) {
		// System.out.println("Byte number " + i + " " +
		// toBinaryString(compac[i]));
		// }
		//
		// System.out.println("Give me bit 18-27");
		// compac = Getbits(9);
		//
		// for (int i = 0; i < compac.length; i++) {
		// System.out.println("Byte number " + i + " " +
		// toBinaryString(compac[i]));
		// }

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(toBinaryString(a));
		byte[] num = new byte[1];
		num[0] = a;
		System.out.println(byteToInt(num));

		Message message = new Message("120 14 10 31 23 00 15 26 C66A43FDFFEFFF7FFBC21A10C08583A81B20D806C836815206902C8038C26880", 1);
		for (String print : message.WriteHumanFile()) {
			System.out.println(print);
		}

	}
}
