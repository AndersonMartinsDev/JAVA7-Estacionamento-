
package park;

import java.sql.Time;
import java.util.Date;

public class Park {
    private Integer id;
    private Integer codigo;
    private Date dataEntrada;
    private Date dataSaida;
    private Time horaEntrada;
    private Time horaSaida;

    Park(){
        int x = 0;
        this.id = ++x;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return (int) (Math.random() * 100000);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Time getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Time getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(Time horaSaida) {
        this.horaSaida = horaSaida;
    }
    
}
