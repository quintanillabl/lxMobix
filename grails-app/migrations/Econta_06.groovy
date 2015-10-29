databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1446143860313-1") {
		addColumn(tableName: "proveedor") {
			column(name: "cuenta_contable_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1446143860313-3") {
		createIndex(indexName: "FK_r9hxg93b0edj8hd13y3ixtmwq", tableName: "proveedor") {
			column(name: "cuenta_contable_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1446143860313-2") {
		addForeignKeyConstraint(baseColumnNames: "cuenta_contable_id", baseTableName: "proveedor", constraintName: "FK_r9hxg93b0edj8hd13y3ixtmwq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_contable", referencesUniqueColumn: "false")
	}
}
