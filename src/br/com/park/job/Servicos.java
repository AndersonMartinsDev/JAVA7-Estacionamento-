package br.com.park.job;

public final class Servicos {

    private static int inc = 0;
    private Integer id;
    private String nome;
    private int acessos;//QUANTIDADE DE ACESSOS POR TICKET
    private Integer tolerancia;
    private String unidadeMedida;

    
    public Servicos() {
        this.id = inc++;
        setId(id);
    }
    public Servicos(int id,String nome,int acessos,int tolerancia,String unidadeMedida){
        setId(id);
        setNome(nome);
        setAcessos(acessos);
        setTolerancia(tolerancia);
        setUnidadeMedida(unidadeMedida);
    }
    
    public int getAcessos() {
        return acessos;
    }

    public void setAcessos(int acessos) {
        this.acessos = acessos;
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

}
