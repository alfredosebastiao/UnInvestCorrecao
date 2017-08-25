
package c4.aula.modelo;

import java.util.Date;

/**
 *
 * @author User
 */
public class Fachineiro {
    
    private String nome;
    private Date dataIngresso;
    private double salario;
    private Date dataRegisto;
    private final double BONUS = 0.02;

    public Fachineiro(String nome, Date dataIngresso, double salario) {
        this.nome = nome;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
        this.setDataRegisto();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public double getSalario() {
        return salario;
    }
    
    public double getSalarioFinal() {
        return salario + (salario * this.BONUS);
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getDataRegisto() {
        return dataRegisto;
    }

    private void setDataRegisto() {
        this.dataRegisto = new Date();
    }
    
    
    
}
