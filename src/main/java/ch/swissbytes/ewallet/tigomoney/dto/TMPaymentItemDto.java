package ch.swissbytes.ewallet.tigomoney.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TMPaymentItemDto {

    private String serial;
    private Integer quantity;
    private String concept;
    private Double unitPrice;
    private Double totalPrice;

    public static TMPaymentItemDto createNew(String aSerial, Integer aQuantity, String aConcept, Double aUnitPrice, Double aTotalPrice) {
        TMPaymentItemDto item = new TMPaymentItemDto();
        item.setSerial(aSerial);
        item.setQuantity(aQuantity);
        item.setConcept(aConcept);
        item.setUnitPrice(aUnitPrice);
        item.setTotalPrice(aTotalPrice);
        return item;
    }

    public String toString() {
        return String.format("*¡%s|%s|%s|%s|%s", serial, quantity, concept, unitPrice, totalPrice);
    }
}
