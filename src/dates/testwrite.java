package dates;

import Model.Message;

public class testwrite {

//	public static byte[] toByteArray(String s) {
//		
//		byte data[] = new byte[(int) Math.ceil(s.length() / 2.0)];
//		int i;
//		
//		for (i = 0; i < (s.length()-1); i += 2) {
//			data[i / 2] = (Integer.decode("0x" + s.charAt(i) + s.charAt(i + 1))).byteValue();
//		}
//		if(s.length() %2==1)
//			data[data.length-1] = (Integer.decode("0x" + s.charAt(i) + 0)).byteValue();
//		
//		return data;
//
//	}

//	public static String toBinaryString(byte n) {
//
//		return String.format("%8s", Integer.toBinaryString((n + 256) % 256)).replace(' ', '0');
//	}

	public static void main(String[] args) throws InterruptedException {

//		String hex = "F0F";
//		//String hex = "530C3FE0003FC7FEBFE4003FDC000000020003FF80017999F9FB9B97885";
//		byte[] mensajebin = new byte[hex.length()];
//		
//		System.out.println("Test program decode he to binary");
//		System.out.println(hex);
//		System.out.println(hex.length());
//		
//		mensajebin = toByteArray(hex);
//		System.out.println(mensajebin.length);
//
//		for (int i = 0; i < mensajebin.length; i++) {
//			System.out.println("Byte number "+ i +" "+ toBinaryString( mensajebin[i]));
//
//		}
		
		Message message = new Message("120 14 10 31 23 00 11 26 9A6A53FDFFEF05683741780AC04601F017A0FE0977FBFFDFFEFFF78011657800", 1);
		System.out.println(message.WriteHumanFile());
		
		

	}
}
