databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438695012094-1") {
		dropForeignKeyConstraint(baseTableName: "cuenta_por_pagar", baseTableSchemaName: "lxMobix", constraintName: "FK_gsif3fmkt5ysn1q0ndk0mugbj")
	}

	changeSet(author: "rcancino (generated)", id: "1438695012094-2") {
		dropColumn(columnName: "cuenta_contable_id", tableName: "cuenta_por_pagar")
	}

	changeSet(author: "rcancino (generated)", id: "1438695012094-3") {
		dropColumn(columnName: "concepto", tableName: "gasto_det")
	}
}
