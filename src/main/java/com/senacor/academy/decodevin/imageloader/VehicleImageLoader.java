package com.senacor.academy.decodevin.imageloader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Named
@RequestScoped
public class VehicleImageLoader {
  private static Logger LOGGER = Logger.getLogger(VehicleImageLoader.class.getName());

  @PersistenceContext
  private EntityManager entityManager;
  // lookup the vehicle image
  //
  // To fill the DB, you will need to connect to the DB and insert some values.
  // Go to the Openshift Web Console, Navigate to the Pod and click on "Terminal".
  //
  // 1. Execute psql and connect to your DB
  // psql -d sampledb -U username -W
  // 2. See if the table is already there. The application should have created it already.
  // select * from vehicleimage;
  // 3. Insert some image urls into the DB
  // insert into vehicleimage (identifier, imageurl, wmi, vds) values (1, 'some url', 'WVW', 'ZZZ3BZ');
  //
  // Alternatively use the oc tool to connect to the pod
  // 1. Login to the pod
  // oc login <openshift web consule url>
  // 2. Connect to the project
  // oc project <your project name>
  // 3. Get the name of the postgres pod
  // oc get pods
  // 4. Login to the pod
  // oc rsh <pod name>
  // continue like above
  public VehicleImage lookupImage(String wmi, String vds) {
    if (entityManager == null) {
      LOGGER.warning("entityManager is null");
      return null;
    }

    // build and execute query to fetch the image url from the db
    String query = "select vi from VehicleImage vi where vi.wmi = :wmi and vi.vds = :vds";

    TypedQuery<VehicleImage> typedQuery = entityManager.createQuery(query, VehicleImage.class);

    typedQuery.setParameter("wmi", wmi);
    typedQuery.setParameter("vds", vds);

    List<VehicleImage> result = typedQuery.getResultList();

    if (result.size() > 0) {
      return result.get(0);
    } else {
      return null;
    }
  }

}
