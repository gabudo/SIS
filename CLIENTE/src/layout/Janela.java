/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import views.formularioProduto;
import views.listaProduto;

public class Janela extends JFrame{    
    public JPanel menuTopo = new JPanel();  
    public static JPanel centro = new JPanel(); 
    
    public GridLayout layoutTopo = new GridLayout();
    public BorderLayout layoutJanela = new BorderLayout();   
    
    public JButton botaoProduto = new JButton("Produtos");
    public JButton botaoCliente = new JButton("Clientes");
    public JButton botaoFuncionario = new JButton("Funcionarios");
    
  public Janela(){     
      
      botaoProduto.addActionListener( new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              try {
                  centro.removeAll();
                  centro.add( new listaProduto());
                  centro.repaint();
                  centro.validate();
              } catch (NotBoundException ex) {
                  Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
              } catch (MalformedURLException ex) {
                  Logger.getLogger(Janela.class.getName()).log(Level.SEVERE, null, ex);
              }
             
          }
      });
      
      botaoCliente.addActionListener( new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) { }
      });
      
      botaoFuncionario.addActionListener( new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) { }
      });
      
      menuTopo.setLayout(layoutTopo);
      menuTopo.add(botaoProduto);
      menuTopo.add(botaoCliente);
      menuTopo.add(botaoFuncionario);
            
      setLayout(layoutJanela);
      add(menuTopo, BorderLayout.NORTH);
       
      add(centro, BorderLayout.CENTER);
      setSize(280, 400);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
  }  
}
