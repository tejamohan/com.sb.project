package com.sb.project.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScmNewItemDTO {
	
	private String pciItemName;

	private String pciItemDesc;


	private Double pciItemQuantity;


	private String pciItemUnits;


	private Integer pciStatus = 1;


	private String pciCreatedBy;

 
	private LocalDateTime pciCreatedDate;

	private String pciModifiedBy;

	private LocalDateTime pciModifiedDate;

	private String pciMake;

	private String pciModel;

	private String pciItemDeliveryDate;
	

}
