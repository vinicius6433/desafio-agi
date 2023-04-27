package PageObjects;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.runtime.model.ExceptionDetails;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static utils.Utils.*;

public class lupaPage {

    public lupaPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "search-open")
    private  WebElement btnLupa;
    @FindBy(css = "[class='desktop-search']")
    private  WebElement desktopSearch;
    @FindBy(css = "input[type='search']")
    private  WebElement inputPesquisar;
    @FindBy(css = "[class='desktop-search'] [value='Pesquisar']")
    private  WebElement btnPesquisarDesktop;

    @FindBy(css = "[class='error-code']")
    private WebElement errorCode;
    @FindBy(xpath = "//h1")
    private  WebElement error;

    public void validarUrl(String url){
        WebDriverWait webDriver = new WebDriverWait(driver, Duration.ofSeconds(1));
        if(webDriver.until(ExpectedConditions.urlContains(url))){
            logger.log(Level.INFO, "Validação efetuada");
        };

    }
    public void acionarLupa(){
        btnLupa.click();
    }
    public void validarDisparadaDeEventos(){
        //Verificação se a disparada de eventos mudou a classe desktop-search
        String displayValue = desktopSearch.getCssValue("display");
        assertEquals("block", displayValue);
    }
    public void pesquisar(String pesquisa){
        inputPesquisar.clear();
        validarDados(pesquisa);
    }
    public void btnPesquisar(String valores){
        if(!valores.equals("ENTER")){
            btnPesquisarDesktop.click();
        }
    }
    public void validarPesquisa(String key){

        switch(key) {
            case "ENTER":
                validarUrl("https://blogdoagi.com.br/?s=");
                break;
            case "SPACE":
                validarUrl("https://blogdoagi.com.br/?s=");
                break;
            case "BUG":
                assertEquals("414 Request-URI Too Large", error.getText());
                break;
            case "BUG 2":
                assertEquals("ERR_CONNECTION_CLOSED",errorCode.getText());
                break;
            case "ME CONTRATA":
                validarUrl("https://blogdoagi.com.br/?s=Agrade%C3%A7o+pela+oportunidade+de+mostrar+minhas+capacidades+t%C3%A9cnicas+agrade%C3%A7o+a+Mari+pela+%C3%B3tima+entrevista+%3AD");
                validarViaTexto("Nenhum resultado");
                break;
            default:
                throw new RuntimeException("Por favor digite um valor válido de acordo com o método lupaPage.validarPesquisa()");
        }
    }
    private void validarDados(String key){
        Actions actions = new Actions(driver);
        switch(key) {
            case "ENTER":
                inputPesquisar.sendKeys(Keys.ENTER);
                break;
            case "SPACE":
                actions.sendKeys(" ");
                break;
            case "BUG":
                System.out.println("ERROR 414 Bug no limite de caracteres, proveniente a falta de parametros no plugin do wordpress... :D");
                inputPesquisar.sendKeys(getJson("Data/dadosBug","olhaOBug"));
                break;
            case "BUG 2":
                System.out.println("ERR_CONNECTION_CLOSED falta limitação no HTML, o triste dos CMS :D");
                inputPesquisar.sendKeys(getJson("Data/dadosBug","caiuAquiCaiuAi"));
                break;
            case "ME CONTRATA":
                inputPesquisar.sendKeys("Agradeço pela oportunidade de mostrar minhas capacidades técnicas agradeço a Mari pela ótima entrevista :D");
                break;
            default:
                throw new RuntimeException("Por favor digite um valor válido de acordo com o método lupaPage.validarDados()");
        }
    }

}
