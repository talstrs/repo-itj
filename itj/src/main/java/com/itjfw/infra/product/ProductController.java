package com.itjfw.infra.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itjfw.common.util.UtilSearch;

@Controller
public class ProductController {

	@Autowired
	ProductService productService;
	
	// 리스트 페이지
	@RequestMapping(value = "/productXdmList")
	public String productXdmList(@ModelAttribute("vo") ProductVo productVo, Model model) throws Exception{
		
		UtilSearch.setSearch(productVo);
		
		productVo.setParamsPaging(productService.selectOneCount(productVo));
		
		if(productVo.getTotalRows() > 0) {
			
			model.addAttribute("list", productService.selectList(productVo));
		}
	
		return  "xdm/product/productXdmList";
	
	}
	
	// uelete
	@RequestMapping(value = "/productXdmUelete")
	public String productXdmUelete(ProductDto productDto) throws Exception{
		
		productService.updateDelete(productDto);
	
		return  "xdm/product/productXdmList";
	
	}
	
	// delete
	@RequestMapping(value = "/productXdmDelete")
	public String productXdmDelete(ProductDto productDto) throws Exception{
		
		productService.delete(productDto);
	
		return  "xdm/product/productXdmList";
	
	}
	
	// insert
	@RequestMapping(value = "/productXdmInsert")
	public String productXdmInsert(ProductDto productDto) throws Exception{
		
		productService.insert(productDto);
	
		return  "xdm/product/productXdmList";
	
	}
	
	
	// update
	@RequestMapping(value = "/productXdmUpdate")
	public String productXdmUpdate(ProductDto productDto) throws Exception{
		
		productService.update(productDto);
	
		return  "xdm/product/productXdmList";
	
	}
	
	// 상품 상세 정보
	@RequestMapping(value = "/productXdmView")
	public String productXdmView(ProductDto productDto, Model model) throws Exception{
		
		model.addAttribute("item", productService.selectOne(productDto));
		
		return  "xdm/product/productXdmView";
	
	}
	
	// 상품 등록
	@RequestMapping(value = "/productXdmInsertForm")
	public String productXdmInsertForm() throws Exception{
		
		
		return  "xdm/product/productXdmInsertForm";
	
	}
	
	// 상품 수정
	@RequestMapping(value = "/productXdmUpdateForm")
	public String productXdmUpdateForm(ProductDto productDto, Model model) throws Exception{
		
		model.addAttribute("item", productService.selectOne(productDto));
		
		return  "xdm/product/productXdmUpdateForm";
	
	}
	
}