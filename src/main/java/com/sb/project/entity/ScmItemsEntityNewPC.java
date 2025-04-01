package com.sb.project.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "new_items_pc_table")
public class ScmItemsEntityNewPC {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "new_item_id")
	private long itenid;

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
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "new_item_id")
	private List<ScmItemsNewEntity> newItems;

}
