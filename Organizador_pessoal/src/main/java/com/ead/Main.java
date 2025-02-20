package com.ead;

import Entities.CadastrarAtividade;
import Modal.Dao.Cadastrar.CadastrarDao;
import Modal.Dao.Cadastrar.DaoFactory;
import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class Main {

    private static final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");
    private static final Scanner sc = new Scanner(System.in);
    private static CadastrarDao cadastrarDao;

    public static void main(String[] args) {
        // Gerenciamento centralizado da conexão
        try (Connection conn = DB.getConnection()) {
            cadastrarDao = DaoFactory.creatAtividadeDAO(conn); // DAO compartilhado

            int menu;
            do {
                menu = exibirMenu();
                processarOpcao(menu);
            } while (menu != 0);

        } catch (SQLException e) {
            System.err.println("Erro no banco de dados: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida! Digite apenas números.");
        } finally {
            sc.close();
        }
    }

    private static int exibirMenu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1 - Adicionar Atividade");
        System.out.println("2 - Listar Atividades");
        System.out.println("3 - Editar Atividade");
        System.out.println("4 - Excluir Atividade");
        System.out.println("0 - Finalizar Programa");
        System.out.print("Escolha uma opção: ");

        return sc.nextInt();
    }

    private static void processarOpcao(int menu) {
        try {
            switch (menu) {
                case 1 -> cadastrarAtividade();
                case 2 -> listarAtividades();
                case 3 -> editarAtividade();
                case 4 -> excluirAtividade();
                case 0 -> System.out.println("Finalizando programa...");
                default -> System.out.println("Opção inválida!");
            }
        } catch (SQLException e) {
            System.err.println("Erro na operação com o banco: " + e.getMessage());
        } finally {
            sc.nextLine(); // Limpa buffer
        }
    }

    private static void cadastrarAtividade() throws SQLException {
        try {

            System.out.println("\n----- Nova Atividade -----");

            System.out.print("Nome da atividade: ");
            sc.nextLine();
            String nome = sc.nextLine();

            System.out.print("Descrição: ");
            String descricao = sc.nextLine();

            System.out.print("Data (dd/MM/yy): ");
            Date data = dataFormatada.parse(sc.nextLine());

            CadastrarAtividade novaAtividade = new CadastrarAtividade(null, nome, descricao, data);
            cadastrarDao.insert(novaAtividade);

            System.out.println("Atividade cadastrada com sucesso!");

        } catch (ParseException e) {
            System.err.println("Formato de data inválido! Use dd/MM/yy");
        }
    }

    private static void listarAtividades() throws SQLException {
        List<CadastrarAtividade> atividades = cadastrarDao.findAll();

        if (atividades.isEmpty()) {
            System.out.println("\nNenhuma atividade cadastrada!");
            return;
        }

        System.out.println("\n===== Atividades Cadastradas =====");
        atividades.forEach(System.out::println);
    }

    private static void editarAtividade() throws SQLException {
        listarAtividades();

        System.out.print("\nDigite o ID da atividade para editar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        try {
            System.out.print("Novo nome: ");
            String nome = sc.nextLine();

            System.out.print("Nova descrição: ");
            String descricao = sc.nextLine();

            System.out.print("Nova data (dd/MM/yy): ");
            Date data = dataFormatada.parse(sc.nextLine());

            CadastrarAtividade atualizada = new CadastrarAtividade(id, nome, descricao, data);
            cadastrarDao.upgrade(atualizada, id);

            System.out.println("Atividade atualizada com sucesso!");

        } catch (ParseException e) {
            System.err.println("Formato de data inválido! Use dd/MM/yy");
        }
    }

    private static void excluirAtividade() throws SQLException {
        listarAtividades();

        System.out.print("\nDigite o ID da atividade para excluir: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpar buffer

        cadastrarDao.delete(id);
        System.out.println("Atividade excluída com sucesso!");
    }
}