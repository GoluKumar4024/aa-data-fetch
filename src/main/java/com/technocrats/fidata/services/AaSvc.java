package com.technocrats.fidata.services;

import com.technocrats.fidata.constants.AaConstants;
import com.technocrats.fidata.dtos.aa.AAFiDataReqDto;
import com.technocrats.fidata.dtos.aa.AAFiDataRespDto;
import com.technocrats.fidata.dtos.aa.FetchedDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.technocrats.fidata.constants.AaConstants.AA_HEART_BEAT_URI;

@Service
@RequiredArgsConstructor
public class AaSvc {

    private final WebClient webClientAA;
    private final DHEncSvc dhEncSvc;

    public String checkHeartBeat() {
        String heartbeat = webClientAA.get()
                .uri(AA_HEART_BEAT_URI)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        // log to mongodb here
        return heartbeat;
    }

    public AAFiDataRespDto sendDataFetchReq(AAFiDataReqDto AAFiDataReqDto) {
        return webClientAA.post()
                .uri(AaConstants.AA_FI_DATA_FETCH_REQ)
                .body(Mono.just(AAFiDataReqDto), AAFiDataReqDto.class)
                .retrieve()
                .bodyToMono(AAFiDataRespDto.class)
                .block();
    }

    public FetchedDataDto fetchDataForSession(String sessionId) {
        return webClientAA.get()
                .uri(AaConstants.AA_FI_DATA_FETCH_FOR_SESSION + "/" + sessionId)
                .retrieve()
                .bodyToMono(FetchedDataDto.class)
                .block();
    }


}
