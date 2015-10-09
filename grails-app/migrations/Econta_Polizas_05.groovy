databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1444341436072-1") {
		addColumn(tableName: "arrendamiento") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444341436072-2") {
		dropForeignKeyConstraint(baseTableName: "arrendamiento", baseTableSchemaName: "lx_mobix", constraintName: "FK_4a6y7htgtk4y0w68chwryvrug")
	}

	changeSet(author: "rcancino (generated)", id: "1444341436072-4") {
		createIndex(indexName: "FK_6dtcfc08ad75psdsmbo09anp7", tableName: "arrendamiento") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1444341436072-5") {
		dropColumn(columnName: "cuenta_deudora_id", tableName: "arrendamiento")
	}

	changeSet(author: "rcancino (generated)", id: "1444341436072-3") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "arrendamiento", constraintName: "FK_6dtcfc08ad75psdsmbo09anp7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}
}
