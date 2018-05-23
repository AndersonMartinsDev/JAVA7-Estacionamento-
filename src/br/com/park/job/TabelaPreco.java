
package br.com.park.job;

public class TabelaPreco {
    private static int inc = 0;
    private int id;
    private double tempo;
    private float moeda;

    public TabelaPreco() {
        this.id = inc++;
        setId(id);
    }
    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public double getTempo() {
        return tempo;
    }
    public void setTempo(double tempo) {
        this.tempo = tempo;
    }
    public float getMoeda() {
        return moeda;
    }
    public void setMoeda(float moeda) {
        this.moeda = moeda;
    }




}
