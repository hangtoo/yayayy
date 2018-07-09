package codeGenerate.factory;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;

import codeGenerate.CommonPageParser;
import codeGenerate.CreateBean;
import codeGenerate.def.CodeResourceUtil;

public class CodeGenerateFactory {
	private final static Logger log= Logger.getLogger(CodeGenerateFactory.class);
	private static String url = CodeResourceUtil.URL;
	private static String username = CodeResourceUtil.USERNAME;
	private static String passWord = CodeResourceUtil.PASSWORD;

	private static String buss_package = CodeResourceUtil.bussiPackage;
	private static String projectPath = getProjectPath();

	private static String SXG="/";
	/**
	 * @param tableName：表名
	 * @param codeName：注释
	 * @param entityPackage：实体包
	 * @param keyType：主键生成方式
	 *            01:UUID 02:自增
	 * @param isGenerateJsp:是否生产jsp页面和js文件
	 * @param isJspSimple:是否产生edit.jsp(编辑)和look.jsp(查看)
	 */
	public static void codeGenerate(String tableName, String codeName, String entityPackage, String keyType,
			boolean isGenerateVue, boolean isVueSimple) {
		CreateBean createBean = new CreateBean();
		createBean.setMysqlInfo(url, username, passWord);

		String className = createBean.getTablesNameToClassName(tableName);
		String lowerName = className.substring(0, 1).toLowerCase() + className.substring(1, className.length());

		String srcPath = projectPath + CodeResourceUtil.source_root_package + SXG;
		String resourcePath = projectPath + CodeResourceUtil.resource_root_package + SXG;

		String pckPath = srcPath + CodeResourceUtil.bussiPackageUrl + SXG;
		String pckResourcePath = resourcePath + CodeResourceUtil.bussiPackageUrl + SXG;

//		String webPath = projectPath + CodeResourceUtil.web_root_package + SXG+"WEB-INF"+SXG+"views"+SXG
//				+ CodeResourceUtil.bussiPackageUrl + SXG;
//		webPath = webPath + entityPackage + SXG;
		
		String webPath = projectPath + "www/src/views/page/";

		String modelPath = entityPackage + SXG + "page"+SXG + className + "Page.java";
		String beanPath = entityPackage + SXG + "entity"+SXG + className + ".java";
		String mapperPath = entityPackage + SXG + "dao"+SXG + className + "Dao.java";
		String servicePath = entityPackage + SXG + "service"+SXG + className + "Service.java";
		String serviceInterfacePath = entityPackage + SXG + "service"+SXG+"I" + className + "Service.java";
		String controllerPath = entityPackage + SXG + "controller"+SXG + className + "Controller.java";
		String sqlMapperPath = entityPackage + SXG + "mapper"+SXG + className + "Mapper.xml";

		String jspPath = "list.jsp";
		String editJspPath = "edit.jsp";
		String lookJspPath = "look.jsp";

		VelocityContext context = new VelocityContext();
		context.put("className", className);
		context.put("lowerName", lowerName);
		context.put("codeName", codeName);
		context.put("tableName", tableName);
		context.put("bussPackage", buss_package);
		context.put("bussiPackageUrl", buss_package.replace(".", "/"));
		context.put("entityPackage", entityPackage);
		context.put("keyType", keyType);
		context.put("user", System.getProperty("user.name"));
		context.put("time", new Date());
		context.put("organization", CodeResourceUtil.getOrganization());

		try {
			context.put("feilds", createBean.getBeanFeilds(tableName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Map sqlMap = createBean.getAutoCreateSql(tableName);
			context.put("columnDatas", createBean.getColumnDatas(tableName));
			context.put("SQL", sqlMap);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		CommonPageParser.WriterPage(context, "EntityTemplate.ftl", pckPath, beanPath);
		CommonPageParser.WriterPage(context, "PageTemplate.ftl", pckPath, modelPath);
		CommonPageParser.WriterPage(context, "DaoTemplate.ftl", pckPath, mapperPath);
		CommonPageParser.WriterPage(context, "ServiceTemplate.ftl", pckPath, servicePath);
		CommonPageParser.WriterPage(context, "IServiceTemplate.ftl", pckPath, serviceInterfacePath);
		CommonPageParser.WriterPage(context, "MapperTemplate.xml", pckResourcePath, sqlMapperPath);
		CommonPageParser.WriterPage(context, "ControllerTemplate.ftl", pckPath, controllerPath);
		boolean isGenerateJsp = false;
		boolean isJspSimple = false;
		if (isGenerateJsp) {
			if (isJspSimple) {
				CommonPageParser.WriterPage(context, "simpleJspTemplate.ftl", webPath, jspPath);
			} else {
				CommonPageParser.WriterPage(context, "jspTemplate.ftl", webPath, jspPath);
				CommonPageParser.WriterPage(context, "editJspTemplate.ftl", webPath, editJspPath);
				CommonPageParser.WriterPage(context, "lookJspTemplate.ftl", webPath, lookJspPath);
			}
		}

		if (isGenerateVue) {
			CommonPageParser.WriterPage(context, "simpleVueTemplate.ftl", webPath, className+".vue");
				//CommonPageParser.WriterPage(context, "vueTemplate.ftl", webPath, jspPath);
				//CommonPageParser.WriterPage(context, "editVueTemplate.ftl", webPath, editJspPath);
				//CommonPageParser.WriterPage(context, "lookVueTemplate.ftl", webPath, lookJspPath);
		}

		log.info("----------------------------代码生成完毕---------------------------");
	}

	public static String getProjectPath() {
		String path = System.getProperty("user.dir").replace("\\", "/") + "/";
		return path;
	}

}