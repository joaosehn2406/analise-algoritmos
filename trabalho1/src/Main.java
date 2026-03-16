import model.Pedido;
import model.Produto;
import model.TipoEntrega;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final int OPCAO_ADICIONAR_PRODUTO = 1;
    private static final int OPCAO_LISTAR_PRODUTOS = 2;
    private static final int OPCAO_CRIAR_PEDIDO = 3;
    private static final int OPCAO_ADICIONAR_PRODUTO_PEDIDO = 4;
    private static final int OPCAO_CALCULAR_FRETE = 5;
    private static final int OPCAO_LISTAR_PEDIDOS = 6;
    private static final int OPCAO_SAIR = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto> produtos = new ArrayList<>();
        Map<Integer, Pedido> pedidos = new HashMap<>();
        int proximoIdPedido = 1;

        boolean executando = true;
        while (executando) {
            exibirMenu();
            int opcao = lerInteiro(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case OPCAO_ADICIONAR_PRODUTO -> adicionarProduto(scanner, produtos);
                case OPCAO_LISTAR_PRODUTOS -> listarProdutos(produtos);
                case OPCAO_CRIAR_PEDIDO -> {
                    Pedido novoPedido = criarPedido(scanner, proximoIdPedido);
                    if (novoPedido != null) {
                        pedidos.put(proximoIdPedido, novoPedido);
                        System.out.println("Pedido criado com ID: " + proximoIdPedido);
                        proximoIdPedido++;
                    }
                }
                case OPCAO_ADICIONAR_PRODUTO_PEDIDO -> adicionarProdutoAoPedido(scanner, produtos, pedidos);
                case OPCAO_CALCULAR_FRETE -> calcularFretePedido(scanner, pedidos);
                case OPCAO_LISTAR_PEDIDOS -> listarPedidos(pedidos);
                case OPCAO_SAIR -> {
                    executando = false;
                    System.out.println("Encerrando aplicação...");
                }
                default -> System.out.println("Opção inválida.");
            }

            System.out.println();
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=== MENU ===");
        System.out.println("1 - Adicionar produto");
        System.out.println("2 - Listar produtos");
        System.out.println("3 - Criar pedido");
        System.out.println("4 - Adicionar produto em pedido");
        System.out.println("5 - Calcular frete de pedido");
        System.out.println("6 - Listar pedidos");
        System.out.println("0 - Sair");
    }

    private static void adicionarProduto(Scanner scanner, List<Produto> produtos) {
        String nome = lerTexto(scanner, "Nome do produto: ");
        double valor = lerDouble(scanner, "Valor do produto: ");
        double peso = lerDouble(scanner, "Peso do produto (kg): ");

        try {
            Produto produto = new Produto(nome, valor, peso);
            produtos.add(produto);
            System.out.println("Produto adicionado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    private static void listarProdutos(List<Produto> produtos) {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("=== PRODUTOS ===");
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            System.out.printf("%d - %s | R$ %.2f | %.2f kg%n", i, produto.nome(), produto.valor(), produto.peso());
        }
    }

    private static Pedido criarPedido(Scanner scanner, int idPedido) {
        TipoEntrega tipoEntrega = lerTipoEntrega(scanner);

        try {
            return new Pedido(idPedido, tipoEntrega, LocalDate.now());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
            return null;
        }
    }

    private static void adicionarProdutoAoPedido(Scanner scanner, List<Produto> produtos, Map<Integer, Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido criado.");
            return;
        }

        if (produtos.isEmpty()) {
            System.out.println("Cadastre ao menos um produto antes.");
            return;
        }

        listarPedidos(pedidos);
        int idPedido = lerInteiro(scanner, "Informe o ID do pedido: ");
        Pedido pedido = pedidos.get(idPedido);

        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        listarProdutos(produtos);
        int indiceProduto = lerInteiro(scanner, "Informe o índice do produto: ");

        if (indiceProduto < 0 || indiceProduto >= produtos.size()) {
            System.out.println("Índice de produto inválido.");
            return;
        }

        Produto produto = produtos.get(indiceProduto);
        pedido.adicionarProduto(produto);
        System.out.println("Produto adicionado ao pedido com sucesso.");
    }

    private static void calcularFretePedido(Scanner scanner, Map<Integer, Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido criado.");
            return;
        }

        listarPedidos(pedidos);
        int idPedido = lerInteiro(scanner, "Informe o ID do pedido: ");
        Pedido pedido = pedidos.get(idPedido);

        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        double pesoTotal = pedido.calcularPesoTotal();
        double frete = pedido.calcularFrete();
        System.out.printf("Peso total: %.2f kg | Frete: R$ %.2f%n", pesoTotal, frete);
    }

    private static void listarPedidos(Map<Integer, Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido criado.");
            return;
        }

        System.out.println("=== PEDIDOS ===");
        for (Map.Entry<Integer, Pedido> entry : pedidos.entrySet()) {
            int id = entry.getKey();
            Pedido pedido = entry.getValue();
            System.out.printf("ID %d | Peso total: %.2f kg | Frete atual: R$ %.2f%n", id, pedido.calcularPesoTotal(), pedido.calcularFrete());
        }
    }

    private static TipoEntrega lerTipoEntrega(Scanner scanner) {
        while (true) {
            System.out.println("Tipo de entrega:");
            System.out.println("1 - ENCOMENDA_PAC");
            System.out.println("2 - SEDEX");
            System.out.println("3 - RETIRADA_LOJA");

            int opcao = lerInteiro(scanner, "Escolha o tipo: ");
            switch (opcao) {
                case 1:
                    return TipoEntrega.ENCOMENDA_PAC;
                case 2:
                    return TipoEntrega.SEDEX;
                case 3:
                    return TipoEntrega.RETIRADA_LOJA;
                default:
                    System.out.println("Tipo inválido.");
            }
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número decimal.");
            }
        }
    }

    private static String lerTexto(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine();
            if (!entrada.isBlank()) {
                return entrada;
            }
            System.out.println("Texto não pode ser vazio.");
        }
    }
}
