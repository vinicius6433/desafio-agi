#language: pt
#encoding: UTF-8

@DesafioAgibank
@AutomacaoLupa
Funcionalidade: Automacao Lupa
  Contexto:
    Dado que estou na url "https://blogdoagi.com.br/"

  @VerificarDisparadaDeEventosAoClicarNaLupa
  Cenario: Verificar disparada de eventos ao clicar na lupa
    Quando acionar a lupa
    Entao verifico a disparada de eventos no clique da lupa

  @VerificarCenariosDePesquisa
  Esquema do Cenario: Cenario: Verificar Cenarios de Pesquisa
    Quando acionar a lupa
    E digitar "<valores>"
    Entao pesquiso e valido as informacoes da pesquisa de "<valores>"

    Exemplos:
      |valores      |
      |ENTER        |
      |SPACE        |
      |BUG          |
      |BUG 2        |
      |ME CONTRATA  |