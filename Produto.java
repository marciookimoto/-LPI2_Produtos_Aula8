/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bim.exercicio.produto.aula2;

import java.io.Serializable;

/**
 *
 * @author marci
 */
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L; 
    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;
    private Categoria categoria;
    private String datacompra;
    private String restrito;

    public Produto(String codigo, String nome, double preco, int quantidade, Categoria categoria, String datacompra, String restrito) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.datacompra = datacompra;
        this.restrito = restrito;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getRestrito() {
        return restrito;
    }

    public String getDataCompra() {
        return datacompra;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setTemRestricao(String restrito) {
        this.restrito= restrito;
    }

    public void setDataCompra(String datacompra) {
        this.datacompra = datacompra;
    }

}


