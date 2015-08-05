databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1438719584042-1") {
		dropForeignKeyConstraint(baseTableName: "aplicacion_de_cobro", baseTableSchemaName: "lx_mobix", constraintName: "FK_fru0dv7xpkbhslnwnc7cf5k9m")
	}

	changeSet(author: "rcancino (generated)", id: "1438719584042-3") {
		dropColumn(columnName: "abonos", tableName: "venta")
	}

	changeSet(author: "rcancino (generated)", id: "1438719584042-4") {
		dropColumn(columnName: "pagos", tableName: "venta")
	}

	changeSet(author: "rcancino (generated)", id: "1438719584042-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_por_cobrar_id", baseTableName: "aplicacion_de_cobro", constraintName: "FK_fru0dv7xpkbhslnwnc7cf5k9m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venta", referencesUniqueColumn: "false")
	}
}
