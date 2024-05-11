package com.microshop.stockmanagement.productcacheservice.response;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class InternalApiResponse<T>{ // <T> yi eklediğpimiz i,çin classımız generic bir class oldu.
    // HATA MESAJI BAŞARI MESAJI VB BÜTÜN MESAJLARI İÇERECEK CLASS DIR.

    private FriendlyMessage friendlyMessage; // mesajın title ı ve descr,pton ı bulunuyortr olacak
    private T payload; // T dediğimiz için generic oldu ve istediğimiz herhangi bir type ı verebiliriz.
    private boolean hasError;
    private List<String> errorMessage;
    private HttpStatus httpStatus;
}
