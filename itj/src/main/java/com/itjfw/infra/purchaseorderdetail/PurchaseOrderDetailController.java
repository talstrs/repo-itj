package com.itjfw.infra.purchaseorderdetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itjfw.common.util.UtilSearch;

@Controller
public class PurchaseOrderDetailController {

	@Autowired
	PurchaseOrderDetailService purchaseOrderDetailService;
	
	// 리스트 페이지
	@RequestMapping(value = "/purchaseOrderDetailXdmList")
	public String purchaseOrderDetailXdmList(@ModelAttribute("vo") PurchaseOrderDetailVo PurchaseOrderDetailVo, Model model) throws Exception{
		
		UtilSearch.setSearch(PurchaseOrderDetailVo);
		
		PurchaseOrderDetailVo.setParamsPaging(purchaseOrderDetailService.selectOneCount(PurchaseOrderDetailVo));
		
		if(PurchaseOrderDetailVo.getTotalRows() > 0) {
			
			model.addAttribute("list", purchaseOrderDetailService.selectListWithPaging(PurchaseOrderDetailVo));
		}
	
		return  "xdm/purchaseorderdetail/purchaseOrderDetailXdmList";
	
	}
	
	// multiuelete
	@RequestMapping(value = "/purchaseOrderDetailMultiUele")
	public String purchaseOrderDetailMultiUele(PurchaseOrderDetailDto dto) throws Exception{
		String[] checkboxSeqArray = dto.getCheckboxSeqArray();
		for(String checkboxSeq : checkboxSeqArray) {
			dto.setPurchaseOrderDetailSeq(checkboxSeq);
			purchaseOrderDetailService.updateDelete(dto);
		}
		
		return "redirect:/purchaseOrderDetailXdmList";
	}
	
	// uelete
	@RequestMapping(value = "/purchaseOrderDetailUelete")
	public String purchaseOrderDetailUelete(PurchaseOrderDetailDto PurchaseOrderDetailDto) throws Exception{
		
		purchaseOrderDetailService.updateDelete(PurchaseOrderDetailDto);
	
		return "redirect:/purchaseOrderDetailXdmList";
	
	}
	
	// delete
	@RequestMapping(value = "/purchaseOrderDetailXdmDelete")
	public String purchaseOrderDetailXdmDelete(PurchaseOrderDetailDto PurchaseOrderDetailDto) throws Exception{
		
		purchaseOrderDetailService.delete(PurchaseOrderDetailDto);
	
		return "redirect:/purchaseOrderDetailXdmList";
	
	}
	
	// insert
	@RequestMapping(value = "/purchaseOrderDetailXdmInsert")
	public String purchaseOrderDetailXdmInsert(PurchaseOrderDetailDto PurchaseOrderDetailDto) throws Exception{
		
		purchaseOrderDetailService.insert(PurchaseOrderDetailDto);
	
		return "redirect:/purchaseOrderDetailXdmList";
	
	}
	
	
	// update
	@RequestMapping(value = "/purchaseOrderDetailXdmUpdate")
	public String purchaseOrderDetailXdmUpdate(PurchaseOrderDetailDto PurchaseOrderDetailDto) throws Exception{
		
		purchaseOrderDetailService.update(PurchaseOrderDetailDto);
	
		return "redirect:/purchaseOrderDetailXdmList";
	
	}
	
	// 상세 정보 페이지
	@RequestMapping(value = "/purchaseOrderDetailXdmView")
	public String purchaseOrderDetailXdmView(PurchaseOrderDetailDto PurchaseOrderDetailDto, Model model) throws Exception{
		
		model.addAttribute("item", purchaseOrderDetailService.selectOne(PurchaseOrderDetailDto));
		
		return  "xdm/purchaseorderdetail/purchaseOrderDetailXdmView";
	
	}
	
	// 등록 페이지
	@RequestMapping(value = "/purchaseOrderDetailXdmInsertForm")
	public String purchaseOrderDetailXdmInsertForm() throws Exception{
		
		
		return  "xdm/purchaseorderdetail/purchaseOrderDetailXdmInsertForm";
	
	}
	
	// 수정 페이지
	@RequestMapping(value = "/purchaseOrderDetailXdmUpdateForm")
	public String purchaseOrderDetailXdmUpdateForm(PurchaseOrderDetailDto PurchaseOrderDetailDto, Model model) throws Exception{
		
		model.addAttribute("item", purchaseOrderDetailService.selectOne(PurchaseOrderDetailDto));
		
		return  "xdm/purchaseorderdetail/purchaseOrderDetailXdmUpdateForm";
	
	}
	
}
