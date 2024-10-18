package driverFactory;

import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {
	WebDriver driver;
	String inputpath=".Hybride_Framework\\FileInput/";
	String outputpath="11Hybride_Framework\\FileOutput";
	String TCSheet="Mastercases";
	public void starttest() throws Throwable
	{
		String ModelStatus="";
		String ModelNew="";
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		for(int i=1;i<=xl.rowcount(TCSheet);i++)
		{
			if(xl.getcelldata(TCSheet, i, 2).equalsIgnoreCase("y"))
			{
				for(int j=1;j<=xl.rowcount(TCSheet);j++)
				{
					String destination=xl.getcelldata(TCSheet, j, 0);
					String objectType=xl.getcelldata(TCSheet, j, 1);
					String ltype=xl.getcelldata(TCSheet, j, 2);
					String lvalue=xl.getcelldata(TCSheet, j, 3);
					
					String testdata=xl.getcelldata(TCSheet, j, 4);
					try {
						if(objectType.equalsIgnoreCase("startbroser"))
						{
							driver=FunctionLibrary.startBrowser();
						}
						if(objectType.equalsIgnoreCase("openurl"))
						{
							driver=FunctionLibrary.openUrl();
						}
						if(objectType.equalsIgnoreCase("waitforelement"))
						{
							driver=FunctionLibrary.waitForElement(ltype, lvalue, testdata);
						}
						if(objectType.equalsIgnoreCase("typeAction"))
						{
							driver=FunctionLibrary.typeAction(ltype, lvalue, testdata);
						}
						if(objectType.equalsIgnoreCase("clickaction"))
						{
							driver=FunctionLibrary.clickAction(ltype, lvalue);
						}
						if(objectType.equalsIgnoreCase("validtitle"))
						{
							driver=FunctionLibrary.validateTitle(testdata);
						}
						if(objectType.equalsIgnoreCase("closebroser"))
						{
							driver=FunctionLibrary.closeBrowser();
						}
						xl.setcelldata(TCSheet, j, 5, "pass", outputpath);
						Statusmodel="true";
					} catch (Exception e) {
						System.out.println(e.getMessage());
						xl.setcelldata(TCSheet, j, 5, "fail", outputpath);
						ModelNew="false";
					}
					
					if(Module_Status.equalsIgnoreCase("True"))
					{
						//write as pass into TCsheet in Status cell
						xl.setcelldata(TCSheet, i, 3, "Pass", outputpath);
					}
				}
				if(Module_New.equalsIgnoreCase("False"))
				{
					//write as Fail into TCsheet in Status cell
					xl.setcelldata(TCSheet, i, 3, "Fail", outputpath);
				}
			}
			else
			{
				//write as blocked for testcases which are flag to N
				xl.setcelldata(TCSheet, i, 3, "Blocked", outputpath);
			}

				}
				
			}
		}
	}
	

}
