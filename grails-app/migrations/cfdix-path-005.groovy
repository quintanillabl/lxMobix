databaseChangeLog = {

	changeSet(author: "rcancino (generated)", id: "1498066655335-1") {
		addColumn(tableName: "cobro") {
			column(name: "cfdi_id", type: "bigint")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1498066655335-3") {
		createIndex(indexName: "FK_32f6w9o5mybpef0aixfuclmq2", tableName: "cobro") {
			column(name: "cfdi_id")
		}
	}

	changeSet(author: "rcancino (generated)", id: "1498066655335-2") {
		addForeignKeyConstraint(baseColumnNames: "cfdi_id", baseTableName: "cobro", constraintName: "FK_32f6w9o5mybpef0aixfuclmq2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "cfdi", referencesUniqueColumn: "false")
	}
}
