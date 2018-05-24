/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.park.client;

import br.com.park.job.Ticket;

/**
 *
 * @author Anderson M.
 */
public class Cliente {
    private static int inc = 0;
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Ticket passe;
    
    public Cliente() {
     
    }
    public Cliente(String tel,Ticket passe) {
        this.id = inc++;
        setId(id);
        setTelefone(tel);
        setPasse(passe);
    }

    public Ticket getPasse() {
        return passe;
    }

    public void setPasse(Ticket passe) {
        this.passe = passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
   
}
