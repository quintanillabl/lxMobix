databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1437236466212-1") {
		addColumn(tableName: "venta_det") {
			column(name: "inmueble_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437236466212-3") {
		createIndex(indexName: "FK_s0ihm8vyhwaraoo801g4o1ld9", tableName: "venta_det") {
			column(name: "inmueble_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1437236466212-2") {
		addForeignKeyConstraint(baseColumnNames: "inmueble_id", baseTableName: "venta_det", constraintName: "FK_s0ihm8vyhwaraoo801g4o1ld9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "inmueble", referencesUniqueColumn: "false")
	}
}
