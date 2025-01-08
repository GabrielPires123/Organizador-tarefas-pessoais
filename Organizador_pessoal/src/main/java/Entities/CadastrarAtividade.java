package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CadastrarAtividade {

    private Integer id;
    private String nomeAtividade;
    private String descricao;
    private Date data;
    private final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");


    public CadastrarAtividade(String nomeAtividade, String descricao, Date data ,Integer id)
    {
        validaçãoString(nomeAtividade);
        validaçãoData(data);
        this.descricao = descricao;
        this.nomeAtividade = nomeAtividade;
        this.id = id;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        validaçãoData(data);
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public String getNomeAtividade() {

        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        validaçãoString(nomeAtividade);
        this.nomeAtividade = nomeAtividade;
    }

    public Integer getId() {
        return id;
    }


    public void validaçãoString(String nome)
    {
        if (nome == null || nome.trim().isEmpty())
        {
            throw new RuntimeException("O nome da atividade não pode estar vazio.");
        }
        if (!nome.matches("[a-zA-Z ]+")) {
            throw new RuntimeException("Entrada inválida! O título deve conter apenas letras.");
        }
    }
    public void validaçãoData(Date date)
    {
        Date now = new Date();
        if (date.before(now))
        {
            throw new RuntimeException("Data informada não pode ser no passado. Insira uma data igual ou posterior a hoje, " + dataFormatada.format(now));
        }
    }

    @Override
    public String toString() {
        return "\n===== Lista de Atividades =====" +
                "\nID: " + id+
                "\nNome: " + nomeAtividade +
                "\nData: " + dataFormatada.format(data) +
                "\nDescrição: " + descricao +
             "\n-------------------------------";
    }
}
