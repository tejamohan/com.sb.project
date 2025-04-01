package com.sb.project.dto;

import java.time.LocalDate;
import java.util.List;

import com.sb.project.entity.ScmItemsNewEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ScmPcNewItemDTO {
	

	private String suppCode;

	private Double pcItemValue;

	private String pcItemRemarks;

	private Integer pcItemStatus;

	private String pcItemCreatedBy;

	@Column(name = "pc_created_date") // nullable = false, updatable = false, insertable = false, columnDefinition =
										// "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDate pcItemCreatedDate;

	private String pcItemModifiedBy;

	private LocalDate pcItemModifiedDate;

	private Integer approvedStatus;

	private String approvedBy;

	private LocalDate approvedDate;
	
	private List<ScmNewItemDTO> newItems;


}
