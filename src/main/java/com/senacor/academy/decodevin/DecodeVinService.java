package com.senacor.academy.decodevin;

import com.senacor.academy.decodevin.imageloader.VehicleImage;
import com.senacor.academy.decodevin.imageloader.VehicleImageLoader;
import com.senacor.academy.decodevin.model.VehicleData;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("/vehicledata")
public class DecodeVinService {
  private DecodeVin decodeVin = new DecodeVin();
  private static Logger LOGGER = Logger.getLogger(DecodeVin.class.getName());

  @Inject
  VehicleImageLoader vehicleImageLoader;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{vin}")
  public Response decodeVIN(@PathParam("vin") final String vin) {
    if (vin == null || vin.length() != 17) {
      LOGGER.warning("invalid vin: " + vin);
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    LOGGER.info("lookup vin: " + vin);
    
    VehicleData vehicleData = decodeVin.decodeVin(vin);

    if (vehicleImageLoader != null && vehicleData != null) {
      VehicleImage vehicleImage = vehicleImageLoader.lookupImage(vehicleData.getWmi(), vehicleData.getVds());
      if (vehicleImage != null && vehicleImage.getImageUrl() != null) {
        vehicleData.setImage(vehicleImage.getImageUrl());
      }
    }

    return Response.ok(vehicleData).build();
  }
}
