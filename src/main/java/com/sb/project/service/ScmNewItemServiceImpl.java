package com.sb.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sb.project.dto.ItemDetailsDTO;
import com.sb.project.dto.ItemsJoinDTO;
import com.sb.project.dto.ScmPcNewItemDTO;
import com.sb.project.dto.SuppcodeProcdureCallDTO;
import com.sb.project.dto.ValueMapper;
import com.sb.project.entity.ScmItemsEntityNewPC;
import com.sb.project.exception.ItemNotFoundException;
import com.sb.project.repo.ScmNewItemsPcRepository;

import jakarta.transaction.Transactional;import jakarta.xml.bind.annotation.adapters.CollapsedStringAdapter;

@Service
public class ScmNewItemServiceImpl implements ScmNewItemService{
	
	@Autowired
	private ScmNewItemsPcRepository repo;

	@Override
	public String saveItems(ScmPcNewItemDTO saveItem) throws ItemNotFoundException {
		
		Optional<ScmPcNewItemDTO> hasItem=Optional.ofNullable(saveItem);
		ScmItemsEntityNewPC entityItem=new ScmItemsEntityNewPC();
		try {
		if(hasItem.isPresent()) {
			BeanUtils.copyProperties(hasItem.get(), entityItem);
			entityItem=  repo.save(entityItem);
			
		} 
		}catch(Exception e) {
			throw new ItemNotFoundException("Item Not Found");
			}
		return entityItem!=null?"Item saved successfully":"Item not saved";
	
	
		 
	}
	
	

	@Override
	public List<ScmItemsEntityNewPC> getAllItems() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public ScmPcNewItemDTO getByPcItemValue(Double pcItemValue) throws ItemNotFoundException {
	      Optional<Double> getPcValue= Optional.ofNullable(pcItemValue);
	      Optional<ScmItemsEntityNewPC> getItem= null;
	      ScmPcNewItemDTO dto=new ScmPcNewItemDTO();
	      try {
	         if(getPcValue.isPresent()) {
	         getItem=	  repo.findByPcItemValue(getPcValue.get());
	         }
	      BeanUtils.copyProperties(getItem.get(), dto);
	      }catch(Exception e){
	    	  throw new ItemNotFoundException("No Item Found With this id");
	      }
		return dto ;
	}

   

	@Override
	public String updateDatePcValue(Long id,ScmPcNewItemDTO source) throws ItemNotFoundException {
		Optional<Long> getId= Optional.ofNullable(id);
		//Optional<ScmPcNewItemDTO> getPcValue= Optional.ofNullable(pcValue);
		//System.out.println(getPcValue.get());
		ScmItemsEntityNewPC target=null;
		//Integer updateItem=null;
		 try {
	         if(getId.isPresent()) {
	        	 target=repo.findById(id).orElseThrow(()->new ItemNotFoundException("No Item With Id"));
	        	 System.out.println(target);
	        	  target.setSuppCode(source.getSuppCode());
	              target.setPcItemValue(source.getPcItemValue());
	              target.setPcItemRemarks(source.getPcItemRemarks());
	              target.setPcItemStatus(source.getPcItemStatus());
	              target.setPcItemCreatedBy(source.getPcItemCreatedBy());
	              target.setPcItemCreatedDate(source.getPcItemCreatedDate());
	              target.setPcItemModifiedBy(source.getPcItemModifiedBy());
	              target.setPcItemModifiedDate(source.getPcItemModifiedDate());
	              target.setApprovedStatus(source.getApprovedStatus());
	              target.setApprovedBy(source.getApprovedBy());
	              target.setApprovedDate(source.getApprovedDate());

	        	 System.out.println(target);
	        	 target=repo.save(target);
	        	System.out.println(target);
	         }
	      }catch(Exception e){
	    	  throw new ItemNotFoundException("No Item Found With this id");
	      }
		return target!=null?"item updated Successfully":"item not updated";
	}



	@Override
	public List<ItemDetailsDTO> getItemsByStatus(Integer status) {
		
		List<ItemDetailsDTO> getItems=repo.findItemsByStatus(status);
		
		return getItems;
	}



	@Override
	public List<ItemsJoinDTO> getItemsBySupCode(String suppCode) {
		List<ItemsJoinDTO> getItemsSuppCode=repo.findItemsBySupCode(suppCode);
		return getItemsSuppCode;
	}


    @Transactional
	@Override
	public List<Object[]> getItemsBySuppProcedureCall(SuppcodeProcdureCallDTO callPro) {
		  String actionMode=callPro.getActionmode();
		  String createdBy=callPro.getCratedby();
		  String suppcode=callPro.getSuppcode();
		                  
		List<Object[]> getItems=repo.getItemsBySuppCode(actionMode, createdBy, suppcode);
		return getItems;
	}



	@Override
	public List<ScmPcNewItemDTO> getAllItemsByDescOrder(String field) {
		  List<ScmPcNewItemDTO> getAllItems=new ArrayList<>();
	       List<ScmItemsEntityNewPC>  getAllByDesc=repo.findAll(Sort.by(Sort.Direction.DESC, field));
	       getAllItems= getAllByDesc.stream().map(ValueMapper::mapToDTO).collect(Collectors.toList());
	         
		return getAllItems;
	}



	@Override
	public List<ScmPcNewItemDTO> getListByPagination(int offset, int size) {
		Page<ScmItemsEntityNewPC> getAllItemsPagi=null; 
		getAllItemsPagi= repo.findAll(PageRequest.of(offset, size));
		List<ScmPcNewItemDTO> getNewItems= getAllItemsPagi.stream().map(ValueMapper::mapToDTO).collect(Collectors.toList());
		return getNewItems;
	}




	
}
