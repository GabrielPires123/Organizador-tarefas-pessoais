package com.ead;

import Entities.CadastrarAtividade;
import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class Main {
    static List<CadastrarAtividade> cadastrarList = new ArrayList<>();
    static SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");
    static Scanner sc = new Scanner(System.in);
    static Connection conn = DB.getConnection();
    static PreparedStatement st = null;


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
                sc.nextLine();

                switch (menu) {

                    case 1:
                        CadastrarAtividade();
                        break;

                    case 2:
                        ListarAtividades();
                        break;
                    case 3:
                            //EditarAtividade();
                        break;

                    case 4:
                            ExcluirAtividade();
                        break;

                    case 0:
                        DB.closeStatement(st);
                        DB.closeConnection();
                        System.out.println("Finalizando programa");
                        break;

                    default:
                        System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                }

            } while (menu != 0);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Apenas números são permitidos.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void ListarAtividades() {
        try {
            ResultSet rs;
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("Select * from fila");

            System.out.print("\n===== Lista de Atividades =====");
            while (rs.next()) {

                System.out.print("\nId: " + rs.getInt("Id") +
                                "\nNome da atividade" + rs.getString("Nome") +
                                "\nDescrição: " + rs.getString("NomeDescricao") +
                                "\nDataRegistro: " + rs.getString("DataRegistro") + "\n");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

   /* public static void EditarAtividade() throws SQLException {

        ListarAtividades();
        try {

            System.out.println("Digite o ID da atividade que deseja editar: ");
            int buscaIdEditar = sc.nextInt();
            sc.nextLine(); // Consumir a linha restante no buffer

            // Buscar atividade pelo ID
            for (CadastrarAtividade i : cadastrarList) {

                System.out.println("Novo nome da atividade: ");
                String nome = sc.nextLine();
                //i.setNomeAtividade(nome);
                System.out.println("Nova descrição da atividade: ");
                String descricao = sc.nextLine();
                i.setDescricao(descricao);
                System.out.println("Nova data da atividade (dd/MM/yy): ");
                String data = sc.nextLine();
                Date date = dataFormatada.parse(data);
                i.setData(date);
                System.out.println("Atividade editada com sucesso!");


            }
        } catch (ParseException e) {
            System.out.println("Entrada inválida! Digite uma data válida." + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println(" " + e.getMessage());
        }

    }*/

    public static void ExcluirAtividade() throws SQLException {
        ListarAtividades();


        System.out.println("Digite o ID da atividade que deseja excluir: ");
        int atividadeToRemove = sc.nextInt();

        conn.setAutoCommit(false);
        st = conn.prepareStatement("delete from fila where id=?");
        st.setInt(1,atividadeToRemove);

        st.executeUpdate();
        conn.commit();


        System.out.println("Atividade removida com sucesso!");
    }

    public static void CadastrarAtividade() {

        try {
            System.out.println("\n----- Atividade -----");

            System.out.print("Nome da atividade: ");
            String nome = sc.nextLine();

            System.out.print("Descrição da atividade: ");
            String descricao = sc.nextLine();

            System.out.print("Data da atividade (dd/MM/yy): ");
            String data = sc.nextLine();
            Date date = dataFormatada.parse(data);

            CadastrarAtividade cadastrarAtividade = new CadastrarAtividade(nome, descricao, date);
            cadastrarList.add(cadastrarAtividade);
            try {
                conn.setAutoCommit(false);

                st = conn.prepareStatement("INSERT INTO FILA(Nome, NomeDescricao, DataRegistro) VALUES(?,?,?)");
                st.setString(1, cadastrarAtividade.getNomeAtividade());
                st.setString(2, cadastrarAtividade.getDescricao());
                st.setDate(3, new java.sql.Date(cadastrarAtividade.getData().getTime()));

                st.executeUpdate();
                conn.commit();

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("" + e.getMessage());
            }
        } catch (ParseException | SQLException e) {
            System.out.println("Entrada inválida! Digite uma data válida.");
        }


    }
}

