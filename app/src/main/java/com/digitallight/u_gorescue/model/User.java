package com.digitallight.u_gorescue.model;

/**
 * Created by F on 1/17/2016.
 */
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")

public class User {
    private String idUser;
    private String username;
    private String password;
    private String noIdentitas;
    private String nama;
    private String nomorHp;
    private String email;

    /**
     *
     * @return
     * The idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     * The id_user
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public User withIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public User withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     *
     * @return
     * The noIdentitas
     */
    public String getNoIdentitas() {
        return noIdentitas;
    }

    /**
     *
     * @param noIdentitas
     * The no_identitas
     */
    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public User withNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
        return this;
    }

    /**
     *
     * @return
     * The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     *
     * @param nama
     * The nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    public User withNama(String nama) {
        this.nama = nama;
        return this;
    }

    /**
     *
     * @return
     * The nomorHp
     */
    public String getNomorHp() {
        return nomorHp;
    }

    /**
     *
     * @param nomorHp
     * The nomor_hp
     */
    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
    }

    public User withNomorHp(String nomorHp) {
        this.nomorHp = nomorHp;
        return this;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }
}
