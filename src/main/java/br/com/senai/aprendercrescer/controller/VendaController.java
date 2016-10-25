
package br.com.senai.aprendercrescer.controller;

import br.com.senai.aprendercrescer.dao.UsuarioDao;
import br.com.senai.aprendercrescer.dao.VendaDao;
import br.com.senai.aprendercrescer.model.Usuario;
import br.com.senai.aprendercrescer.model.Venda;
import java.util.ArrayList;


public class VendaController {

    VendaDao vendaDao;

    public VendaController() {
        if (vendaDao == null) {
            vendaDao = new VendaDao();
        }
    }

    public boolean insereVenda(Venda venda) {

//        if (venda.getIdVenda() == 0) {
            return vendaDao.insereVenda(venda);
//        } else {
//            return vendaDao.updateVenda(venda);
//        }

    }

    
    
    public ArrayList<Venda> getVenda() {
        return vendaDao.getVenda();
    }
/*
    public boolean deleteUsuario(int id) {
        return vendaDao.deleteVenda(id);
    }
*/
}
