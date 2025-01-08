package com.ead;

import Entities.CadastrarAtividade;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    static List<CadastrarAtividade> cadastrarList = new ArrayList<>();
    static SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            int menu;
            do {
                System.out.println("\n===== Menu =====");
                System.out.println("1 - Adicionar Atividade");
                System.out.println("2 - Listar Atividades");
                System.out.println("3 - Editar Atividade");
                System.out.println("4 - Excluir Atividade");
                System.out.println("0 - Finalizar Programa");

                menu = sc.nextInt();

                switch (menu) {

                    case 1:
                        CadastrarAtividade();
                        break;

                    case 2:
                        if (cadastrarList.size() != 0) {
                            ListarAtividades();
                        } else {
                            System.out.println("Nenhuma atividade cadastrada. Adicione uma atividade para ser listada.");
                        }

                        break;

                    case 3:

                        if (cadastrarList.size() != 0) {
                            EditarAtividade();
                        } else {
                            System.out.println("Não há atividades para serem editadas.");
                        }
                        break;

                    case 4:

                        if (cadastrarList.size() != 0) {
                            ExcluirAtividade();
                        } else {
                            System.out.println("Não há atividades para excluir.");
                        }
                        break;

                    case 0:
                        System.out.println("Finalizando programa");
                        break;

                    default:
                        System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                }

            } while (menu != 0);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Apenas números são permitidos.");
        }
    }

    public static void ListarAtividades() {
        for (CadastrarAtividade i : cadastrarList) {
            System.out.println(" " + i.toString());
        }
    }

    public static void EditarAtividade() {

        ListarAtividades();
        try {

            System.out.println("Digite o ID da atividade que deseja editar: ");
            int buscaIdEditar = sc.nextInt();
            sc.nextLine(); // Consumir a linha restante no buffer

            // Buscar atividade pelo ID
            for (CadastrarAtividade i : cadastrarList) {
                if (i.getId() == buscaIdEditar) {
                    System.out.println("Novo nome da atividade: ");
                    String nome = sc.nextLine();
                    i.setNomeAtividade(nome);
                    System.out.println("Nova descrição da atividade: ");
                    String descricao = sc.nextLine();
                    i.setDescricao(descricao);
                    System.out.println("Nova data da atividade (dd/MM/yy): ");
                    String data = sc.nextLine();
                    Date date = dataFormatada.parse(data);
                    i.setData(date);
                    System.out.println("Atividade editada com sucesso!");
                }

            }
        } catch (ParseException e) {
            System.out.println("Entrada inválida! Digite uma data válida." + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(" " + e.getMessage());
        }
    }

    public static void ExcluirAtividade() {
        ListarAtividades();

        System.out.println("Digite o ID da atividade que deseja excluir: ");
        int resp = sc.nextInt();

        // Buscar atividade pelo ID
        CadastrarAtividade atividadeToRemove = null;
        for (CadastrarAtividade i : cadastrarList) {
            if (i.getId() == resp) {
                atividadeToRemove = i;
                break;
            }
        }

        if (atividadeToRemove != null) {
            cadastrarList.remove(atividadeToRemove);
            System.out.println("Atividade removida com sucesso!");
        } else {
            System.out.println("ID não encontrado. Nenhuma atividade foi removida.");
        }
    }

    public static void CadastrarAtividade() {
        try {
            System.out.println("Quantas atividades você deseja registrar? ");
            int quantidadeAtividade = sc.nextInt();

            sc.nextLine(); // Consumir a linha restante no buffer

            for (int i = 1; i <= quantidadeAtividade; i++) {

                System.out.println("\n----- Atividade " + i + " -----");

                System.out.print("Nome da atividade: ");
                String nome = sc.nextLine();

                System.out.print("Descrição da atividade: ");
                String descricao = sc.nextLine();

                System.out.print("Data da atividade (dd/MM/yy): ");
                String data = sc.nextLine();

                Date date = dataFormatada.parse(data);
                int id = cadastrarList.size() + 1;
                CadastrarAtividade cadastrarAtividade = new CadastrarAtividade(nome, descricao, date, id);
                cadastrarList.add(cadastrarAtividade);
            }
        } catch (ParseException e) {
            System.out.println("Entrada inválida! Digite uma data válida.");
        } catch (RuntimeException e) {
            System.out.println("" + e.getMessage());
        }
    }

}

