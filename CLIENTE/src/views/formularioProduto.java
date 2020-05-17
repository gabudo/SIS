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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hitch
 */
public class formularioProduto extends JPanel implements ActionListener{
    JTextField campo_descricao;
    JTextField campo_preco;
    JTextField campo_quantidade;
    JButton botao_salvar;
    int idProduto = 0;
            
    public formularioProduto(int id){
        
        try {
            InterfaceProduto produtoRemoto = (InterfaceProduto) Naming.lookup("rmi://192.168.0.120:1099/Produto");
            
            JLabel label_descricao = new JLabel("Descrição: ");
            campo_descricao = new JTextField(20);
            JLabel label_preco = new JLabel("Preço: ");
            campo_preco = new JTextField(20);
            JLabel label_quantidade = new JLabel("Quantidade: ");
            campo_quantidade = new JTextField(20);
            
            botao_salvar = new JButton("Salvar");
            botao_salvar.addActionListener(this);
            
            if(id > 0){
                try{
                    produtoRemoto.pegaDadosProduto(id);
                    campo_descricao.setText(produtoRemoto.getDescricao());
                    campo_preco.setText(Double.toString(produtoRemoto.getPreco()));        
                    campo_quantidade.setText(Integer.toString(produtoRemoto.getQuantidade()));
                    idProduto = produtoRemoto.getId();
                
                }catch(RemoteException e){
                    System.out.print(e.toString());
                }
               
            }
            
            add(label_descricao);
            add(campo_descricao);
            add(label_preco);
            add(campo_preco);
            add(label_quantidade);
            add(campo_quantidade);
            add(botao_salvar);
        } catch (NotBoundException ex) {
            Logger.getLogger(formularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(formularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(formularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {       
            
            String string_descricao = campo_descricao.getText();
            String string_preco = campo_preco.getText();
            String string_quantidade = campo_quantidade.getText();
                       
            String descricao = string_descricao;
            double preco = Double.parseDouble(string_preco);
            int quantidade = Integer.parseInt(string_quantidade);
            
        try{    
            InterfaceProduto produtoRemoto = (InterfaceProduto) Naming.lookup("rmi://192.168.0.120:1099/Produto");            
            produtoRemoto.setDescricao(descricao);
            produtoRemoto.setPreco(preco);
            produtoRemoto.setQuantidade(quantidade);
                
            
            if(idProduto>0){
                produtoRemoto.setId(idProduto);
                produtoRemoto.atualizar();
            }else{
                produtoRemoto.adicionar();
            }
            
                      
        }catch(RemoteException re){
            JOptionPane.showMessageDialog(null, "Erro Remoto:"+re.toString(), "Erro Remoto", JOptionPane.WARNING_MESSAGE);
        } catch (NotBoundException ex) {
            Logger.getLogger(formularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(formularioProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
