
import java.util.Scanner;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        String usuario, senha, perfil = "", resposta;
        String sku = "HDMI-2.1-001";
        String categoria = "HDMI";

        int estoque = 50;
        int quantidade;
        int unidadesVendidas = 0;
        int vendas = 0;

        double precoBase = 100.0;

        // Taxas ficticias para demonstracao
        double icms = 18.0;
        double pis = 1.65;
        double cofins = 7.6;

        double subtotal, imposto, total;
        double faturamento = 0.0;
        double impostosAcumulados = 0.0;

        boolean continuar = true;

        // 1. LOGIN DO SISTEMA
        System.out.println("=== LOGIN DO SISTEMA ===");

        System.out.print("Usuario: ");
        usuario = entrada.nextLine();

        System.out.print("Senha: ");
        senha = entrada.nextLine();

        // 2. VALIDACAO DO USUARIO
        if (usuario.equals("ADMIN") && senha.equals("123")) {
            perfil = "ADMINISTRADOR";

        } else if (usuario.equals("VENDEDOR") && senha.equals("456")) {
            perfil = "VENDEDOR";

        } else if (usuario.equals("CLIENTE") && senha.equals("789")) {
            perfil = "CLIENTE";

        } else {
            System.out.println("Usuario ou senha incorretos.");
            entrada.close();
            return;
        }

        System.out.println("\nBem-vindo, " + perfil + "!");

        // 3. CLIENTE REALIZA A COMPRA
        if (perfil.equals("CLIENTE")) {

            System.out.println("\n=== AREA DO CLIENTE ===");
            System.out.println("Bem-vindo a nossa loja!");

        } else {

            System.out.println("\n=== AREA DE VENDAS ===");
        }

        // 4. PROCESSO DE COMPRA OU VENDA
        while (continuar && estoque > 0) {

            System.out.println("\n=== PRODUTO DISPONIVEL ===");
            System.out.println("Produto: " + categoria);
            System.out.println("SKU: " + sku);
            System.out.println("Preco unitario: R$ " + precoBase);
            System.out.println("Estoque disponivel: " + estoque);

            if (perfil.equals("CLIENTE")) {
                System.out.print("Quantidade para comprar: ");
            } else {
                System.out.print("Quantidade para venda: ");
            }

            // Validacao da quantidade
            try {
                quantidade = Integer.parseInt(
                    entrada.nextLine().trim()
                );
            } catch (NumberFormatException e) {
                System.out.println(
                    "Quantidade invalida. Digite um numero inteiro."
                );
                continue;
            }

            if (quantidade <= 0) {

                System.out.println(
                    "Digite uma quantidade maior que zero."
                );

            } else if (quantidade > estoque) {

                System.out.println(
                    "Estoque insuficiente. Disponivel: " + estoque
                );

            } else {

                boolean compraConfirmada = true;

                // Cliente confirma o pedido antes de comprar
                if (perfil.equals("CLIENTE")) {

                    System.out.print(
                        "Confirmar compra? (S/N): "
                    );

                    resposta = entrada.nextLine().trim();

                    if (resposta.equalsIgnoreCase("N")) {
                        compraConfirmada = false;

                    } else if (resposta.equalsIgnoreCase("S")) {
                        compraConfirmada = true;

                    } else {
                        compraConfirmada = false;
                        System.out.println(
                            "Opcao invalida. Compra cancelada."
                        );
                    }
                }

                if (!compraConfirmada) {

                    System.out.println("Compra nao realizada.");

                } else {

                    // 5. CALCULO DOS VALORES
                    subtotal = precoBase * quantidade;

                    imposto = subtotal
                        * (icms + pis + cofins) / 100.0;

                    total = subtotal + imposto;

                    // 6. ATUALIZACAO DO ESTOQUE
                    estoque = estoque - quantidade;

                    unidadesVendidas += quantidade;
                    vendas++;

                    faturamento += total;
                    impostosAcumulados += imposto;

                    // 7. RESUMO DA OPERACAO
                    System.out.println("\n=== RESUMO DA OPERACAO ===");

                    System.out.printf(
                        Locale.forLanguageTag("pt-BR"),
                        "Subtotal: R$ %.2f%n", subtotal
                    );

                    System.out.printf(
                        Locale.forLanguageTag("pt-BR"),
                        "Impostos simulados: R$ %.2f%n", imposto
                    );

                    System.out.printf(
                        Locale.forLanguageTag("pt-BR"),
                        "Total a pagar: R$ %.2f%n", total
                    );

                    System.out.println(
                        "Estoque restante: " + estoque
                    );

                    if (perfil.equals("CLIENTE")) {
                        System.out.println(
                            "Compra registrada com sucesso!"
                        );
                    }

                    if (estoque < 10) {
                        System.out.println(
                            "ALERTA: estoque baixo! Necessario reposicao."
                        );
                    }
                }
            }

            // 8. VERIFICAR SE DESEJA CONTINUAR
            if (estoque > 0) {

                do {
                    System.out.print(
                        "\nDeseja realizar outra operacao? (S/N): "
                    );

                    resposta = entrada.nextLine().trim();

                } while (
                    !resposta.equalsIgnoreCase("S")
                    && !resposta.equalsIgnoreCase("N")
                );

                continuar = resposta.equalsIgnoreCase("S");
            }
        }

        if (estoque == 0) {
            System.out.println("\nEstoque esgotado.");
        }

        // 9. RELATORIO EXCLUSIVO DO ADMINISTRADOR
        if (perfil.equals("ADMINISTRADOR")) {

            System.out.println("\n=== RELATORIO DE VENDAS ===");

            System.out.println("Vendas realizadas: " + vendas);

            System.out.println(
                "Unidades vendidas: " + unidadesVendidas
            );

            System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Faturamento com impostos simulados: R$ %.2f%n",
                faturamento
            );

            System.out.printf(
                Locale.forLanguageTag("pt-BR"),
                "Impostos simulados acumulados: R$ %.2f%n",
                impostosAcumulados
            );

            System.out.println("Estoque final: " + estoque);
        }

        System.out.println("\nSistema encerrado.");

        entrada.close();
    }
}
