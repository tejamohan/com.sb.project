package com.sb.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDetailsDTO {
	
	 private String suppCode;
	    private Double pcItemValue;
	    private String itemName;
	    private Double itemQuantity;

}
