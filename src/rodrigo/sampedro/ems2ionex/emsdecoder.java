package rodrigo.sampedro.ems2ionex;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Model.Jget;
import Model.LoadDataFile;
import Model.Message;
import Model.WriteCurrentData;

public class emsdecoder {

	private static float porcent = 0;
	private static int count = 0;
	private static int visual = 3600;// 3600 lines each file
	private static boolean show = false;

	// DECLARE VARIABLES IN PROGRAM

	private static String server = "ftp://ems.estec.esa.int/pub/";
	private static String prn = "PRN120/";
	private static int day = 12;
	private static int hour = 0;

	private static int mode = 1;// file mode

	private static int inityear = Calendar.getInstance().get(Calendar.YEAR);
	private static int initday = 3;
	private static int endday = 3;
	private static int inithour = 23;
	private static int endhour = 23;
	private static int MAXDAY = 365;
	private static List<String> originalmessage = new ArrayList<String>();

	// DECODE Message
	private static Message message = null;
	private static List<String> human = new ArrayList<String>();
	private static List<String> message26 = new ArrayList<String>();

	// FUNCTIONS

	public static void PrintHelp() {
		System.out.println("-------------------------------------------------------------------------------------");
		System.out.println(" ***                      EMS DECODER 0.01 ALFA  --HELP                           ***");
		System.out.println(" -------------------------------------------------------------------------------------");
		System.out.println("");
		System.out.println(" --ModeFile  : Use currentmessage.txt as server of Egnos information.");
		System.out.println(" -Show		: Show in the screen the % of download and events.");
		System.out.println(" -PRN120		: Use Egnos Message from PRN120 (by default).");
		System.out.println(" -PRN126		: Use Egnos Message from PRN126.");
		System.out.println(" -TODAY		: Download the EMS message from 23h 2 days ago until 0:00 of today.");
		System.out.println(" -Y XXXX		: Specific Year where XXXX is per example 2012.");
		System.out.println(" -D XXX		: Specific Day, number of the day 0-365 (366) days of the year.");
		System.out.println(" -H			: Specific Hour, number of hours 0-23 h.");
		System.out.println("");
		System.out.println(" Example:");
		System.out.println(" emsdecoder -Show -PRN126 -TODAY");
		System.out.println(" emsdecoder -Show -PRN126 -Y 2014 -D 324 -H 15");
	}

	public static synchronized void Countline() {

		count++;
		if (show) {
			int i = ((100 * count) / visual);
			float newporcent = (100 * (float) count) / visual;

			if (newporcent - porcent > 0.01) {
				porcent = newporcent;
				ViewonScreen(i);
			}
		}

	}

	public static void ViewonScreen(int i) {

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
			// e.printStackTrace();
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

		System.out.print("  " + String.format("%.3f", porcent) + "% Downloading...");

		if (i >= 100) {
			System.out.print("+  " + String.format("%.2f", porcent) + "% COMPLETE");
			System.out.println("END");
		}
	}

	// ------------------------------ MAIN --------------------------------

	public static void main(String[] args) {

		boolean debug = true;

		if (debug) {
			// DEMO debug

			// *********** INPUT TEST PROGRAM AND MENUS ***********
			args = new String[] { "-PRN120", "-TODAY" };
			// args = new String[] { "-PRN126", "-TODAY" };
			// args = new String[] { "-PRN120", "-D" , "25"};
			// args = new String[] { "-PRN120", "-D" , "25", "-H", "14"};

			// *************************************************
		}

		// Check input arguments
		if ((args.length == 0)) {

			// TODO: FALTAN LOS MENSAJES DE SALIDA HELP
			PrintHelp();
			System.err.println("\n Input Argument Error use --HELP");
			System.exit(1);
		}

		// Check argmument input
		for (int i = 0; i < args.length; i++) {
			// MODE
			if (args[i] == "-ModeFile")
				mode = 1;
			// Show on screen
			if (args[i] == "-Show")
				show = true;
			// PRN satellites
			if (args[i] == "-PRN120")
				prn = "PRN120/";
			if (args[i] == "-PRN126")
				prn = "PRN126/";
			// Today
			if (args[i] == "-TODAY") {
				int today = Calendar.getInstance().get(Calendar.DAY_OF_YEAR) - 1;
				if (today == 0) {
					inityear = inityear - 1;
					initday = 365;
					if (inityear % 4 == 0 && inityear % 100 != 0 || inityear % 400 == 0) {
						initday = 366;
					}
					endday = 1;
				} else {
					initday = today - 1;
					endday = today;
				}
			}
			// Year
			try {
				if (args[i] == "-Y") {
					inityear = Integer.parseInt(args[i + 1]);
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);

			}
			// Day
			try {
				if (args[i] == "-D") {
					int today = Integer.parseInt(args[i + 1]);
					initday = today;
					endday = today;
					inithour = 0;
					endhour = 23;
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);
			}
			// Hour
			try {
				if (args[i] == "-H") {
					int h = Integer.parseInt(args[i + 1]);
					inithour = h;
					endhour = h;
				}
			} catch (Exception e) {
				System.err.println("\n Input format was Wrong.");
				System.err.println("\n Use -help.");
				System.exit(1);
			}
		}

		if (show) {
			// Download algoritm item precalculate
			if (endday > initday)
				visual = visual * ((24 - inithour) + endhour + 24 * (endday - initday));
			else
				visual = visual * (endhour - inithour);
		}

		// -----------------------------------------------------------------------------------------------------------------------

		// PREPARE DOWNLOAD LIST FILES AND DECODER
		if (mode == 0) {
			// Configure Jget
			Jget wget = new Jget();
			// calcule if the day have 365 or 366 days
			if (inityear % 4 == 0 && inityear % 100 != 0 || inityear % 400 == 0) {
				MAXDAY = 366;
			}
			boolean lastday = false;
			boolean lasthour = false;
			for (day = initday; !lastday; day++) {
				lasthour = false;
				for (hour = inithour; !lasthour; hour++) {
					String url = server + prn + "y" + inityear + "/d" + day + "/h" + String.format("%02d", hour) + ".ems";
					// MAKE DOWLOAD
					originalmessage.addAll(wget.Dowload(url));
					if (hour == endhour || hour == 23) {
						lasthour = true;
					}
				}
				inithour = 0;
				if (day == endday) {
					lastday = true;
				}
				if (day == MAXDAY) {
					day = 1;
				}
			}
			// Write Current Data
			WriteCurrentData writer = new WriteCurrentData();
			writer.Write(originalmessage);
		} else if (mode == 1) {
			// LOAD DATA FROM FILE
			LoadDataFile load = new LoadDataFile();
			originalmessage = load.LoadData();
		}

		// ------------------------------------------------------------------------------------

		for (int i = 0; i < originalmessage.size(); i++) {
			message = new Message(originalmessage.get(i), i);
			human.addAll(message.WriteHumanFile());
		}
		// Write Current Data
		WriteCurrentData writer = new WriteCurrentData();
		writer.setFilename("humanmessage.txt");
		writer.Write(human);

		System.out.println("FIN **");

	}
}
