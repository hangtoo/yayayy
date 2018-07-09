package code;

import codeGenerate.def.FtlDef;
import codeGenerate.factory.CodeGenerateFactory;

/**
 * @author Jeecg
 *
 */
public class CodeUtil {

	public static void main(String[] args) {
		/** 此处修改成你的 表名 和 中文注释 ***/
		createBankFacade();
	}
	
	private static void createBankFacade() {
		String tableName = "t_bank_facade";
		String codeName = "银行接口";
		String entityPackage = "bankfacade";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}
}
