package ch.swissbytes.ewallet.tigomoney.dto;

import ch.swissbytes.ewallet.tigomoney.util.TMStatusResponseMsg;
import ch.swissbytes.ewallet.util.TMEntityUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

@Getter
@Setter
@ToString
public class TMStatusResponseDto {
    //0;http://190.129.208.178:96/developer/faces/proceso.xhtml?id=123
    private Integer code;
    private String url;
    private String description;

    public static TMStatusResponseDto createNew() {
        val result = new TMStatusResponseDto();
        result.setCode(TMEntityUtil.DEFAULT_ERROR_CODE);
        result.setUrl(TMEntityUtil.DEFAULT_STRING);
        result.setDescription(TMEntityUtil.DEFAULT_STRING);
        return result;
    }

    public static TMStatusResponseDto createNew(String serverResult) {
        val values = serverResult.split(";");
        val result = new TMStatusResponseDto();
        result.setCode(new Integer(values[0]));
        result.setUrl(values[1]);
        result.setDescription(TMStatusResponseMsg.get(result.getCode()));
        return result;
    }
}
