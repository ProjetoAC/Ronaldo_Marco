package br.com.senai.aprendercrescer.controller;

import br.com.senai.aprendercrescer.dao.ProdutoDao;
import br.com.senai.aprendercrescer.model.Produto;
import java.util.ArrayList;



public class ProdutoController {

     ProdutoDao produtoDao;

    public ProdutoController() {
        if (produtoDao == null) {
            produtoDao = new ProdutoDao();
        }
    }

   
//    public boolean insereProduto(Produto produto) {
//
//        if (produto.getIdProduto() == 0) {
//            return ProdutoDao.insereProduto(produto);
//        } else {
//            return produtoDao.updateproduto(produto);
//        }
//
//    }

    
    
    public ArrayList<Produto> getProduto() {
        return produtoDao.getProduto();
    }
/*
    public boolean deleteUsuario(int id) {
        return vendaDao.deleteVenda(id);
    }
*/
}
