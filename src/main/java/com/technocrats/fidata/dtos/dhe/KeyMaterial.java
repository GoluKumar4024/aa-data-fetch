package com.technocrats.fidata.dtos.dhe;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeyMaterial {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DHPublicKey {

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        private Date expiry;

        @JsonProperty(value = "Parameters")
        private String Parameters;

        @JsonProperty(value = "KeyValue")
        private String KeyValue;
    }

    @JsonProperty(value = "DHPublicKey")
    DHPublicKey DHPublicKey;

    private String cryptoAlg;

    private String curve;

    private String params;


}
