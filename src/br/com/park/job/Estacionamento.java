package br.com.park.job;

import br.com.park.dtbase.bdBack;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Estacionamento {
    
    private int id;
    private Servicos servico = new Servicos();
    private TabelaPreco Tpreco = new TabelaPreco();
    private Caixa caixa;
    private Ticket tk = new Ticket();
  
    public Estacionamento(Servicos sv,TabelaPreco tb,Ticket tk) {
        setServico(sv);
        setTpreco(tb);
    }
    public Estacionamento() {
    }

    public Ticket getTk() {
        return tk;
    }

    public void setTk(Ticket tk) {
        this.tk = tk;
    }

    public TabelaPreco getTpreco() {
        return Tpreco;
    }

    public void setTpreco(TabelaPreco Tpreco) {
        this.Tpreco = Tpreco;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Servicos getServico() {
        return servico;
    }

    public void setServico(Servicos servico) {
        this.servico = servico;
    }

  /**
    //<<<<<< ---- Serviços  ---- >>>>

    public int qualServico(String nomeServ) {
        int tempoTolerancia = 0;
        for (int i = 0; i < Gservico.size(); i++) {
            if (nomeServ.equalsIgnoreCase(Gservico.get(i).getNome())) {
                tempoTolerancia = Gservico.get(i).getTolerancia();
            }
        }
        return tempoTolerancia;
    }

    //------------------- Convenio ----------------->>>>//
    public void novoConvenio(Convenio convenio, String desconto) {
        convenio.setConvenioNome(convenio.getId() + "ª Hora");
        convenio.setDesconto(Integer.valueOf(desconto));
        this.Gconvenio.add(convenio);
    }

    public void novoConvenioEspecial(Convenio convenio, String nome, String desconto) {
        convenio.setConvenioNome(nome);
        convenio.setDesconto(Integer.valueOf(desconto));
        this.Gconvenio.add(convenio);
    }

    public Convenio loadConvenio(int x) {
        return Gconvenio.get(x);
    }*/
    
    /**
     * o estacionamento deve ser cobrado de acordo com a tabela de preço 
     * então na teoria ele cobra o tempo desde o inicio.
     * 
     * O serviço vai dá algum tipo de tolerancia e esse tempo não vai ser cobrado 
     * se por acaso esse tempo passar então só pode ser descontado com o Convênio 
     */
   public void geraReceita(){
       // Aqui será feita a comparação, está sendo testada no pacote de testes 
       
       Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
       Date horaAtual = c.getTime();
       
   }
}
