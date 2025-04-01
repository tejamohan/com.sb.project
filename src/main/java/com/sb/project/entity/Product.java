package com.sb.project.entity;



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
	import lombok.EqualsAndHashCode;
	import lombok.NoArgsConstructor;


	@Data
	@Entity
	@Table(name="prod_tab")
	public class Product {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "prod_id")
		private int pid;
		//@EqualsAndHashCode.Include
		private String name;
		private String companyname;
		private double cost;
	
		
//		 @Override
//		    public boolean equals(Object o) {
//		       if (this == o) return true;
//		        if (o == null || getClass() != o.getClass()) return false;
//		        Emloyee employee = (Emloyee) o;
//		         return Objects.equals(name, employee.name);
//		    }
	//	
//		@Override
//		public int hashCode() {
//			// TODO Auto-generated method stub
//			return Objects.hash(name);
//		}

	}



