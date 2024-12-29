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
        SimpleDateFormat fds = new SimpleDateFormat("dd/MM/yy");

        int x =1;
        while(x != 0){
            System.out.println("Menu\n" +
                    "1 - Adicionar Atividade\n" +
                    "2 - Listar\n" +
                    "3 - Editar\n" +
                    "4 - Excluir\n" +
                    "0 - Finalizar programa");
            //MENU
            x = sc.nextInt();

            switch (x) {

                //Cadastro atividades
                case 1:
                    System.out.println("Quantas Atividades irão ser registradas: ");
                    int quantidade = sc.nextInt();

                    sc.nextLine(); // Consumir a linha restante no buffer

                    for (int i = 1; i <= quantidade; i++) {
                        int id =1;
                        System.out.println("Digite o nome da atividade: ");
                        String nome = sc.nextLine();

                        System.out.println("Descrição: ");
                        String descricao = sc.nextLine();

                        System.out.println("Qual dia vai ocorrer a atividade (dd/MM/yy): ");
                        String data = sc.nextLine();

                        Date  date = fds.parse(data);
                        CadastrarAtividade cadastrarAtividade = new CadastrarAtividade(nome, descricao,date,id);
                        cadastrarList.add(cadastrarAtividade);
                        id++;
                    }
                    break;
                case 2:
                    if(cadastrarList.size() != 0)
                    {
                        for (CadastrarAtividade i : cadastrarList)
                        {
                            System.out.println("Tarefas: \n" + i.getNomeAtividade() + "\nData:" + fds.format(i.getData()) +
                                    "\nDescrição: " + i.getDescricao());
                        }
                    }
                    else
                    {
                        System.out.println("Adicione uma tarefa para ser listada");
                    }

                    break;

                case 3:
                    break;

                case 4:

                    if(cadastrarList.size() != 0)
                    {
                        int resp;
                        for (CadastrarAtividade i : cadastrarList)
                        {

                            System.out.printf("Qual tarefa deseja excluir? %n " +
                                    "%d Tarefas: %s", i.getId(), i.getNomeAtividade());
                            resp = sc.nextInt();
                            cadastrarList.remove(resp-1);
                        }


                    }
                    else
                    {
                        System.out.println("Adicione uma tarefa para ser listada  ");
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

