package com.ead;

import Entities.CadastrarAtividade;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        List<CadastrarAtividade> cadastrarList = new ArrayList<>();
        SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");

        int id =1;
        int menu =1;

        while(menu != 0){
            System.out.println("Menu\n" +
                    "1 - Adicionar Atividade\n" +
                    "2 - Listar\n" +
                    "3 - Editar\n" +
                    "4 - Excluir\n" +
                    "0 - Finalizar programa");
            //MENU
            menu = sc.nextInt(); //ok

            switch (menu) {

                //Cadastro atividades
                case 1:
                    System.out.println("Quantas Atividades irão ser registradas: ");
                    int quantidadeAtividade = sc.nextInt();

                    sc.nextLine(); // Consumir a linha restante no buffer

                    for (int i = 1; i <= quantidadeAtividade; i++)
                    {

                        System.out.println("Digite o nome da atividade: ");
                        String nome = sc.nextLine();

                        System.out.println("Descrição: ");
                        String descricao = sc.nextLine();

                        System.out.println("Qual dia vai ocorrer a atividade (dd/MM/yy): ");
                        String data = sc.nextLine();

                        Date  date = dataFormatada.parse(data);
                        CadastrarAtividade cadastrarAtividade = new CadastrarAtividade(nome, descricao,date,id);
                        cadastrarList.add(cadastrarAtividade);
                        id++;
                    }
                    break;//ok

                case 2:
                    if(cadastrarList.size() != 0)
                    {
                        System.out.println("Lista de Atividades: \n");
                        for (CadastrarAtividade i : cadastrarList)
                        {
                            System.out.println("Nome: " + i.getNomeAtividade());
                            System.out.println("Data:" + dataFormatada.format(i.getData()));
                            System.out.println("Descrição: " + i.getDescricao());
                        }
                    }
                    else
                    {
                        System.out.println("Adicione uma tarefa para ser listada");
                    }

                    break; //ok

                case 3:

                    if (cadastrarList.size() != 0)
                    {
                        System.out.println("Atividades disponíveis:");
                        for (CadastrarAtividade i : cadastrarList)
                        {
                            System.out.println("id: " + i.getId());
                            System.out.println("Nome: " + i.getNomeAtividade());
                            System.out.println("Data:" + dataFormatada.format(i.getData()));
                            System.out.println("Descrição: " + i.getDescricao());
                        }

                        System.out.println("Digite o ID da atividade que deseja Editar:");
                        int buscaIdEditar = sc.nextInt();
                        sc.nextLine(); // Consumir a linha restante no buffer

                        // Buscar atividade pelo ID
                        for (CadastrarAtividade i : cadastrarList)
                        {
                            if (i.getId() == buscaIdEditar)
                            {
                                System.out.println("Nome: ");
                                String nome = sc.nextLine();
                                i.setNomeAtividade(nome);
                                System.out.println("Descrição: ");
                                String descricao = sc.nextLine();
                                i.setDescricao(descricao);
                                System.out.println("Data nova atividade (dd/MM/yy): ");
                                String data = sc.nextLine();
                                Date  date = dataFormatada.parse(data);
                                i.setData(date);
                                System.out.println("Atividade editada com sucesso!");
                            }

                        }

                    } else
                    {
                        System.out.println("Não há atividades para excluir.");
                    }
                    break;

                case 4:

                    if (cadastrarList.size() != 0) {
                        System.out.println("Atividades disponíveis:");
                        for (CadastrarAtividade i : cadastrarList) {
                            System.out.printf("ID: %d | Nome: %s%n", i.getId(), i.getNomeAtividade());
                        }

                        System.out.println("Digite o ID da atividade que deseja excluir:");
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
                    else
                    {
                        System.out.println("Não há atividades para serem editadas.");
                    }
                    break;
                default:
                    System.out.println("Digite novamente!");
            }
        };
    }


   /*Futuramente adicionar modularização

     public void Cadastrar()
    {
    }*/

}

