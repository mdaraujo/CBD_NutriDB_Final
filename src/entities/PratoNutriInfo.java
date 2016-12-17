/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Miguel
 */
public class PratoNutriInfo {
    
    private int energia;
    private float proteina;
    private float lipidos;
    private int colestrol;
    private float hidratos;
    private float fibra;

    public PratoNutriInfo() {
        energia = 0;
        proteina = 0;
        lipidos = 0;
        colestrol = 0;
        hidratos = 0;
        fibra = 0;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public float getProteina() {
        return proteina;
    }

    public void setProteina(float proteina) {
        this.proteina = proteina;
    }

    public float getLipidos() {
        return lipidos;
    }

    public void setLipidos(float lipidos) {
        this.lipidos = lipidos;
    }

    public int getColestrol() {
        return colestrol;
    }

    public void setColestrol(int colestrol) {
        this.colestrol = colestrol;
    }

    public float getHidratos() {
        return hidratos;
    }

    public void setHidratos(float hidratos) {
        this.hidratos = hidratos;
    }

    public float getFibra() {
        return fibra;
    }

    public void setFibra(float fibra) {
        this.fibra = fibra;
    }
    
    public void addEnergia(int energia) {
        this.energia += energia;
    }

    public void addProteina(float proteina) {
        this.proteina += proteina;
    }

    public void addLipidos(float lipidos) {
        this.lipidos += lipidos;
    }

    public void addColestrol(int colestrol) {
        this.colestrol += colestrol;
    }

    public void addHidratos(float hidratos) {
        this.hidratos += hidratos;
    }

    public void addFibra(float fibra) {
        this.fibra += fibra;
    }
    
    @Override
    public String toString()
    {
        return String.format("Energia: %d, Proteina: %f, Lipidos: %f, Colestrol: %d, Hidratos: %f, Fibra: %f", energia, proteina, lipidos, colestrol, hidratos, fibra);
    }
}
