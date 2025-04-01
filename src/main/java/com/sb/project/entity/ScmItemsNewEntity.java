package com.sb.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "new_items_entity")
public class ScmItemsNewEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "new_item_pcc_id")
	private long pcItemID;

	@Column(name = "item_name")
	private String pciItemName;

	@Column(name = "item_desc")
	private String pciItemDesc;

	@Column(name = "item_quantity")
	private Double pciItemQuantity;

	@Column(name = "item_units")
	private String pciItemUnits;

	@Column(name = "item_status")
	private Integer pciStatus = 1;

	@Column(name = "item_created_by")
	private String pciCreatedBy;

	@Column(name = "item_created_date") // nullable = false, updatable = false)
	private LocalDateTime pciCreatedDate;

	@Column(name = "item_modified_by")
	private String pciModifiedBy;

	@Column(name = "item_modified_date")
	private LocalDateTime pciModifiedDate;

	@Column(name = "item_make")
	private String pciMake;

	@Column(name = "item_model")
	private String pciModel;

	@Column(name = "item_delivery_date")
	private String pciItemDeliveryDate;

}
