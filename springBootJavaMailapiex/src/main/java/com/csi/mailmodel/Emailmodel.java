package com.csi.mailmodel;

import lombok.Data;

@Data
public class Emailmodel {

    private String emailTo[];

    private String emailCC[];

    private String emailSubject;

    private String emailBody;

    private String emailAttachment;
}
