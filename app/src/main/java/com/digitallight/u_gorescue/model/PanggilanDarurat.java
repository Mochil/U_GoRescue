package com.digitallight.u_gorescue.model;

/**
 * Created by F on 1/17/2016.
 */
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class PanggilanDarurat {
	
private Integer idPangdar;
private String tanggal;
private Integer latitude;
private Integer longtitude;
private String namaJalan;
private String namaSubjalan;
private String namaDaerah;
private Integer idSopir;
private Integer idUser;

/**
* 
* @return
* The idPangdar
*/
public Integer getIdPangdar() {
return idPangdar;
}

/**
* 
* @param idPangdar
* The id_pangdar
*/
public void setIdPangdar(Integer idPangdar) {
this.idPangdar = idPangdar;
}

public PanggilanDarurat withIdPangdar(Integer idPangdar) {
this.idPangdar = idPangdar;
return this;
}

/**
* 
* @return
* The tanggal
*/
public String getTanggal() {
return tanggal;
}

/**
* 
* @param tanggal
* The tanggal
*/
public void setTanggal(String tanggal) {
this.tanggal = tanggal;
}

public PanggilanDarurat withTanggal(String tanggal) {
this.tanggal = tanggal;
return this;
}

/**
* 
* @return
* The latitude
*/
public Integer getLatitude() {
return latitude;
}

/**
* 
* @param latitude
* The latitude
*/
public void setLatitude(Integer latitude) {
this.latitude = latitude;
}

public PanggilanDarurat withLatitude(Integer latitude) {
this.latitude = latitude;
return this;
}

/**
* 
* @return
* The longtitude
*/
public Integer getLongtitude() {
return longtitude;
}

/**
* 
* @param longtitude
* The longtitude
*/
public void setLongtitude(Integer longtitude) {
this.longtitude = longtitude;
}

public PanggilanDarurat withLongtitude(Integer longtitude) {
this.longtitude = longtitude;
return this;
}

/**
* 
* @return
* The namaJalan
*/
public String getNamaJalan() {
return namaJalan;
}

/**
* 
* @param namaJalan
* The nama_jalan
*/
public void setNamaJalan(String namaJalan) {
this.namaJalan = namaJalan;
}

public PanggilanDarurat withNamaJalan(String namaJalan) {
this.namaJalan = namaJalan;
return this;
}

/**
* 
* @return
* The namaSubjalan
*/
public String getNamaSubjalan() {
return namaSubjalan;
}

/**
* 
* @param namaSubjalan
* The nama_subjalan
*/
public void setNamaSubjalan(String namaSubjalan) {
this.namaSubjalan = namaSubjalan;
}

public PanggilanDarurat withNamaSubjalan(String namaSubjalan) {
this.namaSubjalan = namaSubjalan;
return this;
}

/**
* 
* @return
* The namaDaerah
*/
public String getNamaDaerah() {
return namaDaerah;
}

/**
* 
* @param namaDaerah
* The nama_daerah
*/
public void setNamaDaerah(String namaDaerah) {
this.namaDaerah = namaDaerah;
}

public PanggilanDarurat withNamaDaerah(String namaDaerah) {
this.namaDaerah = namaDaerah;
return this;
}

/**
* 
* @return
* The idSopir
*/
public Integer getIdSopir() {
return idSopir;
}

/**
* 
* @param idSopir
* The id_sopir
*/
public void setIdSopir(Integer idSopir) {
this.idSopir = idSopir;
}

public PanggilanDarurat withIdSopir(Integer idSopir) {
this.idSopir = idSopir;
return this;
}

/**
* 
* @return
* The idUser
*/
public Integer getIdUser() {
return idUser;
}

/**
* 
* @param idUser
* The id_user
*/
public void setIdUser(Integer idUser) {
this.idUser = idUser;
}

public PanggilanDarurat withIdUser(Integer idUser) {
this.idUser = idUser;
return this;
}
}