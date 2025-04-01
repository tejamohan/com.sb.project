package com.sb.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemsJoinDTO {
	
	private String pcItemRemarks;
	private String pcItemCreatedBy;
	private double pciItemQuantity;
	private String pciItemUnits;

}
