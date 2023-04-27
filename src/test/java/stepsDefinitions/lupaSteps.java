package stepsDefinitions;

import PageObjects.lupaPage;
import io.cucumber.java.es.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.jetbrains.annotations.NotNull;

import static utils.Utils.espera;


public class lupaSteps {
    lupaPage lp = new lupaPage();

    @Dado("que estou na url {string}")
    public void queEuEstejaLogadoNaPaginaDo(String url) {
        lp.validarUrl(url);
    }

    @Quando("acionar a lupa")
    public void acionarALupa() {
        lp.acionarLupa();
    }

    @Entao("verifico a disparada de eventos no clique da lupa")
    public void verificoADisparadaDeEventosNa() {
        lp.validarDisparadaDeEventos();
    }

    @E("digitar {string}")
    public void digitar(@NotNull String pesquisa) {
        lp.pesquisar(pesquisa);
    }

    @Entao("pesquiso e valido as informacoes da pesquisa de {string}")
    public void pesquisoEValidoAsInformacoesDaPesquisaDe(String valores) {
        lp.btnPesquisar(valores);
        lp.validarPesquisa(valores);
        espera(1000);
    }
}
