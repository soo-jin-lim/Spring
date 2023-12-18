package com.example.springexam03.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value=1) //최소값
    @Positive  //양수만 가능
    private int page=1;

    @Builder.Default
    @Min(value=3)
    @Max(value = 100)
    @Positive
    private int size=3;

    private String[] types;
    private String keyword;
    private LocalDate from;
    private LocalDate to;
    public int getSkip(){
        return (page-1)*3;
    }
    public String getLink(){
        StringBuilder builder=new StringBuilder();

        builder.append("page="+this.page);
        builder.append("&size="+this.size);

        if(types !=null && types.length >0){
            for(int i=0; i< types.length; i++){
                builder.append("&types="+types[i]);
            }
        }
        if(keyword != null){
            try{
                builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        if(from != null){
            builder.append("&from="+from.toString());
        }
        if(to !=null){
            builder.append("&to="+to.toString());
        }
        return builder.toString();
    }
    public boolean checkType(String type){
        if(types == null || types.length==0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }
}
