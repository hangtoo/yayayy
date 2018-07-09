package ${bussPackage}.${entityPackage}.service;


import com.sunfintech.base.service.IBaseService;
import ${bussPackage}.${entityPackage}.entity.${className};

/**
 *
 * <br>
 * <b>功能：</b>I${className}Service<br>
 * <b>作者：</b>${user}<br>
 * <b>日期：</b> ${time} <br>
 * <b>版权所有： 2017，${organization}<br>
 */
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = I${className}Service.class)
public interface I${className}Service extends IBaseService<${className}>{
}
