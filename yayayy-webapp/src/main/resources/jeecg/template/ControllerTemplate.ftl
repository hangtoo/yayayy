package ${bussPackage}.${entityPackage}.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunfintech.base.web.BaseAction;
import ${bussPackage}.${entityPackage}.entity.${className};
import ${bussPackage}.${entityPackage}.page.${className}Page;
import ${bussPackage}.${entityPackage}.service.I${className}Service;

/**
 *
 * <br>
 * <b>功能：</b>${className}Controller<br>
 * <b>作者：</b>${user}<br>
 * <b>日期：</b> ${time} <br>
 * <b>版权所有： 2017，${organization}<br>
 */
@Controller
@RequestMapping("/${lowerName}")
public class ${className}Controller extends BaseAction{

	private final static Logger log= Logger.getLogger(${className}Controller.class);

	@Autowired
	private I${className}Service ${lowerName}Service;

	
	/**
	 * 数据列表
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/dataList")
	public Map<String, Object>  datalist(@RequestBody ${className}Page page,HttpServletResponse response) throws Exception{
		Map<String,Object>  context = getRootMap();
		int count = ${lowerName}Service.queryByCount(page);
		List<${className}> dataList = new ArrayList<>();
		if(count>0){
			dataList = ${lowerName}Service.queryByList(page);
		}
		context.put("list", dataList);
		context.put("totalRow", count);
		return context;
	}

	/**
	 * 添加或修改数据
	 * @param url
	 * @param classifyId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(@RequestBody ${className} entity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(entity==null) {
			return fail( "参数错误!");
		}
		
		if(entity.getId()!=null){
			${lowerName}Service.updateBySelective(entity);
		}else{
			${lowerName}Service.add(entity);
		}
		return success("保存成功~");
	}

	@ResponseBody
	@RequestMapping("/getId")
	public ${className} getId(Long id,HttpServletResponse response) throws Exception{
		${className} entity  = ${lowerName}Service.queryById(id);
		return entity;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public Map<String,Object> delete(@RequestBody ${className} entity,HttpServletRequest request,HttpServletResponse response) throws Exception{
		if(entity==null){
			return fail("请选择删除记录");
		}
		${lowerName}Service.delete(entity.getId());
		return success("删除成功");
	}

}
