package com.senacor.academy.decodevin.imageloader;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class VehicleImage {
  @Id
  @GeneratedValue
  private Long identifier;

  private String wmi;

  private String vds;

  private String imageUrl;

  public Long getIdentifier() {
    return identifier;
  }

  public void setIdentifier(Long identifier) {
    this.identifier = identifier;
  }

  public String getWmi() {
    return wmi;
  }

  public void setWmi(String wmi) {
    this.wmi = wmi;
  }

  public String getVds() {
    return vds;
  }

  public void setVds(String vds) {
    this.vds = vds;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
