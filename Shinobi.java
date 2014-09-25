package com.hendi.tes.shinobi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Senju Hashirama
 */
@XmlRootElement
public class Shinobi {
    String ID;
    String nama;
    String Level;
    String Jutsu;
    String Desa;

    public String getID() {
        return ID;
    }

    @XmlElement
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNama() {
        return nama;
    }

    @XmlElement
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLevel() {
        return Level;
    }

    @XmlElement
    public void setLevel(String Level) {
        this.Level = Level;
    }

    public String getJutsu() {
        return Jutsu;
    }

    @XmlElement
    public void setJutsu(String Jutsu) {
        this.Jutsu = Jutsu;
    }

    public String getDesa() {
        return Desa;
    }

    @XmlElement
    public void setDesa(String Desa) {
        this.Desa = Desa;
    }
    
    
    
}
