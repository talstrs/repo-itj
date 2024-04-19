package com.itjfw.infra.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itjfw.common.base.BaseController;
import com.itjfw.common.util.UtilSearch;
import com.itjfw.infra.code.CodeService;
import com.itjfw.infra.codegroup.CodeGroupDto;
import com.itjfw.infra.codegroup.CodeGroupService;
import com.itjfw.infra.codegroup.CodeGroupVo;

@Controller
public class PoController extends BaseController {
	private String poUrl = "xdm/po/";

	
	@Autowired
	CodeService codeService;
	@Autowired
	PoService poService;
	
	@RequestMapping(value = "/poXdmList")
	public String poXdmList(@ModelAttribute("vo") PoVo vo, Model model) throws Exception {
		UtilSearch.setSearch(vo);

		vo.setParamsPaging(poService.selectCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", poService.selectListWithPaging(vo));
		}

		return poUrl + "poXdmList";
	}

	@RequestMapping(value = "/poView")
	public String poView(PoDto dto, Model model) throws Exception {

		model.addAttribute("item", poService.selectOne(dto));

		return poUrl + "poView";
	}

	@RequestMapping(value = "/poUpdateForm")
	public String poUpdateForm(Model model, PoDto dto) throws Exception {

		model.addAttribute("item", poService.selectOne(dto));
		return poUrl + "poUpdateForm";
	}

	@RequestMapping(value = "/poInsertForm")
	public String poInsertForm(Model model) throws Exception {
		model.addAttribute("poGroupList", poService.selectListWithoutPaging());
		return poUrl + "poInsertForm";
	}

	@RequestMapping(value = "/poInsert")
	public String poInsert(PoDto dto, Model model) throws Exception {
		System.out.println("==================================================");
		poService.insert(dto);
		return "redirect:/poXdmList";
	}

	@RequestMapping(value = "/poUpdt")
	public String poUpdt(PoDto dto, Model model) throws Exception {

		poService.update(dto);
		return "redirect:/poXdmList";
	}

	@RequestMapping(value = "/poUelete")
	public String poUelete(PoDto dto, Model model) throws Exception {

		poService.uelete(dto);
		return "redirect:/poXdmList";
	}

	@RequestMapping(value = "/poDelete")
	public String poDelete(PoDto dto, Model model) throws Exception {

		poService.delete(dto);
		return "redirect:/poXdmList";
	}


}