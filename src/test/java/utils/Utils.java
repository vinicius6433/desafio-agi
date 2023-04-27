package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import io.cucumber.core.cli.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.simple.JSONArray;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;

public class Utils {
	public static WebDriver driver;

	public static final Logger logger = Logger.getLogger(Utils.class.getName());


	public static void acessarSistema(String ambiente ) {
		String url = getJson("Ambiente/ambiente", ambiente);
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		logger.log(Level.INFO, "Acessou o sistema com sucesso!");
	}
	public static String getJson(String path, String key){
		String chave = "";
		JSONObject jsonObject;
		//Cria o parse de tratamento
		JSONParser parser = new JSONParser();

		try {
			//Salva no objeto JSONObject o que o parse tratou do arquivo
			jsonObject = (JSONObject) parser.parse(new FileReader(
					"dados/"+path+".json"));

			//Salva nas variaveis os dados retirados do arquivo
			chave = (String) jsonObject.get(key);

		}
		//Trata as exceptions que podem ser lançadas no decorrer do processo
		catch (IOException | ParseException e) {
			logger.log(Level.SEVERE, ""+e);
		}
		return chave;
	}

	public static void alterandoDadosNoJson(String Path, String key, String value)  {
		String path = "dados/" + Path + ".json";
		FileWriter writeFile = null;
		JSONObject jsonObject = new JSONObject();
		try{

			// Ler o arquivo JSON
			String content = new String(Files.readAllBytes(Paths.get(path)));
			// Converter o conte?do do arquivo em um objeto JSON
			org.json.JSONObject json = new org.json.JSONObject(content);
			// Alterar o valor de uma chave espec?fica
			json.put(key, value);
			// Salvar o arquivo JSON com a nova linha alterada
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(json.toString());
			fileWriter.flush();
			fileWriter.close();
		}catch (IOException e){
			try{
				writeFile = new FileWriter(path);
				jsonObject.put(key, value);
				//Escreve no arquivo conteudo do Objeto JSON
				writeFile.write(jsonObject.toJSONString());
				writeFile.close();
			}catch (IOException E){
				logger.log(Level.SEVERE, ""+e);
			}
		}
	}
	public static void espera(int valor){
		try {
			Thread.sleep(valor);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	public static WebElement findXpath(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(xpath))));
	}
	public static void validarViaTexto(String textoASerValidado){
		assertEquals(textoASerValidado,findXpath("//*[text()[contains(.,'"+textoASerValidado+"')]]").getText());
	}
}
