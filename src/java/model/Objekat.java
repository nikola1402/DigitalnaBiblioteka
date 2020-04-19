/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import org.bson.Document;

/**
 *
 * @author nikol
 */
public class Objekat {
    
    private String nazivDela, ime, prezime, jezik, kolekcija, stamparija, mesto;
    private Integer godina;
    
    public Objekat (String nazivDela, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto) {
        this.nazivDela = nazivDela;
        this.ime = ime;
        this.prezime = prezime;
        this.godina = godina;
        this.jezik = jezik;
        this.kolekcija = kolekcija;
        this.stamparija = stamparija;
        this.mesto = mesto;
    }
    
    public Objekat() {
    }
    
    
    public String getNazivDela() {
        return nazivDela;
    }

    public void setNazivDela(String nazivDela) {
        this.nazivDela = nazivDela;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJezik() {
        return jezik;
    }

    public void setJezik(String jezik) {
        this.jezik = jezik;
    }

    public String getKolekcija() {
        return kolekcija;
    }

    public void setKolekcija(String kolekcija) {
        this.kolekcija = kolekcija;
    }

    public String getStamparija() {
        return stamparija;
    }

    public void setStamparija(String stamparija) {
        this.stamparija = stamparija;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public Integer getGodina() {
        return godina;
    }

    public void setGodina(Integer godina) {
        this.godina = godina;
    }
    
    public Document sacuvajObjekat (String nazivDela, String ime, String prezime, Integer godina, String jezik, String kolekcija, String stamparija, String mesto) {
        
        Document objekat = new org.bson.Document("naziv_dela", nazivDela)
                            .append("ime", ime)
                            .append("prezime", prezime)
                            .append("godina", godina)
                            .append("jezik", jezik)
                            .append("kolekcija", kolekcija)
                            .append("stamparija", stamparija)
                            .append("mesto", mesto);
        
        
        return objekat;
    }
    
    public Integer prebroj(String nazivDela) {
        
                
        Integer count = 1;
        for (int i=0;i<=nazivDela.length()-1;i++)
        {
            if (nazivDela.charAt(i) == ' ' && nazivDela.charAt(i+1)!=' ')
            {
                count++;
            }
        }
        return count;
        
    }
    
    
}
