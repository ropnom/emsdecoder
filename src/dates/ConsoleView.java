package dates;

public class ConsoleView {

	
	public void View(){
		System.out.println(System.getProperty("os.name").toLowerCase());

		float porcent = 0;
		for (int i = 0; i < 73; i++) {

			try {
				
				Process exitCode;
				Runtime r = Runtime.getRuntime();
				if (System.getProperty("os.name").startsWith("Window")) {
					exitCode = r.exec("cls");
				} else {
					exitCode = r.exec("clear");
				}
				System.out.println(exitCode);


			} catch (Exception e) {

				for (int j = 0; j < 200; j++) {
					System.out.println();
				}
				//e.printStackTrace();

			}

			System.out.println("\n");
			System.out.println("  ******************************************");
			System.out.println("  | DONWLOADING FILES FROM ESA SERVER ...  |");
			System.out.println("  ******************************************");
			System.out.println("\n");
			System.out.print("  +");
			for (int l = 0; l < i; l++) {
				System.out.print("-");
			}
			porcent = (100 * (float) i) / 73;

			System.out.print("  " + String.format("%.3f", porcent) + "% Downloading...");

			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		System.out.print("+  " + String.format("%.2f", porcent) + "% COMPLETE");
		System.out.println("END");
	}
}
