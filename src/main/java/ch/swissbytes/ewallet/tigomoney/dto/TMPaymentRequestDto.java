package ch.swissbytes.ewallet.tigomoney.dto;


import ch.swissbytes.ewallet.util.TMEntityUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TMPaymentRequestDto {

    private String orderId = null;
    private Integer line = null;
    private String correctUrl = null;
    private String errorUrl = null;
    private Double amount = null;
    private String name = TMEntityUtil.DEFAULT_STRING;
    private String documentNumber = TMEntityUtil.DEFAULT_STRING;
    private String notification = TMEntityUtil.DEFAULT_STRING;
    private String confirmation = TMEntityUtil.DEFAULT_STRING;
    private String message = TMEntityUtil.DEFAULT_STRING;
    private String businessName = TMEntityUtil.DEFAULT_STRING;
    private String nit = TMEntityUtil.DEFAULT_STRING;
    private List<TMPaymentItemDto> items = TMEntityUtil.DEFAULT_ARRAYLIST;

    //pv_orderId                (String[20],    required)
    //pv_linea                  (Integer[8],    required)
    //pv_urlCorrecto            (String[200],   required)
    //pv_urlError               (String[200],   required)
    //pv_monto                  (Double,        required)
    //pv_nombre                 (String[100],   optional, default value: "")
    //pv_nroDocumento           (String[10],    optional, default value: "0")
    //pv_notificacion           (String[20],    optional, default value: "")
    //pv_confirmacion           (String[20],    optional, default value: "")
    //pv_mensaje                (String[100],   optional, default value: "")
    //pv_razonSocial            (String[40],    optional, default value: "")
    //pv_nit                    (String[20],    optional, default value: "")
    //pv_items                  (String,        optional, default value: "")

    public String toString() {
        StringBuilder itemsStr = new StringBuilder();
        for (TMPaymentItemDto item : items)
            itemsStr.append(item.toString());

        StringBuilder result = new StringBuilder();
        result.append("pv_nroDocumento=");
        result.append(documentNumber);
        result.append(";pv_linea=");
        result.append(line);
        result.append(";pv_monto=");
        result.append(amount);
        result.append(";pv_orderId=");
        result.append(orderId);
        result.append(";pv_nombre=");
        result.append(name);
        result.append(";pv_confirmacion=");
        result.append(confirmation);
        result.append(";pv_notificacion=");
        result.append(notification);
        result.append(";pv_urlCorrecto=");
        result.append(correctUrl);
        result.append(";pv_urlError=");
        result.append(errorUrl);
        result.append(";pv_items=");
        result.append(itemsStr.toString());
        result.append(";pv_razonSocial=");
        result.append(businessName);
        result.append(";pv_nit=");
        result.append(nit);

        return result.toString();
    }
}
