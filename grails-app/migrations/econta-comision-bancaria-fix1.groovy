databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1475597368922-1") {
		addColumn(tableName: "comision") {
			column(name: "gasto_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1475597368922-3") {
		createIndex(indexName: "FK_94dgh3l8548mpvfyw0xopyc7", tableName: "comision") {
			column(name: "gasto_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1475597368922-2") {
		addForeignKeyConstraint(baseColumnNames: "gasto_id", baseTableName: "comision", constraintName: "FK_94dgh3l8548mpvfyw0xopyc7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cuenta_por_pagar", referencesUniqueColumn: "false")
	}
}
