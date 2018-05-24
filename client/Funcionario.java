
package br.com.park.client;
public class Funcionario {
    private static int inc = 0;
    private int id;
    private String nome;
    private String nvOper;
    private boolean operador;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNvOper() {
        return nvOper;
    }

    public void setNvOper(String nvOper) {
        this.nvOper = nvOper;
    }

    public boolean isOperador() {
        return operador;
    }

    public void setOperador(boolean operador) {
        this.operador = operador;
    }
}
