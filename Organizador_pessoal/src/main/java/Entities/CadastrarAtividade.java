package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class CadastrarAtividade {

    private Integer id;
    private String nomeAtividade;
    private String descricao;
    private Date data;
    private final SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yy");


    public CadastrarAtividade()
    {
        /*validaçãoString(nomeAtividade);
        validaçãoData(data);*/
    }

    public CadastrarAtividade(Integer id, String nomeAtividade,String descricao, Date data) {
        this.data = data;
        this.descricao = descricao;
        this.id = id;
        this.nomeAtividade = nomeAtividade;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public SimpleDateFormat getDataFormatada() {
        return dataFormatada;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastrarAtividade that = (CadastrarAtividade) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /*public void setNomeAtividade(String nomeAtividade) {
        validaçãoString(nomeAtividade);
        this.nomeAtividade = nomeAtividade;
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
    }*/

    @Override
    public String toString() {
        return "\n-------------------------------" +
                "\nID: " + getId()+
                "\nNome: " + getNomeAtividade() +
                "\nData: " + getDataFormatada().format(data) +
                "\nDescrição: " + getDescricao() +
             "\n-------------------------------";
    }
}

