
package br.com.park.logs;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Logs {
    
    private static int inc = 0;
    private int id;
    private String modulo;
    private String descricao;
    private Date registro;
    Calendar c = GregorianCalendar.getInstance(Locale.ROOT);
    
    
    public Logs() {
        setId(inc++);
        setRegistro(c.getTime());
    }


    public static int getInc() {
        return inc;
    }
    public static void setInc(int inc) {
        Logs.inc = inc;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModulo() {
        return modulo;
    }
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Date getRegistro() {
        return registro;
    }
    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    public String toString(){
        DateFormat data = DateFormat.getDateInstance();
        DateFormat hora = DateFormat.getTimeInstance();
        String logText =
        getModulo()+",  "+
        getDescricao()+",  "+
        hora.format(getRegistro())+", "+
        data.format(getRegistro())+"\n";
        return logText;
    }
  
}
