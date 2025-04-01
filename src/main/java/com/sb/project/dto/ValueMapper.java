package com.sb.project.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sb.project.entity.ScmItemsEntityNewPC;
import com.sb.project.entity.ScmItemsNewEntity;

public class ValueMapper {

    // Map ScmItemsEntityNewPC to DTO or another representation
    public static ScmPcNewItemDTO mapToDTO(
            ScmItemsEntityNewPC source
            
    ) {
        if (source == null ) {
            return null;
        }
        ScmPcNewItemDTO target=new ScmPcNewItemDTO();
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

        // Map the child collection
        if (source.getNewItems() != null) {
            List<ScmNewItemDTO> mappedItems = source.getNewItems()
                    .stream()
                    .map(ValueMapper::mapToScmItemsNewDTO)
                    .collect(Collectors.toList());
            target.setNewItems(mappedItems);
        }

        return target;
    }

    public static ScmItemsEntityNewPC mapToEntity(
    		ScmPcNewItemDTO source
            
    ) {
        if (source == null ) {
            return null;
        }
        ScmItemsEntityNewPC target=new ScmItemsEntityNewPC();
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

        // Map the child collection
        if (source.getNewItems() != null) {
            List<ScmItemsNewEntity> mappedItems = source.getNewItems()
                    .stream()
                    .map(ValueMapper::mapToScmItemsNewEntity)
                    .collect(Collectors.toList());
            target.setNewItems(mappedItems);
        }

        return target;
    }

    
    
    
    
    // Map ScmItemsNewEntity to DTO or another representation
    public static ScmNewItemDTO mapToScmItemsNewDTO(ScmItemsNewEntity source) {
        if (source == null) {
            return null;
        }

        ScmNewItemDTO target = new ScmNewItemDTO();
        target.setPciItemName(source.getPciItemName());
        target.setPciItemDesc(source.getPciItemDesc());
        target.setPciItemQuantity(source.getPciItemQuantity());
        target.setPciItemUnits(source.getPciItemUnits());
        target.setPciStatus(source.getPciStatus());
        target.setPciCreatedBy(source.getPciCreatedBy());
        target.setPciCreatedDate(source.getPciCreatedDate());
        target.setPciModifiedBy(source.getPciModifiedBy());
        target.setPciModifiedDate(source.getPciModifiedDate());
        target.setPciMake(source.getPciMake());
        target.setPciModel(source.getPciModel());
        target.setPciItemDeliveryDate(source.getPciItemDeliveryDate());

        return target;
    }
    public static ScmItemsNewEntity mapToScmItemsNewEntity(ScmNewItemDTO source) {
        if (source == null) {
            return null;
        }

        ScmItemsNewEntity target = new ScmItemsNewEntity();
        target.setPciItemName(source.getPciItemName());
        target.setPciItemDesc(source.getPciItemDesc());
        target.setPciItemQuantity(source.getPciItemQuantity());
        target.setPciItemUnits(source.getPciItemUnits());
        target.setPciStatus(source.getPciStatus());
        target.setPciCreatedBy(source.getPciCreatedBy());
        target.setPciCreatedDate(source.getPciCreatedDate());
        target.setPciModifiedBy(source.getPciModifiedBy());
        target.setPciModifiedDate(source.getPciModifiedDate());
        target.setPciMake(source.getPciMake());
        target.setPciModel(source.getPciModel());
        target.setPciItemDeliveryDate(source.getPciItemDeliveryDate());

        return target;
    }
    
}
