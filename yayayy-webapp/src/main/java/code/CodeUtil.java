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
		//createMsgRecode();
		//createtCustomerFrom();
		//createtInvestInfo();
		//createtCustomer();
		//createtCustomerBankInfo();
		//createtCustomerInvest();
		//createtImage();
		//createtTransferIn();
		//createTransferOut();
		//createInvestRegionInfo();
		//createTbank();
		//createTchannel();
		//createBankFacade();
		//createCustomerBankRisk();
		//createTPromotion();
		//createCity();
		//createProvince();
		//createBankCode();
		//createTPromotionCustomer();
		//createTApiLog();
		//createTArticle();
		//createTCustomerFrom();
		createTArticleAdv();
	}
	
	private static void createTArticleAdv() {
		String tableName = "t_article_adv";
		String codeName = "头条广告表";
		String entityPackage = "articleadv";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	private static void createTBanner() {
		String tableName = "t_banner";
		String codeName = "banner表";
		String entityPackage = "banner";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	private static void createTCustomerFrom() {
		String tableName = "t_customer_from";
		String codeName = "客户来源表";
		String entityPackage = "customerfrom";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	private static void createTArticle() {
		String tableName = "t_article";
		String codeName = "理财头条表";
		String entityPackage = "article";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	private static void createTApiLog() {
		String tableName = "t_api_log";
		String codeName = "接口日志表";
		String entityPackage = "apilog";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	private static void createTPromotionCustomer() {
		String tableName = "t_promotion_customer";
		String codeName = "活动参与表";
		String entityPackage = "promotioncustomer";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	private static void createBankCode() {
		String tableName = "t_bank_code";
		String codeName = "银行所在网点配置表";
		String entityPackage = "bankcode";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	
	private static void createProvince() {
		String tableName = "t_province";
		String codeName = "省份配置表";
		String entityPackage = "province";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	
	private static void createCity() {
		String tableName = "t_city";
		String codeName = "城市配置表";
		String entityPackage = "city";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	private static void createTPromotion() {
		String tableName = "t_promotion";
		String codeName = "运营活动表";
		String entityPackage = "promotion";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, true);
	}
	
	private static void createCustomerBankRisk() {
		String tableName = "t_customer_bank_risk";
		String codeName = "用户银行风险表";
		String entityPackage = "customerbankrisk";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}

	private static void createBankFacade() {
		String tableName = "t_bank_facade";
		String codeName = "银行接口";
		String entityPackage = "bankfacade";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}
	
	private static void createTchannel() {
		String tableName = "t_channel";
		String codeName = "渠道编号关系表";
		String entityPackage = "channel";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);

	}

	private static void createTbank() {
		String tableName = "t_bank";
		String codeName = "银行编号关系表";
		String entityPackage = "bank";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);

	}

	private static void createInvestRegionInfo() {
		String tableName = "t_invest_region_info";
		String codeName = "投资产品专区信息表";
		String entityPackage = "investregioninfo";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createTransferOut() {
		String tableName = "t_transfer_out";
		String codeName = "提现情况表";
		String entityPackage = "transferout";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createtTransferIn() {
		String tableName = "t_transfer_in";
		String codeName = "充值情况表";
		String entityPackage = "transferin";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createtImage() {
		String tableName = "t_image";
		String codeName = "影像目录表";
		String entityPackage = "image";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createtCustomerInvest() {
		String tableName = "t_customer_invest";
		String codeName = "客户投资表";
		String entityPackage = "customerinvest";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}
	
	private static void createtCustomerBankInfo() {
		String tableName = "t_customer_bank_info";
		String codeName = "客户银行卡信息表";
		String entityPackage = "customerbankinfo";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createtCustomer() {
		String tableName = "t_customer";
		String codeName = "客户基本信息表";
		String entityPackage = "customer";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}
	
	private static void createtInvestInfo() {
		String tableName = "t_invest_info";
		String codeName = "投资产品信息表";
		String entityPackage = "investinfo";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, true, false);
	}

	private static void createtCustomerFrom() {
		String tableName = "t_customer_from";
		String codeName = "客户来源表";
		String entityPackage = "customerfrom";// 实体包
		String keyType = FtlDef.KEY_TYPE_01;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}
	
	private static void createMsgRecode() {
		String tableName = "t_sms_recode";
		String codeName = "短信信息表(原短信记录表)";
		String entityPackage = "sms";// 实体包
		String keyType = FtlDef.KEY_TYPE_02;// 主键生成方式 01:UUID 02:自增
		CodeGenerateFactory.codeGenerate(tableName, codeName, entityPackage, keyType, false, false);
	}

}
