/**********************************************************
 Project:	   AlertMgmt	
 File:         HibernateUtil.java
 Created:      Jul 22, 2014
 Last Changed: Jul 22, 2014
 Author:       CDAC

This code is copyright (c) 2014 C-DAC Noida.

 ***********************************************************/
package net.user.config;

/*
import mms.masters.model.GbltCountryMstImsc;
import mms.masters.model.GbltDepartmentMst;
import mms.masters.model.GbltDiagnosisMst;
import mms.masters.model.GbltDistrictMstImsc;
import mms.masters.model.GbltGenderMst;
import mms.masters.model.GbltMasterReportConfig;
import mms.masters.model.GbltMenuMst;
import mms.masters.model.GbltMetatableColumnMst;
import mms.masters.model.GbltMetatableTypeMst;
import mms.masters.model.GbltRoleMenuMst;
import mms.masters.model.GbltRoleMst;
import mms.masters.model.GbltRoleSeatTableDtl;
import mms.masters.model.GbltSeatMst;
import mms.masters.model.GbltSeatRoleMst;
import mms.masters.model.GbltStateMstImsc;
import mms.masters.model.GbltTaskDtl;
import mms.masters.model.GbltTaskParamMst;
import mms.masters.model.GbltTaskSystemMst;
import mms.masters.model.GbltTaskUserMst;
import mms.masters.model.GbltUnitMst;
import mms.masters.model.GbltUserMst;
import mms.masters.model.HsttAnalysisParamDtl;
import mms.masters.model.HsttApplicationErrorLogDtl;
import mms.masters.model.HsttApprovingAuthMst;
import mms.masters.model.HsttAuthorityHierMst;
import mms.masters.model.HsttBankBranchMst;
import mms.masters.model.HsttBankMst;
import mms.masters.model.HsttBlacklistedSuppDtl;
import mms.masters.model.HsttCommitteeTypeMst;
import mms.masters.model.HsttComponentMst;
import mms.masters.model.HsttConfigDtl;
import mms.masters.model.HsttCurrencyMst;
import mms.masters.model.HsttDeliverymodeMst;
import mms.masters.model.HsttDeptDiagnosisMst;
import mms.masters.model.HsttDeptEmpMst;
import mms.masters.model.HsttDistributorMst;
import mms.masters.model.HsttDrugContradictionMst;
import mms.masters.model.HsttDrugMst;
import mms.masters.model.HsttDrugbrandMst;
import mms.masters.model.HsttFundingSourceMst;
import mms.masters.model.HsttGroupMst;
import mms.masters.model.HsttItemtypeMst;
import mms.masters.model.HsttPeneltyConfigDtl;
import mms.masters.model.HsttPotypeComponentMst;
import mms.masters.model.HsttProgrammeItemMst;
import mms.masters.model.HsttProgrammeMst;
import mms.masters.model.HsttQcLabMst;
import mms.masters.model.HsttRackMst;
import mms.masters.model.HsttSourceMst;
import mms.masters.model.HsttStoreCategoryMst;
import mms.masters.model.HsttStoreDeptMst;
import mms.masters.model.HsttStoreEmpMst;
import mms.masters.model.HsttStoreHistDtl;
import mms.masters.model.HsttStoreMst;
import mms.masters.model.HsttStoreProgrammeMst;
import mms.masters.model.HsttStoreReqtypeMst;
import mms.masters.model.HsttStoreThirdpartyMst;
import mms.masters.model.HsttStorehierarchyMst;
import mms.masters.model.HsttStoreitemMst;
import mms.masters.model.HsttStoretypeItemMst;
import mms.masters.model.HsttSubcategoryTypeMst;
import mms.masters.model.HsttSubgroupMst;
import mms.masters.model.HsttSupplierEscalationMst;
import mms.masters.model.HsttSupplierGradeMst;
import mms.masters.model.HsttSupplierGs1Mst;
import mms.masters.model.HsttSupplierMst;
import mms.masters.model.HsttSupplierTypeMst;
import mms.masters.model.PistDesignationMst;
import mms.masters.model.PistEmpPersonnelDtl;
import mms.masters.model.SbltDwhMstPk;
import mms.masters.model.SsttBudgetClassMst;
import mms.masters.model.SsttBuildingMst;
import mms.masters.model.SsttCertificateMst;
import mms.masters.model.SsttDrugClassificationMst;
import mms.masters.model.SsttDrugParameterMst;
import mms.masters.model.SsttDrugStandardMst;
import mms.masters.model.SsttDwhTypeMst;
import mms.masters.model.SsttFloorMst;
import mms.masters.model.SsttIndenttypeMst;
import mms.masters.model.SsttIssuetypeMst;
import mms.masters.model.SsttItemApprovedTypeMst;
import mms.masters.model.SsttItemCategoryMst;
import mms.masters.model.SsttLabTypeMst;
import mms.masters.model.SsttPeriodMst;
import mms.masters.model.SsttPoPrefixMst;
import mms.masters.model.SsttProgrammeTypeMst;
import mms.masters.model.SsttPurchasemodeMst;
import mms.masters.model.SsttRateContracttypeMst;
import mms.masters.model.SsttRoomMst;
import mms.masters.model.SsttStockStatusMst;
import mms.masters.model.SsttSupplierLevelMst;
import mms.reports.model.GbltUserLog;
import mms.transactions.model.HsttAckItemDtl;
import mms.transactions.model.HsttAcknowledgeDtl;
import mms.transactions.model.HsttApprovalDtl;
import mms.transactions.model.HsttApprovalItemDtl;
import mms.transactions.model.HsttApprovalItemLogDtl;
import mms.transactions.model.HsttApprovalLogDtl;
import mms.transactions.model.HsttBgDtl;
import mms.transactions.model.HsttBlockeditemDtl;
import mms.transactions.model.HsttBreakageDtl;
import mms.transactions.model.HsttBreakageItemDtl;
import mms.transactions.model.HsttBudgetAllocationDtl;
import mms.transactions.model.HsttBudgetDtl;
import mms.transactions.model.HsttBudgetLogDtl;
import mms.transactions.model.HsttChallanDtl;
import mms.transactions.model.HsttChallanItemDtl;
import mms.transactions.model.HsttChallanProgItemDtl;
import mms.transactions.model.HsttChallanVerifieditemDtl;
import mms.transactions.model.HsttCircularDtl;
import mms.transactions.model.HsttCondemnRetReqDtl;
import mms.transactions.model.HsttDrugAnnualValueDtl;
import mms.transactions.model.HsttDrugCurrstockDtl;
import mms.transactions.model.HsttDrugParameterDtl;
import mms.transactions.model.HsttIndentDtl;
import mms.transactions.model.HsttIndentItemDtl;
import mms.transactions.model.HsttIndentItemLogDtl;
import mms.transactions.model.HsttIndentLogDtl;
import mms.transactions.model.HsttIssueDtl;
import mms.transactions.model.HsttIssueItemDtl;
import mms.transactions.model.HsttIssueItemLogDtl;
import mms.transactions.model.HsttIssueLogDtl;
import mms.transactions.model.HsttPatempIssueDtl;
import mms.transactions.model.HsttPatientCountDayDss;
import mms.transactions.model.HsttPatientDtl;
import mms.transactions.model.HsttPoComponentDtl;
import mms.transactions.model.HsttPoDtl;
import mms.transactions.model.HsttPoItemDtl;
import mms.transactions.model.HsttPoItemLogDtl;
import mms.transactions.model.HsttPoLogDtl;
import mms.transactions.model.HsttPoProgrammeDtl;
import mms.transactions.model.HsttPoProgrammeItemDtl;
import mms.transactions.model.HsttPoProgrammeItemLogDtl;
import mms.transactions.model.HsttPoScheduleDtl;
import mms.transactions.model.HsttPoScheduleLogDtl;
import mms.transactions.model.HsttPrgFsMappingMst;
import mms.transactions.model.HsttPurIndentItemDdwDtl;
import mms.transactions.model.HsttQcRejectedDtl;
import mms.transactions.model.HsttRateDiscrepanacyDtl;
import mms.transactions.model.HsttRatecontractItemDtl;
import mms.transactions.model.HsttRatecontractItemLogDtl;
import mms.transactions.model.HsttReplacemntOrderDtl;
import mms.transactions.model.HsttStockDssDtl;
import mms.transactions.model.HsttSupDeliveryItemLogDtl;
import mms.transactions.model.HsttSuppDeliveryDtl;
import mms.transactions.model.HsttSuppDeliveryItemDtl;
import mms.transactions.model.HsttSuppDeliveryLogDtl;
import mms.transactions.model.HsttSupplierBatchDtl;
import mms.transactions.model.HsttTenderDtl;
import mms.transactions.model.HsttThirdpartyIssueDtl;
import mms.transactions.model.HsttThirdpartyIssueItemDtl;
import mms.transactions.model.HsttTransExcessReqLogDtl;
import mms.transactions.model.HsttTransShortReqLogDtl;
import mms.transactions.model.HsttTransactionPerformDss;
import mms.transactions.model.HsttTransferDtl;
import mms.transactions.model.HsttTransferExcessReqDtl;
import mms.transactions.model.HsttTransferItemDtl;
import mms.transactions.model.HsttTransferOrderDtl;
import mms.transactions.model.HsttTransferShortReqDtl;
import mms.transactions.model.HsttTssdrugOpTransDtl;
import mms.transactions.model.SbltDwhTransPk;
import mms.transactions.model.SsttAcknowledgeDtl;
import mms.transactions.model.SsttApprovalreqDtl;
import mms.transactions.model.SsttBudgetConsumptionDtl;
import mms.transactions.model.SsttIndentDtl;
import mms.transactions.model.SsttIssueDtl;
import mms.transactions.model.SsttPatempIssueDtl;
import mms.transactions.model.SsttPatempIssueItemDtl;
import mms.transactions.model.SsttPoDtl;*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class HibernateUtil.
 */
public class HibernateUtil {

	/** The Constant sessionFactory. */
	//private static final SessionFactory sessionFactory;

	/*
	static
	{
		try {
			Configuration config = getConfiguration();

			sessionFactory = config.buildSessionFactory();
		} catch (Throwable ex) {

			System.err.println("Initial SessionFactory creation failed." + ex);

			ex.printStackTrace();

			throw new ExceptionInInitializerError(ex);

		}
	}*/

	/**
	 * Open session.
	 * 
	 * @return the session
	 * @throws Exception
	 */
	/*public static Session openSession() throws Exception {

		Session session = null;

		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {

			throw new Exception("Unable to Connect with Database");

		}
		return session;
	}*/

	/**
	 * Gets the configuration.
	 * 
	 * @return the configuration
	 * @throws Exception
	 */
	/*
	private static Configuration getConfiguration() throws Exception {
		Configuration cfg = new Configuration();

		// holds all primary key values
		cfg.addAnnotatedClass(SbltDwhMstPk.class);
		cfg.addAnnotatedClass(SbltDwhTransPk.class);

		cfg.addAnnotatedClass(HsttDeliverymodeMst.class);
		cfg.addAnnotatedClass(GbltMasterReportConfig.class);
		cfg.addAnnotatedClass(HsttApplicationErrorLogDtl.class);

		cfg.addAnnotatedClass(HsttBankMst.class);
		cfg.addAnnotatedClass(HsttBankBranchMst.class);
		cfg.addAnnotatedClass(HsttSupplierTypeMst.class);
		cfg.addAnnotatedClass(HsttSupplierGradeMst.class);
		cfg.addAnnotatedClass(GbltDistrictMstImsc.class);
		cfg.addAnnotatedClass(HsttStoreitemMst.class);
		cfg.addAnnotatedClass(HsttDrugbrandMst.class);
		cfg.addAnnotatedClass(HsttStoreMst.class);
		cfg.addAnnotatedClass(GbltRoleSeatTableDtl.class);
		cfg.addAnnotatedClass(GbltUserMst.class);
		cfg.addAnnotatedClass(HsttStoreCategoryMst.class);
		cfg.addAnnotatedClass(SsttItemCategoryMst.class);
		cfg.addAnnotatedClass(HsttPotypeComponentMst.class);
		cfg.addAnnotatedClass(SsttIndenttypeMst.class);
		cfg.addAnnotatedClass(HsttComponentMst.class);
		cfg.addAnnotatedClass(HsttGroupMst.class);
		cfg.addAnnotatedClass(GbltMetatableColumnMst.class);
		cfg.addAnnotatedClass(GbltRoleSeatTableDtl.class);
		cfg.addAnnotatedClass(HsttStoreMst.class);
		cfg.addAnnotatedClass(GbltUserMst.class);
		cfg.addAnnotatedClass(HsttRackMst.class);
		cfg.addAnnotatedClass(SsttBuildingMst.class);
		cfg.addAnnotatedClass(SsttFloorMst.class);
		cfg.addAnnotatedClass(SsttRoomMst.class);
		cfg.addAnnotatedClass(HsttQcLabMst.class);
		cfg.addAnnotatedClass(SsttLabTypeMst.class);
		cfg.addAnnotatedClass(GbltStateMstImsc.class);
		cfg.addAnnotatedClass(SsttDwhTypeMst.class);
		cfg.addAnnotatedClass(GbltMetatableTypeMst.class);
		cfg.addAnnotatedClass(PistEmpPersonnelDtl.class);
		cfg.addAnnotatedClass(HsttStoreProgrammeMst.class);
		cfg.addAnnotatedClass(HsttProgrammeMst.class);
		cfg.addAnnotatedClass(PistDesignationMst.class);
		cfg.addAnnotatedClass(HsttStoreEmpMst.class);
		cfg.addAnnotatedClass(GbltGenderMst.class);
		cfg.addAnnotatedClass(HsttStoreDeptMst.class);
		cfg.addAnnotatedClass(HsttSupplierGs1Mst.class);
		cfg.addAnnotatedClass(GbltUnitMst.class);
		cfg.addAnnotatedClass(GbltMenuMst.class);
		cfg.addAnnotatedClass(GbltRoleMst.class);
		cfg.addAnnotatedClass(GbltRoleMenuMst.class);
		cfg.addAnnotatedClass(GbltSeatMst.class);
		cfg.addAnnotatedClass(GbltSeatRoleMst.class);
		cfg.addAnnotatedClass(GbltTaskDtl.class);
		cfg.addAnnotatedClass(GbltTaskParamMst.class);
		cfg.addAnnotatedClass(GbltTaskSystemMst.class);
		cfg.addAnnotatedClass(GbltTaskUserMst.class);
		cfg.addAnnotatedClass(HsttProgrammeItemMst.class);
		cfg.addAnnotatedClass(HsttDrugMst.class);
		cfg.addAnnotatedClass(HsttItemtypeMst.class);
		cfg.addAnnotatedClass(SsttDrugClassificationMst.class);
		cfg.addAnnotatedClass(SsttCertificateMst.class);
		cfg.addAnnotatedClass(SsttDrugStandardMst.class);
		cfg.addAnnotatedClass(SsttIssuetypeMst.class);
		cfg.addAnnotatedClass(SsttItemApprovedTypeMst.class);
		cfg.addAnnotatedClass(HsttSubcategoryTypeMst.class);
		cfg.addAnnotatedClass(HsttStoreThirdpartyMst.class);
		cfg.addAnnotatedClass(HsttStoretypeItemMst.class);
		cfg.addAnnotatedClass(HsttPotypeComponentMst.class);
		cfg.addAnnotatedClass(SsttBudgetClassMst.class);
		cfg.addAnnotatedClass(HsttFundingSourceMst.class);
		cfg.addAnnotatedClass(HsttSourceMst.class);
		cfg.addAnnotatedClass(HsttDistributorMst.class);
		cfg.addAnnotatedClass(GbltUserLog.class);

		cfg.addAnnotatedClass(HsttStoreHistDtl.class);
		cfg.addAnnotatedClass(HsttAnalysisParamDtl.class);
		cfg.addAnnotatedClass(HsttBgDtl.class);
		cfg.addAnnotatedClass(HsttRatecontractItemDtl.class);
		cfg.addAnnotatedClass(HsttTenderDtl.class);
		cfg.addAnnotatedClass(HsttTransactionPerformDss.class);
		cfg.addAnnotatedClass(SsttRateContracttypeMst.class);
		cfg.addAnnotatedClass(SsttSupplierLevelMst.class);

		cfg.addAnnotatedClass(HsttCircularDtl.class);
		cfg.addAnnotatedClass(HsttDrugCurrstockDtl.class);
		cfg.addAnnotatedClass(SsttStockStatusMst.class);
		cfg.addAnnotatedClass(HsttApprovingAuthMst.class);
		
		cfg.addAnnotatedClass(HsttRateDiscrepanacyDtl.class);
		cfg.addAnnotatedClass(HsttTssdrugOpTransDtl.class);
		cfg.addAnnotatedClass(HsttConfigDtl.class);
		cfg.addAnnotatedClass(SsttPeriodMst.class);
		cfg.addAnnotatedClass(HsttCommitteeTypeMst.class);
		cfg.addAnnotatedClass(GbltCountryMstImsc.class);
		cfg.addAnnotatedClass(HsttSupplierEscalationMst.class);
		cfg.addAnnotatedClass(HsttBlacklistedSuppDtl.class);
		cfg.addAnnotatedClass(HsttSupplierMst.class);
		cfg.addAnnotatedClass(HsttPoItemDtl.class);
		cfg.addAnnotatedClass(HsttSubgroupMst.class);
		cfg.addAnnotatedClass(HsttDrugContradictionMst.class);
		cfg.addAnnotatedClass(HsttAuthorityHierMst.class);
		cfg.addAnnotatedClass(HsttThirdpartyIssueItemDtl.class);
		cfg.addAnnotatedClass(HsttThirdpartyIssueDtl.class);

		cfg.addAnnotatedClass(GbltCountryMstImsc.class);
		
		cfg.addAnnotatedClass(HsttBreakageDtl.class);
		cfg.addAnnotatedClass(HsttBreakageItemDtl.class);
		
		cfg.addAnnotatedClass(HsttStoreReqtypeMst.class);
		cfg.addAnnotatedClass(HsttPeneltyConfigDtl.class);

		cfg.addAnnotatedClass(SsttPoDtl.class);
		cfg.addAnnotatedClass(HsttPoDtl.class);
		cfg.addAnnotatedClass(SsttPurchasemodeMst.class);
		cfg.addAnnotatedClass(SsttPoPrefixMst.class);
		cfg.addAnnotatedClass(HsttBudgetDtl.class);
		cfg.addAnnotatedClass(HsttBudgetAllocationDtl.class);
		cfg.addAnnotatedClass(SsttProgrammeTypeMst.class);
		cfg.addAnnotatedClass(HsttPrgFsMappingMst.class);
	 	cfg.addAnnotatedClass(HsttPoScheduleDtl.class);
		

		cfg.addAnnotatedClass(SsttPatempIssueDtl.class);
		cfg.addAnnotatedClass(SsttPatempIssueItemDtl.class);
		cfg.addAnnotatedClass(HsttPatientDtl.class);
		cfg.addAnnotatedClass(HsttPatientCountDayDss.class);
		cfg.addAnnotatedClass(HsttPatempIssueDtl.class);
		cfg.addAnnotatedClass(GbltDepartmentMst.class);
		cfg.addAnnotatedClass(GbltDiagnosisMst.class);
		cfg.addAnnotatedClass(HsttRatecontractItemLogDtl.class);
		cfg.addAnnotatedClass(SsttIndentDtl.class);
		cfg.addAnnotatedClass(HsttDeptEmpMst.class);
		cfg.addAnnotatedClass(HsttDeptDiagnosisMst.class);

		cfg.addAnnotatedClass(SsttIndentDtl.class);
		cfg.addAnnotatedClass(HsttStorehierarchyMst.class);
		cfg.addAnnotatedClass(SsttStockStatusMst.class);
		cfg.addAnnotatedClass(HsttIssueItemDtl.class);
		cfg.addAnnotatedClass(HsttIssueDtl.class);
		cfg.addAnnotatedClass(SsttProgrammeTypeMst.class);
		cfg.addAnnotatedClass(HsttPrgFsMappingMst.class);
		cfg.addAnnotatedClass(SsttIssueDtl.class);
		cfg.addAnnotatedClass(HsttPurIndentItemDdwDtl.class);
		cfg.addAnnotatedClass(HsttStockDssDtl.class);
		

		cfg.addAnnotatedClass(HsttStorehierarchyMst.class);
		cfg.addAnnotatedClass(HsttDrugAnnualValueDtl.class);
		cfg.addAnnotatedClass(HsttIndentItemDtl.class);
		cfg.addAnnotatedClass(HsttIndentDtl.class);
		cfg.addAnnotatedClass(SsttApprovalreqDtl.class);
		cfg.addAnnotatedClass(HsttIndentLogDtl.class);
		cfg.addAnnotatedClass(HsttIndentItemLogDtl.class);
		cfg.addAnnotatedClass(HsttTransShortReqLogDtl.class);
		
		
		cfg.addAnnotatedClass(HsttBlockeditemDtl.class);
		cfg.addAnnotatedClass(HsttSuppDeliveryDtl.class);
		cfg.addAnnotatedClass(HsttIssueLogDtl.class);
		cfg.addAnnotatedClass(HsttIssueItemLogDtl.class);
		cfg.addAnnotatedClass(SsttAcknowledgeDtl.class);
		cfg.addAnnotatedClass(HsttPoProgrammeItemDtl.class);
		cfg.addAnnotatedClass(HsttPoScheduleDtl.class);
		cfg.addAnnotatedClass(SsttBudgetConsumptionDtl.class);
		cfg.addAnnotatedClass(HsttPoProgrammeDtl.class);
		cfg.addAnnotatedClass(HsttPoComponentDtl.class);
		cfg.addAnnotatedClass(HsttPoScheduleLogDtl.class);
		cfg.addAnnotatedClass(HsttPoItemLogDtl.class);
		cfg.addAnnotatedClass(HsttPoLogDtl.class);
		cfg.addAnnotatedClass(HsttPoProgrammeItemLogDtl.class);
		cfg.addAnnotatedClass(HsttSupplierBatchDtl.class);
		cfg.addAnnotatedClass(HsttChallanDtl.class);
		cfg.addAnnotatedClass(HsttChallanItemDtl.class);
		cfg.addAnnotatedClass(HsttQcRejectedDtl.class);
		
		cfg.addAnnotatedClass(HsttApprovalItemDtl.class);
		cfg.addAnnotatedClass(HsttApprovalDtl.class);
		cfg.addAnnotatedClass(HsttStoreitemMst.class);
		cfg.addAnnotatedClass(HsttApprovalLogDtl.class);
		cfg.addAnnotatedClass(HsttApprovalItemLogDtl.class);
		cfg.addAnnotatedClass(HsttChallanVerifieditemDtl.class);
		cfg.addAnnotatedClass(HsttCurrencyMst.class);
		cfg.addAnnotatedClass(HsttQcRejectedDtl.class);
		cfg.addAnnotatedClass(HsttSuppDeliveryItemDtl.class);
		cfg.addAnnotatedClass(HsttChallanProgItemDtl.class);
		cfg.addAnnotatedClass(HsttReplacemntOrderDtl.class);
		cfg.addAnnotatedClass(SsttDrugParameterMst.class);
		cfg.addAnnotatedClass(HsttTransExcessReqLogDtl.class);
		cfg.addAnnotatedClass(HsttTransferShortReqDtl.class);
		
		cfg.addAnnotatedClass(HsttTransferExcessReqDtl.class);
		cfg.addAnnotatedClass(HsttTransferDtl.class);
		cfg.addAnnotatedClass(HsttTransferOrderDtl.class);
		cfg.addAnnotatedClass(HsttTransferItemDtl.class);
		cfg.addAnnotatedClass(HsttSuppDeliveryLogDtl.class);
		cfg.addAnnotatedClass(HsttSupDeliveryItemLogDtl.class);
		cfg.addAnnotatedClass(HsttCondemnRetReqDtl.class);
		cfg.addAnnotatedClass(HsttDrugParameterDtl.class);
		
		cfg.addAnnotatedClass(HsttAcknowledgeDtl.class);
		cfg.addAnnotatedClass(HsttAckItemDtl.class);
		
		
		cfg.addAnnotatedClass(HsttBudgetLogDtl.class);  
		
		
		cfg.setProperty("hibernate.connection.useUnicode", "true");
		cfg.setProperty("hibernate.connection.characterEncoding", "UTF-8");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.postgresql.Driver");

		cfg.setProperty("hibernate.show_sql", "true");
		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.PostgreSQLDialect");
		cfg.setProperty("hibernate.hbm2ddl.auto", "update");

		cfg.setProperty("hibernate.connection.datasource",
				HisUtil.getParameterFromHisPathXML("JNDI_LOOKUP"));

		
		 * cfg.setProperty("log4j.logger.org.hibernate.SQL", "debug");
		 * cfg.setProperty("log4j.logger.org.hibernate.type", "trace");
		 

		return cfg;
	}*/
	
}
