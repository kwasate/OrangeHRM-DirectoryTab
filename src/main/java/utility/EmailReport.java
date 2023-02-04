package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import com.prolifics.ProlificsSeleniumAPI;

public class EmailReport {

	@Test
	public void afterSuite() throws IOException {
		int TestCaseCount = 0, PassCount = 0, FailCount = 0;
		Generic gen = new Generic();

		String pattern = "dd-MM-yy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.println("In After Suite");

		File folder = new File(System.getProperty("user.dir") + "\\target\\");

		// create object of Path
		Path path = Paths.get(System.getProperty("user.dir") + "\\target\\HTMLEmailableReport.html");

		// deleteIfExists File
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int TotalFailed = 0;
		int TotalPassed = 0;
		File[] listOfFiles = folder.listFiles();
		String LogString = "";
		String PreviousLine = "";
		System.out.println(listOfFiles.length);
		for (int j = 0; j < listOfFiles.length; j++)

		{
			int ScenarioCount = 0;
			int NumberOfFailedScenarios = 0;
			int NumberOfPassedScenarios = 0;
			System.out.println(listOfFiles[j].getName());

			if (listOfFiles[j].isFile() && listOfFiles[j].getName().contains(".html")) {
				Path sourcePath = Paths.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName());
				Path destinationPath = Paths
						.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName());

				List<String> allLines = Files.readAllLines(
						Paths.get(System.getProperty("user.dir") + "\\target\\" + listOfFiles[j].getName()));
				boolean LogStarted = false;
				int Result = 0;
				String ScriptStatus = "";
				boolean ScenarioStatus = true, ScenarioFailed = false;

				String FailedSteps = "", LogStep = "";
				boolean StartLog = false, LoggStep = false;
				String CurrentLine = "";
				for (String line : allLines) {
					// System.out.println(line);
					if (line.contains("2%") && line.contains("table_cell")) {
						StartLog = true;
					}
					int LogStepCount = 0;
					if (line.contains("Pass") || line.contains("Fail")) {
						StartLog = false;
						CurrentLine = line;
						if (line.contains("Fail")) {
							LoggStep = true;
							LogStepCount++;
						} else {
							LoggStep = true;
						}
					}
					if (StartLog == true)
						LogStep = LogStep + line;
					if (LoggStep == true && StartLog == false) {
						int first = LogStep.indexOf("36%");
						int second = LogStep.indexOf("36%", first + 1);
						if (second != -1 && LogStepCount > 0) {
							// System.out.println(line);
							int third = LogStep.indexOf("12px", second + 1);
							FailedSteps = FailedSteps + LogStep.substring(second + 5, third - 35) + "<br>";

						}
						LogStep = "";
						LoggStep = false;
					}
					if (line.contains("Result"))
						LogStarted = true;
					if (LogStarted == true)
						Result++;
					if (line.contains("table_cell_header")) {
						ScenarioCount++;
						ScenarioStatus = true;
						ScenarioFailed = false;
						StartLog = false;
						LogStep = "";
						LoggStep = false;
					}

					if (LogStarted == true && Result == 5) {
						System.out.println(listOfFiles[j].getName() + " " + line);
						ScriptStatus = line.trim();
					}
					if (line.contains("Fail") && ScenarioFailed == false) {
						NumberOfFailedScenarios++;
						ScenarioFailed = true;
					}
					PreviousLine = line;
				}
				ScenarioCount--;
				TestCaseCount = TestCaseCount + ScenarioCount;
				if (NumberOfFailedScenarios > 0)
					NumberOfFailedScenarios--;
				NumberOfPassedScenarios = ScenarioCount - NumberOfFailedScenarios;
				System.out.println(NumberOfFailedScenarios);
				/*
				 * String FileNameWithTimeStamp = listOfFiles[j].getName().substring(0,
				 * listOfFiles[j].getName().length() - 10);
				 */
				// FileNameWithTimeStamp = listOfFiles[j].getName().substring(0,
				// FileNameWithTimeStamp.lastIndexOf("_"));

				// LogString = LogString + "<tr><td style=\"font-family:Century Gothic;\">"+
				// listOfFiles[j].getName().substring(0, listOfFiles[j].getName().length() - 5)
				// + "</td>";
				String fileName = listOfFiles[j].getName().substring(0, listOfFiles[j].getName().length() - 5);

				LogString = LogString + "<tr><td style=\"font-family:Century Gothic;\"><a href='" + fileName + ".html'>"
						+ fileName + "</a></td>";
				if (ScriptStatus.contains("Pass")) {
					LogString = LogString + "<td align=center bgcolor=\"#b3ffb3\">" + ScriptStatus + "</td>";
				} else if (ScriptStatus.contains("Fail")) {
					LogString = LogString + "<td align=center bgcolor=\"#ffcccc\">" + ScriptStatus + "</td>";
				}
				LogString = LogString + "<td align=center>" + String.valueOf(ScenarioCount) + "</td>"
						+ "<td align=center bgcolor=\"#b3ffb3\">" + String.valueOf(NumberOfPassedScenarios) + "</td>"
						+ "<td align=center bgcolor=\"#ffcccc\">" + String.valueOf(NumberOfFailedScenarios) + "</td>"
						+ "<td align=left >" + FailedSteps + " </td>" + "</tr>";
				TotalFailed = TotalFailed + NumberOfFailedScenarios;
				TotalPassed = TotalPassed + NumberOfPassedScenarios;
			}

		}

		// HTML Email building

		String HTMLContent = "<html ><head><title style=\"font-family:Century Gothic;\">OrangeHRM Automation - Test Automation Results</title>"
				+ "<style> html *{font-size: 1em ;color: #000 ;font-family: Microsoft JhengHei;} </style>" + "</head>";
		HTMLContent = HTMLContent + "<body><br><br>";
		HTMLContent = HTMLContent + "<p style=\"font-family:Microsoft JhengHei;\">Hi Team "
				+ "<br> Please find the OrangeHRM execution report </p>";
		HTMLContent = HTMLContent + "<p style=\"font-family:Microsoft JhengHei;\">Summary Report for Build <b># "
				+ System.getenv("BUILD_NUMBER") + " </b></p>";

		HTMLContent = HTMLContent + "<font face=\"Century Gothic\"><table border=1>";
		ProlificsSeleniumAPI oPSelFW = null;
		// first headder row
		HTMLContent = HTMLContent + "<tr><td align=center bgcolor=\"#e6faff\"><b>Environment</b></td><td>"
				+ gen.getPropertyValue("eComURL", oPSelFW) + "</td></tr>";

		// summary table end
		HTMLContent = HTMLContent + "</table><br><br></font>";

		// summary table start
		HTMLContent = HTMLContent + "<font face=\"Century Gothic\"><table border=1>";
		// first headder row
		HTMLContent = HTMLContent
				+ "<tr align=center bgcolor=\"#e6faff\"><td ><b>Total Number of TC's</b></td><td><b>Number of Pass TC's</b></td><td><b>Number of Fail TC's</b></td></tr>";
		// second row
		HTMLContent = HTMLContent + "<tr><td align=center>" + TestCaseCount + "</td>"
				+ "<td align=center bgcolor=\"#b3ffb3\">" + String.valueOf(TotalPassed) + "</td>"
				+ "<td align=center bgcolor=\"#ffcccc\">" + String.valueOf(TotalFailed) + "</td></tr>";
		// summary table end
		HTMLContent = HTMLContent + "</table></font>";
		// Execution Report heading
		HTMLContent = HTMLContent
				+ "<h3 style = \"font-family:Century Gothic;\">OrangeHRM Test Automation Execution Report</h3>";
		// Execution Report table start
		HTMLContent = HTMLContent + "<font face=\"Century Gothic\"><table border=1>";
		// first headder row
		HTMLContent = HTMLContent
				+ "<tr align=center bgcolor=\"#e6faff\"><td><b>Functionality</b></td><td><b>Status</b></td><td width = '15%'><b>Total No. of TC's</b></td><td width = '15%'><b>No. of TC's Passed</b></td><td width = '15%'><b>No. of TC's Failed</b></td><td><b>Failure Reason</b></td></tr>";
		HTMLContent = HTMLContent + LogString + "</table></font>";
		// thanks message
		HTMLContent = HTMLContent + "<br><br><p style = \"font-family:Century Gothic;\">Thanks,<br>QE Team</p>";
		// end of html tag
		HTMLContent = HTMLContent + "</body></html>";

		BufferedWriter out = new BufferedWriter(
				new FileWriter(System.getProperty("user.dir") + "//target//HTMLEmailableReport.html"));
		out.write(HTMLContent);
		out.close();
		// System.out.println(HTMLContent);
	}
}
