package com.sb.project.service;

import java.util.List;

import com.sb.project.dto.ItemDetailsDTO;
import com.sb.project.dto.ItemsJoinDTO;
import com.sb.project.dto.ScmPcNewItemDTO;
import com.sb.project.dto.SuppcodeProcdureCallDTO;
import com.sb.project.entity.ScmItemsEntityNewPC;
import com.sb.project.exception.ItemNotFoundException;

public interface ScmNewItemService {
	
	public String saveItems(ScmPcNewItemDTO saveItem) throws ItemNotFoundException;
	
	public List<ScmItemsEntityNewPC> getAllItems();
	
	public ScmPcNewItemDTO getByPcItemValue(Double pcItemValue) throws ItemNotFoundException;
	
	public String updateDatePcValue(Long id,ScmPcNewItemDTO pcValue) throws ItemNotFoundException;
	
	public List<ItemDetailsDTO> getItemsByStatus(Integer status);
	
	public List<ItemsJoinDTO> getItemsBySupCode(String suppCode);
	
	public List<Object[]> getItemsBySuppProcedureCall(SuppcodeProcdureCallDTO callPro);
	
	public List<ScmPcNewItemDTO> getAllItemsByDescOrder(String field);
	
	public List<ScmPcNewItemDTO> getListByPagination(int offset,int size);
	
	
	

}
