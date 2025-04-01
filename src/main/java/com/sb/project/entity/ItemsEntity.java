package com.sb.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="items_by_order")
public class ItemsEntity {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "new_item_id")
	private long itemId;
	private String itemName;
	private double itemPrice;
	private String orderStatus;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate itemManfacDate;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate itemExpiredcDate;
	private long vendorContact;
	private String vendorEmail;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "itemId")
	List<Product> products;
	
}
