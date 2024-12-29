package Entities;

import java.util.Date;

public class CadastrarAtividade {

    private Integer id;
    private String nomeAtividade;
    private String descricao;
    private Date data;

    public CadastrarAtividade(String nomeAtividade, String descricao, Date data ,Integer id)
    {
        this.descricao = descricao;
        this.nomeAtividade = nomeAtividade;
        this.id = id;
        this.data = data;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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
        this.nomeAtividade = nomeAtividade;
    }

    public Integer getId() {
        return id;
    }

}
