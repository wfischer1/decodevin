package com.senacor.academy.decodevin;

import com.senacor.academy.decodevin.model.VehicleData;

public class DecodeVin {

  private static final String YEARCHARS = "ABCDEFGHJKLMNPRSTVWXY123456789";

  public VehicleData decodeVin(String vin) {
    VehicleData data = new VehicleData();

    data.setWmi("unknown");
    data.setVds("unknown");
    data.setVis("unknown");
    data.setYear(0);

    // wmi is chars 1..3
    data.setWmi(vin.substring(0, 3));

    // vds is chars 4..9
    data.setVds(vin.substring(3, 9));

    // vis is chars 10..17
    data.setVis(vin.substring(9));

    char yearCode = vin.charAt(9);

    int year = YEARCHARS.indexOf(yearCode);
    if (year > 0) {
      data.setYear(1980 + year);
    } else {
      data.setYear(0);
    }

    return data;
  }
}
