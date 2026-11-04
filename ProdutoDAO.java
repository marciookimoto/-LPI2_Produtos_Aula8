/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bim.exercicio.produto.aula2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO {

    private final String CAMINHO = "contatos.dat";

    public void salvar(ArrayList<Produto> lista) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(CAMINHO);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(lista);
        objectOut.close();
        fileOut.close();
    }

    public void salvar(ArrayList<Produto> lista, File arquivo) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(arquivo);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(lista);
        objectOut.close();
        fileOut.close();
    }

    public ArrayList<Produto> importar(File arquivo) throws Exception {
        FileInputStream fileIn = new FileInputStream(arquivo);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<Produto> lista = (ArrayList<Produto>) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return lista;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Produto> carregar() throws Exception {
        FileInputStream fileIn = new FileInputStream(CAMINHO);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        ArrayList<Produto> lista = (ArrayList<Produto>) objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return lista;
    }

    public void inserir(Produto p) throws SQLException {
        String sql = "INSERT INTO produtos (codigo, nome, preco, quantidade, categoria_id, datacompra, restrito) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoBanco.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.setInt(5, p.getCategoria().getId());
            stmt.setString(6, p.getDataCompra());
            stmt.setString(7, p.getRestrito());

            stmt.executeUpdate();
        }
    }

    public ArrayList<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        ArrayList<Produto> lista = new ArrayList<>();
        try (Connection conn = ConexaoBanco.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto(
                        rs.getString("codigo"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        CategoriaDAO.buscarPorId(rs.getInt("categoria_id")),
                        rs.getString("datacompra"),
                        rs.getString("restrito")
                );

                lista.add(p);
            }
        }
        return lista;
    }

    public void atualizar(String codigoOriginal, Produto p) throws SQLException {
        String sql = "UPDATE produtos SET codigo=?, nome=?, preco=?, quantidade=?, categoria_id=?, datacompra=?, restrito=? WHERE codigo=?";

        try (Connection conn = ConexaoBanco.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getCodigo());
            stmt.setString(2, p.getNome());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getQuantidade());
            stmt.setInt(5, p.getCategoria().getId());
            stmt.setString(6, p.getDataCompra());
            stmt.setString(7, p.getRestrito());
            stmt.setString(8, codigoOriginal);

            stmt.executeUpdate();
        }
    }

    public void excluir(String codigo) throws SQLException {
        String sql = "DELETE FROM produtos WHERE codigo=?";

        try (Connection conn = ConexaoBanco.getConexao(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }

}
