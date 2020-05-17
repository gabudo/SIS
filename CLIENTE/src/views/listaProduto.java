/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import interfaces.InterfaceProduto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static layout.Janela.centro;

/**
 *
 * @author hitch
 */
public class listaProduto extends JPanel {
    
    private JButton btnAdicionar;
    private JButton btnEditar;
    private JButton btnExcluir;
    
    private static JTable tabela;
    private String[] colunas = {"ID", "DESCRICAO", "PRECO", "QNT"};
    private Object[][] dados;    
    private static DefaultTableModel modelo ;
    
    
    public listaProduto() throws NotBoundException, MalformedURLException{
        
        modelo = new DefaultTableModel(dados, colunas);
        
        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        
        btnAdicionar.addActionListener(new BotaoAdicionarListener());
        btnEditar.addActionListener(new BotaoEditarListener());
        btnExcluir.addActionListener(new BotaoExcluirListener());
        
        
        
        try{
              InterfaceProduto produtoRemoto = (InterfaceProduto) Naming.lookup("rmi://192.168.0.120:1099/Produto");  
           
           tabela = new JTable(modelo);
           
           ArrayList<InterfaceProduto> produtos = produtoRemoto.listar();
           
           for(InterfaceProduto produto: produtos){
             String id = Integer.toString(produto.getId());
             String descricao = produto.getDescricao();
             String preco = Double.toString(produto.getPreco());
             String quantidade = Integer.toString(produto.getQuantidade());
             
             String[] registro = new String[]{id, descricao, preco, quantidade};   
             
             //String[] registro = new String[]{"1", "1", "11", "1222"};   
               
             modelo.addRow(registro);             
           }
            
           add(tabela);
           
           add(btnAdicionar);
           add(btnEditar);
           add(btnExcluir);
        }catch(RemoteException re){
            
        }
    }

    
    private static class BotaoAdicionarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                  centro.removeAll();
                  centro.add( new formularioProduto(0));
                  centro.repaint();
                  centro.validate();
        }
    }

    private static class BotaoEditarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int registroSelecionado = tabela.getSelectedRow();
            
            if(registroSelecionado >= 0){
                 int idProduto = Integer.parseInt(tabela.getValueAt(registroSelecionado, 0).toString());
                 
                  centro.removeAll();
                  centro.add(new formularioProduto(idProduto));
                  centro.repaint();
                  centro.validate();
                   
            }else{
                JOptionPane.showMessageDialog(null, "Selecione um Registro");
            }
        }
    }
    
    private static class BotaoExcluirListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int registroSelecionado = tabela.getSelectedRow();
            if(registroSelecionado >= 0){            
                try {
                    int idProduto = Integer.parseInt(tabela.getValueAt(registroSelecionado, 0).toString());
                    String descricaoProduto = tabela.getValueAt(registroSelecionado, 1).toString();
                    
                    InterfaceProduto produtoRemotoExcluir = (InterfaceProduto) Naming.lookup("rmi://192.168.0.120:1099/Produto");
                    
                    produtoRemotoExcluir.excluir(idProduto);
                    modelo.removeRow(registroSelecionado);
                    
                    
                    JOptionPane.showMessageDialog(null, "o Registro "+descricaoProduto+" foi  excluido");
                } catch (RemoteException ex) {
                    
                } catch (NotBoundException ex) {
                    Logger.getLogger(listaProduto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(listaProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Selecione um Registro");
            }
            
            
        
        }
    }

    
}
