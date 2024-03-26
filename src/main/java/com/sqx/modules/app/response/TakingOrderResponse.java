package com.sqx.modules.app.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class TakingOrderResponse implements Serializable {

  private  int orderNumber;
  private  String gameName;
  private  double  payMoney;







}
