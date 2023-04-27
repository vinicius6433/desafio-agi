package utils;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static utils.Utils.*;

public class GerarMassaDeDados {
    @Test
    public void main() {
        // Define a URL base da API
        RestAssured.baseURI = "http://geradorbrasileiro.com";
            // Faz uma solicitação GET para a URL /api/faker/pessoa
            Response response = RestAssured.given()
                    .when()
                    .get("/api/faker/pessoa")
                    .then()
                    .extract()
                    .response();

            // Verifica se o código de status da resposta é 200 (OK)
            System.out.println(response.then().statusCode(200));

            String pai = response.path("values[0].pai");
            pai = pai.replaceAll("[^\\w\\s]", "");
            String mae = response.path("values[0].mae");
            mae = mae.replaceAll("[^\\w\\s]", "");
            String cpf = response.path("values[0].cpf");
            cpf = cpf.replaceAll("[^\\w\\s]", "");
            String birthDate = response.path("values[0].dataNascimento");
            birthDate = birthDate.replaceAll("/", "");

            String phoneNumber = response.path("values[0].celular");
            phoneNumber = phoneNumber.replaceAll("[^\\d]+", "");

            alterandoDadosNoJson("massaDados/dados", "nomeM" , pai);
            alterandoDadosNoJson("massaDados/dados", "nomeF" , mae);
            alterandoDadosNoJson("massaDados/dados", "cpf" , cpf);
            alterandoDadosNoJson("massaDados/dados", "dataAniversario" , birthDate);
            alterandoDadosNoJson("massaDados/dados", "numeroDeCelular" , phoneNumber);
        }
    }
