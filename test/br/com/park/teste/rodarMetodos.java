/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.teste;

import br.com.park.dtbase.TBdao;
import br.com.park.job.Caixa;
import br.com.park.job.Ticket;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.JLabel;

/**
 *
 * @author Programador
 */
public class rodarMetodos {

    public static byte menu(Scanner sc) {
        System.err.println("Escolha uma opção");
        System.err.println("1 - Cadastrar");
        System.err.println("2 - Alterar");
        System.err.println("3 - Listar ");
        System.err.println("4 - Consultar");
        System.err.println("5 - Sair");

        return sc.nextByte();
    }

    public static byte menuCaixa(Scanner sc) {
        System.err.println("Escolha uma opção");
        System.err.println("1 - Cobrar");
        System.err.println("2 - Desconto");
        System.err.println("3 - Consultar Finanças");
        System.err.println("4 - Fechar caixa");
        System.err.println("5 - Sair");

        return sc.nextByte();
    }

    public static void main(String args[]) throws SQLException {

        Scanner sc = new Scanner(System.in);
        byte opcao = 0;

        while (opcao != 5) {

            opcao = menuCaixa(sc);

            switch (opcao) {
                case 1:// Cobrança de ticket 
                    new TBdao().bdtoArray(); /*
                    Não esquecer de popular o array da tabela de preço 
                    corrigir o carregamento da tabela de preço também 
                    além de atribuir valores ao caixa 
                    
                    */
                    System.err.println("Cobrar ticket");
                    Ticket t1 = new Ticket();
                    t1.setaHora(19, 20, 10);
                    t1.setaHoraSaida(30);
                    Caixa c1 = new Caixa();
                    c1.gerarReceita(t1);
                    System.err.println("Valor: " + t1.getValor() + " Hora de Saida: "+ t1.getSaida());

                    break;
                default:
                    break;
            }

        }
    }

}
