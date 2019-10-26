package BussinessLogicLayer.Entities;

import java.util.Date;

public class Shipment {
    public String shipmentId;

    public String trackingNumber;

    public TrackingInfo trackingInfo;

    public ServiceType serviceType;

    public Contact receiver;

    public Contact sender;
}
