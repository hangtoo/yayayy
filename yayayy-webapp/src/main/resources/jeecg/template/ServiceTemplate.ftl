package ${bussPackage}.${entityPackage}.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import ${bussPackage}.${entityPackage}.entity.${className};
import ${bussPackage}.${entityPackage}.dao.${className}Dao;
import com.hangtoo.base.service.BaseService;

/**
 *
 * <br>
 * <b>功能：</b>${className}Service<br>
 * <b>作者：</b>${user}<br>
 * <b>日期：</b> ${time} <br>
 * <b>版权所有： 2017，${organization}<br>
 */
@Component("$!{lowerName}Service")
@Service
@Transactional
public class ${className}Service extends BaseService<${className}> implements I${className}Service{
	private final static Logger log= Logger.getLogger(${className}Service.class);

	@Autowired
    private ${className}Dao dao;
		
	public ${className}Dao getDao() {
		return dao;
	}

}
