package ch.swissbytes.ewallet.tigomoney.dto;

import ch.swissbytes.ewallet.util.TMEntityUtil;
import ch.swissbytes.ewallet.tigomoney.util.TMPaymentResponseMsg;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

@Getter
@Setter
@ToString
public class TMPaymentResponseDto {
    //codRes=0&mensaje=La Transaccion se realizó de manera exitosa &orderId=1215
    private Integer code;
    private String message;
    private String orderId;
    private String description;

    public static TMPaymentResponseDto createNew() {
        val result = new TMPaymentResponseDto();
        result.setCode(TMEntityUtil.DEFAULT_ERROR_CODE);
        result.setMessage(TMEntityUtil.DEFAULT_STRING);
        result.setOrderId(TMEntityUtil.DEFAULT_STRING);
        result.setDescription(TMEntityUtil.DEFAULT_STRING);
        return result;
    }

    public static TMPaymentResponseDto createNew(String serviceResult) {
        val values = serviceResult.split("&");
        val result = new TMPaymentResponseDto();
        result.setCode(new Integer(values[0].split("=")[1]));
        result.setMessage(values[1].split("=")[1]);
        result.setOrderId(values[2].split("=")[1]);
        result.setDescription(TMPaymentResponseMsg.get(result.getCode()));
        return result;
    }
}
