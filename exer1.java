import java.util.Random;
import java.util.Scanner;

public class exer1 {

    public static void main(String[] args) {
 
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int tenis;

        System.out.println("=========================================================================");
        System.out.println("PLATAFORMA BRASILEIRA DE MONITORAMENTO DE DISPONIBILIDADE E ESTOQUE DE TÊNIS[cite: 1]");
        System.out.println("=========================================================================\n");
        
        System.out.print("INSIRA O MODELO DESEJADO: ");
        String modeloDesejado = scanner.nextLine();

        System.out.print("INSIRA O TAMANHO NORMALIZADO (BR)[cite: 4]: ");
        int tamanhoDesejado = scanner.nextInt();

        System.out.print("INSIRA O SEU TETO ORÇAMENTÁRIO (R$)[cite: 2]: ");
        double tetoPreco = scanner.nextDouble();

        System.out.println("\nProcessando a busca por " + modeloDesejado + " no tamanho BR " + tamanhoDesejado + "...\n");

    
        int estoqueInterno = random.nextInt(3); 

        if (estoqueInterno > 0) {
            System.out.println("✓ PRODUTO LOCALIZADO NO ESTOQUE PRÓPRIO ESPECIALIZADO.[cite: 3]");
            System.out.println("Temos " + estoqueInterno + " par(es) disponível(is) em nosso catálogo interno para o tamanho " + tamanhoDesejado + "!");
        } else {
            System.out.println("✕ Produto indisponível internamente.");
            System.out.println("ACIONANDO BUSCADOR NATIVO INTEGRADO PARA VARREDURA EXTERNA...[cite: 1]\n");
            

            double precoExterno = 200.0 + (random.nextDouble() * 1300.0);

            System.out.printf("O Buscador Nativo encontrou o produto em uma loja parceira por: R$ %.2f\n", precoExterno);

            if (precoExterno <= tetoPreco) {
                System.out.println("\nPRODUTO ENCONTRADO DENTRO DO ORÇAMENTO![cite: 5]");
                System.out.println("--------------------------------------------------");
                System.out.println("Modelo: " + modeloDesejado + "[cite: 5]");
                System.out.println("Tamanho Confirmado: BR " + tamanhoDesejado + "[cite: 5]");
                System.out.printf("Origem: Buscador Nativo (Varejista Externo: Loja X) | Preço: R$ %.2f\n", precoExterno); 
                System.out.println("[Botão: Ir para a Loja parceira ][cite: 5]");
                System.out.println("--------------------------------------------------");
            } else {
                System.out.println("\nPRODUTO ENCONTRADO, MAS ACIMA DO SEU TETO ORÇAMENTÁRIO (R$ " + tetoPreco + ").");
                System.out.println("A REGISTRAR ALERTA E A INICIAR MONITORAMENTO CONTÍNUO...[cite: 3]");
                System.out.println("Você será notificado assim que o preço baixar ou houver nova reposição no mercado.[cite: 3]");
            }
        }

        scanner.close();
    }
}
