package com.senacor.academy.decodevin;

import com.senacor.academy.decodevin.model.VehicleData;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DecodeVinTest {
  private DecodeVin decodeVin = new DecodeVin();

  @Test
  public void decodeVin() {
    String vin = "WVWZZZ3BZWE689725";

    VehicleData data = decodeVin.decodeVin(vin);

    assertThat(data, notNullValue());
    assertThat(data.getWmi(), equalTo("WVW"));
    assertThat(data.getVds(), equalTo("ZZZ3BZ"));
    assertThat(data.getVis(), equalTo("WE689725"));
  }

  @Test
  public void decodeVinYear() {
    String vin = "WVWZZZ3BZWE689725";

    VehicleData data = decodeVin.decodeVin(vin);

    assertThat(data, notNullValue());
    assertThat(data.getYear(), equalTo(1998));
  }
}
