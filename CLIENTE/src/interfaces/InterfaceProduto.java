
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface InterfaceProduto extends Remote{
    
    /**
     *
     * @param id
     * @throws RemoteException
     */
    public void setId(int id) throws RemoteException; 
    public void setDescricao(String descricao) throws RemoteException;   
    public void setPreco(double preco) throws RemoteException;    
    public void setQuantidade(int quantidade) throws RemoteException;    
    public int getId() throws RemoteException;    
    public String getDescricao() throws RemoteException;    
    public double getPreco() throws RemoteException;    
    public int getQuantidade() throws RemoteException;
    
    
    
    public void adicionar() throws RemoteException; 
    
    public void atualizar() throws RemoteException;
    
    public void excluir(int id) throws RemoteException;
    
    public ArrayList<InterfaceProduto> listar() throws RemoteException;
    
    
    public void pegaDadosProduto(int idproduto) throws RemoteException;

   
    
}
