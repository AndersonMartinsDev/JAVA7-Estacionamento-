package br.com.park.job;

public class Servicos {
    private static int inc = 0;
    private Integer id;
    private String nome;
    private int acessos;//Não me lembro porque pus isso aqui lembrar de projetar antes de programar
    private Integer tolerancia;
    private String unidadeMedida;

    
     
    public Servicos() {
        this.id = inc++;
        setId(id);
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(Integer tolerancia) {
        this.tolerancia = tolerancia;
    }

    public void gerarTicket() {

    }

}
