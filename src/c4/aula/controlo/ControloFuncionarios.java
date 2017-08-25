
package c4.aula.controlo;

import c4.aula.modelo.Caixa;
import c4.aula.modelo.Fachineiro;
import c4.aula.modelo.Gestor;
import c4.aula.modelo.ModeloTabela;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public class ControloFuncionarios {
    
    private Gestor[] gestores = new Gestor[100];
    private Caixa[] caixas = new Caixa[100];
    private Fachineiro[] fachineiros = new Fachineiro[100];
    private int totalGestores = 0;
    private int totalCaixas = 0;
    private int totalFachineiros = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    
    public boolean addicionar(Gestor gestor){        
        this.gestores[totalGestores] = gestor;
        totalGestores++;
        return true;
    }
    
    public boolean addicionar(Caixa caixa){        
        this.caixas[totalCaixas] = caixa;
        totalCaixas++;
        return true;
    }
    
    public boolean addicionar(Fachineiro fachineiro){        
        this.fachineiros[totalFachineiros] = fachineiro;
        totalFachineiros++;
        return true;
    }
    
    public JTable listar(JTable table){
        String[] colunas = new String[]{
            "Nome","Data de Ingresso","Data de Cadastro","Categoria","Salario"
        };
        
        ArrayList dados = new ArrayList();
        
        //Vamos os g
        dados = buscarGestores(dados);        
        
        //Vamos os C
        dados = buscarCaixas(dados);
        
        //Vamos os F
        dados = buscarFachineiros(dados);
                
        
        ModeloTabela m = new ModeloTabela(dados, colunas);
        table.setModel(m);
        
        return table; 
    }
    
    private ArrayList buscarGestores(ArrayList dados){
        for (int i = 0; i < this.totalGestores; i++) {            
            dados.add(new Object[]{
                  this.gestores[i].getNome(),
                    sdf.format(this.gestores[i].getDataIngresso()),
                    sdf.format(this.gestores[i].getDataRegisto()),
                    " Gestor ",
                    this.gestores[i].getSalarioFinal(),
                    
            });            
        }
        return dados;
    }
    
    private ArrayList buscarCaixas(ArrayList dados){
        
        for (int i = 0; i < this.totalCaixas; i++) {            
            dados.add(new Object[]{
                  this.caixas[i].getNome(),
                    sdf.format(this.caixas[i].getDataIngresso()),
                    sdf.format(this.caixas[i].getDataRegisto()),
                    " Caixa ",
                    this.caixas[i].getSalarioFinal(),
                    
            });            
        }
        
        return dados;
    }
    
    private ArrayList buscarFachineiros(ArrayList dados){
        
        for (int i = 0; i < this.totalFachineiros; i++) {            
            dados.add(new Object[]{
                  this.fachineiros[i].getNome(),
                    sdf.format(this.fachineiros[i].getDataIngresso()),
                    sdf.format(this.fachineiros[i].getDataRegisto()),
                    " Fachineiro ",
                    this.fachineiros[i].getSalarioFinal(),
                    
            });            
        }
        
        return dados;
    }
    
    public int getTotalGestores() {
        return totalGestores;
    }

    public int getTotalCaixas() {
        return totalCaixas;
    }

    public int getTotalFachineiros() {
        return totalFachineiros;
    }
    
    /**
     * Metodo chamado para remover um deteriminado funcionario da lista
     * @param jTable1
     * @param selectedRow 
     */
    public void remover(JTable jTable1, int selectedRow) {
       
        String nome  = (String) jTable1.getValueAt(selectedRow,0);
        String categoria  = (String) jTable1.getValueAt(selectedRow,3);
        
        removerFuncionario(jTable1, nome, categoria);
        
    }
    
    
    
    /**
     * Metodo usado para pesquisar o funcionario a ser removido em uma determinada lista
     * (ou na lista de gestores ou caixas ou fachineiros) e de seguida eh feita 
     * uma actualizacao na lista que guarda os dados de todos funcionarios apresentados na tabela
     * @param jtable1
     * @param nome
     * @param categoria 
     */
    private void removerFuncionario(JTable jtable1, String nome,String categoria){
        
        if(categoria.equalsIgnoreCase( " Gestor ")){
            removerNoArray(nome, this.gestores);
        }
            
        if(categoria.equalsIgnoreCase(" Caixa ")){
            removerNoArray(nome, this.caixas);
        }
           
        if(categoria.equalsIgnoreCase(" Fachineiro ")){
            removerNoArray(nome, this.fachineiros);
        }
            
        listar(jtable1);
        
    }
    
    
    
    /**
     * Metodo usado para remover um gestor na lista, usando o nome como chave
     * @param nome
     * @param gestores 
     */
    private void removerNoArray(String nome, Gestor[] gestores){
        for(int i = 0; i < totalGestores; i++){
            if(gestores[i].getNome().equalsIgnoreCase(nome)){  
                this.gestores = organizarArray(i,totalGestores,gestores);
                this.totalGestores--; 
                break;
            }
        }
    }
    
    
    /**
     *  Metodo usado para remover um caixa na lista, usando o nome como chave
     * @param nome
     * @param caixas 
     */
    private void removerNoArray(String nome, Caixa[] caixas){
        for(int i = 0; i < totalCaixas; i++){
            if(caixas[i].getNome().equalsIgnoreCase(nome)){  
                this.caixas = organizarArray(i,totalCaixas,caixas);
                this.totalCaixas--;
                break;
            }
        }
    }
    
    
    /**
     * Metodo usado para remover um fachineiro na lista, usando o nome como chave
     * @param nome
     * @param fachineiros 
     */
    private void removerNoArray(String nome, Fachineiro[] fachineiros){
        for(int i = 0; i < totalFachineiros; i++){
            if(fachineiros[i].getNome().equalsIgnoreCase(nome)){  
                this.fachineiros = organizarArray(i,totalGestores,fachineiros);
                this.totalFachineiros--;
                break;
            }
        }
    }
    
    
    /**
     * Metodo usado para organizar o array de gestores apos uma remocao
     * @param i
     * @param quant
     * @param gestores
     * @return 
     */
    private Gestor[] organizarArray(int i, int quant, Gestor[] gestores) {
        for(int j = i; j < quant - 1; j++ ){
            gestores[j] = gestores[j+1];
        }
        return gestores;
    }
    
    
    /**
     * Metodo usado para organizar o array de caixas apos uma remocao
     * @param i
     * @param quant
     * @param caixas
     * @return 
     */
    private Caixa[] organizarArray(int i, int quant, Caixa[] caixas) {
        
        for(int j = i; j < quant - 1; j++ ){
            caixas[j] = caixas[j+1];
        }
        return caixas;
    }
    
    /**
     * Metodo usado para organizar o array de fachineiros apos uma remocao
     * @param i
     * @param quant
     * @param fachineiros
     * @return 
     */
    private Fachineiro[] organizarArray(int i, int quant, Fachineiro[] fachineiros) {
        for(int j = i; j < quant - 1; j++ ){
            fachineiros[j] = fachineiros[j+1];
        }
        
        return fachineiros;
    }
    
    
    
//    private ArrayList ordendar(ArrayList dados) {
//        
//        dados.sort(new Comparator() {
//
//            @Override
//            public int compare(Object t, Object t1) {
//                Entrada e1, e2;
//
//                e1 = (Entrada) t;
//                e2 = (Entrada) t1;
//
//                if (e1.getDataValidade() != null) {
//                    
//                    if (e1.getDataValidade().after(e2.getDataValidade())) {
//                        return 1;
//                    }
//                    if (e1.getDataValidade().before(e2.getDataValidade())) {
//                        return -1;
//                    }
//
//                }
//
//                return 0;
//            }
//        });
//
//    }
    
    
    
}
