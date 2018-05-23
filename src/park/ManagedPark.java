
package park;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class ManagedPark {
   
   private Integer id; 
   private Park estac;
   private ArrayList <Park> bancoPark = new ArrayList();

    public ManagedPark() {
        int x = 0;
        this.id = ++x;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Park getEstac() {
        return estac;
    }

    public void setEstac(Park estac) {
        this.estac = estac;
    }
    public ArrayList<Park> getBancoPark() {
        return bancoPark;
    }

    public void setBancoPark(ArrayList<Park> bancoPark) {
        this.bancoPark = bancoPark;
    }
    
    public void gerarEntrada(){
        Date data = new Date();
        Time time = null;
        estac = new Park();
        estac.getCodigo();
        estac.setDataEntrada(data);
        estac.setHoraEntrada(time);
        bancoPark.add(estac);
    }
}
