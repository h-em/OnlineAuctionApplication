package com.sda.auction.util;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Halip on 17.11.2019.
 */
@Service
public class DateConvertor {

    public String formatter(Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormatter.format(date);
    }

    public Date parsser(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(dateInString);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
