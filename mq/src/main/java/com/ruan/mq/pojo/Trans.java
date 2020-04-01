package com.ruan.mq.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trans implements Serializable {

    private static final long serialVersionUID = 4111486680563617631L;

    String id;

    String type;

}
