package com.sb.project.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.sb.project.dto.ItemDetailsDTO;
import com.sb.project.dto.ItemsJoinDTO;
import com.sb.project.entity.ScmItemsEntityNewPC;

import jakarta.transaction.Transactional;

@Transactional
public interface ScmNewItemsPcRepository extends JpaRepository<ScmItemsEntityNewPC, Long>{
	
	Optional<ScmItemsEntityNewPC> findByPcItemValue(Double pcItemValue);
	  
//	  @Transactional
//	  @Modifying
//	  @Query("UPDATE ScmItemsEntityNewPC s SET s.pcItemValue=:pcItemValue WHERE s.itenid=:itenid")
//      int updateItemPcValue(@Param("itenid") Long id,@Param("pcItemValue") Double pcItemValue );
	
	 @Query("SELECT new com.sb.project.dto.ItemDetailsDTO(p.suppCode, p.pcItemValue, i.pciItemName, i.pciItemQuantity) " +
	           "FROM ScmItemsEntityNewPC p JOIN p.newItems i " +
	           "WHERE p.pcItemStatus = :status")
	    List<ItemDetailsDTO> findItemsByStatus(Integer status);
	 
	 @Query("SELECT new com.sb.project.dto.ItemsJoinDTO(p.pcItemRemarks,p.pcItemCreatedBy,i.pciItemQuantity,i.pciItemUnits)"
		      +"FROM ScmItemsEntityNewPC p JOIN p.newItems i "+
		      "WHERE p.suppCode=:suppCode")
		     public List<ItemsJoinDTO> findItemsBySupCode(String suppCode);
	 
	 
	 @Procedure(procedureName="Scm_Get_item_By_Suppcode")
	 List<Object[]> getItemsBySuppCode(@Param("actionmode") String actionmode,
			                           @Param("createdBy") String createdBy,
			                           @Param("suppcode") String suppcode);

}
