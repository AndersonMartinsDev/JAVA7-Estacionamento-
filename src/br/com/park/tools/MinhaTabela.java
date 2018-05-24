package br.com.park.tools;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class MinhaTabela extends AbstractTableModel {

    private ArrayList linhas = null;
    private String[] colunas = null;
     @Override
    public boolean isCellEditable(int row, int col) {
        // Penultima linha, linha anterior a que foi adicionada
        if(row == colunas.length){
            return false;
        }
        if (col > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public MinhaTabela (ArrayList lin,String[] col ){
        setLinhas(lin);
        setColunas(col);
    }
    public ArrayList getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList linhas) {
        this.linhas = linhas;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    public int getColumnCount(){
        return colunas.length;
    }
    public int getRowCount(){
        return linhas.size();
    }
    public String getColumnName(int numCol){
        return colunas[numCol];
    }
    public Object getValueAt(int numLin,int numCol){
        Object[] linha = (Object[])getLinhas().get(numLin);
        return linha[numCol];
    }
}
