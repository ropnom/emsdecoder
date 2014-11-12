package Model;

public class Payload {

	protected String message;
	protected byte[] binarymessage;
	protected int currentbit = 1;

	public Payload() {

	}

	public Payload(String message) {
		this.binarymessage = Payload.toByteArray(message);
		decode();
	}

	public void decode() {

		this.message = "";
		for (int i = 0; i < binarymessage.length; i++) {
			message = message + toBinaryString(binarymessage[i]);
		}
	}

	public String PrintMessage() {

		if (message == null)
			decode();
		message = "BINARY: " + message;
		return message;

	}

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
		int rambytes = 1 + (initbits + numbits) / 8;
		byte required[] = new byte[numbytes];
		byte ram[] = new byte[rambytes];

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
			required[0] = (byte) (ram[0] & (0xFF << (8 - numbits)));
		} else if (initbits == 0 & numbytes > 1) {
			// lo mismo no hay que mover ningun byte solo tener cuidado con el
			// ultimo
			ram[ram.length - 1] = (byte) (ram[ram.length - 1] & (0xFF << ((8 - (numbits % 8)) % 8)));
			required = ram;
		} else if (initbits > 0 && numbytes == 1) {
			// primero ajustamos depsues hacemos lo mismo que antes
			ram[0] = (byte) (ram[0] << initbits);
			ram[0] = (byte) (ram[0] & (0xFF << ((8 - (numbits % 8)) % 8)));
			required = ram;
		} else if (initbits > 0 && numbytes > 1) {

			for (int i = 0; i < numbytes; i++) {
				if (i != numbytes - 1) {
					// esto no funciona
					// required[i] = (byte) ((byte) (ram[i] << initbits) +
					// (byte) (ram[i + 1] >>> (8 - initbits)));

					// ajustamos
					required[i] = (byte) ((byte) (ram[i] << initbits) + (byte) ((ram[i + 1] & 0xff) >>> (8 - initbits)));
				} else {
					// last iteration
					// ajustamos y limitamos los ultimos bits
					required[i] = (byte) (ram[i] << initbits);
					ram[i] = (byte) (ram[i] & (0xFF << (8 - (numbits % 8))));
				}
			}

		}

		currentbit += numbits;
		for (int i = 0; i < required.length; i++) {
			System.out.println(toBinaryString(required[i]));
		}

		return required;
	}

	public static final int byteToInt(byte[] b, int numbits) {

		if (numbits % 8 != 0) {
			b[b.length - 1] = (byte) (b[b.length - 1] >> (8 - numbits % 8));
		}

		int l = 0;
		for (int i = 0; i < b.length; i++) {
			System.out.println(toBinaryString(b[i]));
			l |= b[i] & 0xFF;
			System.out.println(l);
			if (i < b.length - 1)
				l <<= 8;
		}
		System.out.println(l);
		return l;
	}

	// ******** GETS y SETS ****

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getBinarymessage() {
		return binarymessage;
	}

	public void setBinarymessage(byte[] binarymessage) {
		this.binarymessage = binarymessage;
	}

}
